package textdb.query;

import java.io.StringReader;
import java.sql.SQLException;

import textdb.functions.ConstantValue;
import textdb.functions.Expression;
import textdb.functions.ExtractAttribute;
import textdb.operators.BlockNestedLoopJoin;
import textdb.operators.Operator;
import textdb.operators.Projection;
import textdb.operators.Selection;
import textdb.operators.TextFileScan;
import textdb.parser.ASTAnd;
import textdb.parser.ASTCop;
import textdb.parser.ASTFrom;
import textdb.parser.ASTInteger;
import textdb.parser.ASTSelect;
import textdb.parser.ASTString;
import textdb.parser.ASTWhere;
import textdb.parser.SimpleNode;
import textdb.parser.SimpleParser;
import textdb.parser.TokenMgrError;
import textdb.predicates.And;
import textdb.predicates.Equal;
import textdb.predicates.EquiJoinPredicate;
import textdb.predicates.Greater;
import textdb.predicates.GreaterEqual;
import textdb.predicates.Less;
import textdb.predicates.LessEqual;
import textdb.predicates.NotEqual;
import textdb.predicates.Predicate;
import textdb.predicates.SelectionExprPredicate;
import textdb.predicates.SelectionPredicate;
import textdb.relation.Attribute;
import textdb.relation.Relation;
import textdb.relation.Schema;


public class Optimizer
{	
	public SimpleNode buildParseTree(String sql) throws SQLException 
	{	// This method builds a parse tree from a query string.  Calls the appropriate code automatically generated by JavaCC.
		try {
			StringReader sb = new StringReader(sql);
			SimpleParser parser = new SimpleParser(sb);			
			parser.parseString();
			SimpleNode parseTreeRoot = parser.gettree();
            // System.out.println("Parse tree:\n");            
            // parseTreeRoot.dump("");            
			return parseTreeRoot;
		} catch (Exception e) {			
			throw new SQLException("Parse Exception: " + e);			
		}
        catch (TokenMgrError e) {
            throw new SQLException("Parse Exception: " + e);
        }        
	}
	
	public Operator buildPlan(Schema schema, String sql) throws SQLException
	{	SimpleNode parseRoot = buildParseTree(sql);
	
		parseRoot.dump("");
		
		// Validate tree and convert into execution tree directly.  Note no separate validation step.
		// Note: We are not converting into a relational algebra query tree as an intermediate step.
	
		// Nodes in source tree (parse tree) 
		SimpleNode pjNode=null, slNode=null, obNode=null, fromNode=null;
		
		// Traverse the children of the parse tree root, get each corresponding node
		for (int i = 0; i < parseRoot.jjtGetNumChildren(); i++)
		{
			SimpleNode child = (SimpleNode) parseRoot.jjtGetChild(i);
			if (child instanceof ASTSelect)
				pjNode = child;
			else if (child instanceof ASTWhere)
				slNode = child;			
			else if (child instanceof ASTFrom)
				fromNode = child;
			// TODO: Find if ORDER BY node is in parse tree similar to finding other nodes above
		}
		
		// Step #1: Validate each table in FROM clause.  Throw exception if field is not in schema.  Create scan for each table.  Create joins if more than one table.	
		Operator current;
		SimpleNode tableNode = (SimpleNode) fromNode.jjtGetChild(0);
		String tableName = extractIdentifier(tableNode.toString());
		
		// Lookup table in schema
		Relation r1 = schema.getTable(tableName);
		if (r1 == null)
			throw new SQLException("Table: "+tableName+" not found in schema.");
					
		// Create a file scan to read this table
		TextFileScan fscan = new TextFileScan(r1.getFileName(), r1);
		current = fscan;
	
		if (fromNode.jjtGetNumChildren() > 1)
		{	// Have a join clause.  Only supporting one join currently of form a = b.
			SimpleNode join = (SimpleNode)fromNode.jjtGetChild(1);
			String tableName2 = extractIdentifier(join.jjtGetChild(0).toString());
			// Lookup table in schema
			Relation r2 = schema.getTable(tableName2);
			if (r2 == null)
				throw new SQLException("Table: "+tableName2+" not found in schema.");
						
			// Create a file scan to read this table
			fscan = new TextFileScan(r2.getFileName(), r2);
			
			// Create a join between the two scans
			SimpleNode ON = (SimpleNode) join.jjtGetChild(1);
			String nodeContent = (String) ON.jjtGetChild(0).toString().substring(15);			
			if (!nodeContent.equals("="))
				throw new SQLException("Only simple equi-joins supported.");
			
			// Find each attribute
			SimpleNode compareOp = (SimpleNode) ON.jjtGetChild(0);
			String attr1Name = extractIdentifier(compareOp.jjtGetChild(0).toString());
			Attribute attr1 = r1.findAttributeByName(attr1Name);
			// Find the attribute in the input relation
			if (attr1 == null)
				throw new SQLException("Attribute: "+attr1Name+" not a valid attribute in the query.");
			int fieldIndex1 = r1.getAttributeIndex(attr1);	
			
			String attr2Name = extractIdentifier(compareOp.jjtGetChild(1).toString());
			Attribute attr2 = r2.findAttributeByName(attr2Name);
			// Find the attribute in the input relation
			if (attr2 == null)
				throw new SQLException("Attribute: "+attr2Name+" not a valid attribute in the query.");
			int fieldIndex2 = r2.getAttributeIndex(attr2);	
					
			EquiJoinPredicate pred = new EquiJoinPredicate(new int[]{fieldIndex1}, new int[]{fieldIndex2}, 1);
			BlockNestedLoopJoin bnlj = new BlockNestedLoopJoin(new Operator[]{current, fscan}, pred, 1000, 100);
			current = bnlj;			
			Relation outputRelation = new Relation(r1);
			outputRelation.mergeRelation(r2);
			current.setOutputRelation(outputRelation);			
		}
		
		// Step #2: Create a selection node if have a WHERE clause.  Put the entire clause into that selection node.
		if (slNode != null)
		{
			SelectionPredicate pred = buildSelPredicate((SimpleNode) slNode.jjtGetChild(0), current.getOutputRelation());			
			Selection selOp = new Selection(current, pred);
			current = selOp;			
		}

		
		// Step #3: Support sorting if ORDER BY node.  Only have to support one integer field e.g. ORDER BY r_regionkey
		// TODO: Check if have ORDER BY node
		// TODO: Determine attribute mentioned by ORDER BY clause and verify that it is in schema of current operator
		
		// TODO: Create a sort comparator (see code below)
		// SortComparator sorter = new SortComparator(new int[]{fieldIndex}, new boolean[]{sortAsc});		
		// textdb.operators.MergeSort mergeSortOp = new MergeSort(current, 10000, 100, sorter);
		// mergeSortOp.setOutputRelation(new Relation(inputRelation));
						
		// Step #4: Create a project node to only output fields required.  Verify that fields are correct at this time as well.  Throw an SQLException if field is not valid (in schema or in tables).
		int numAttr = pjNode.jjtGetNumChildren();
		Attribute []attr = new Attribute[numAttr];
		Expression[] exprList = new Expression[numAttr];
		Relation inputRelation = current.getOutputRelation();
		for (int i=0; i < numAttr; i++)
		{	String attrName = extractIdentifier(pjNode.jjtGetChild(i).toString());
			attr[i]= inputRelation.findAttributeByName(attrName);
			// Find the attribute in the input relation
			if (attr[i] == null)
				throw new SQLException("Attribute: "+attrName+" not a valid attribute in the query.");
			 int fieldIndex = inputRelation.getAttributeIndex(attr[i]);			    
			exprList[i] = new ExtractAttribute(fieldIndex);				
		}		
		
		Relation outputRelation =  new Relation(attr);
		Projection projOp = new Projection(current, exprList, outputRelation);
		current = projOp;
		
		return current;
	}
	
	public static String extractIdentifier(String identifierText)
	{	return identifierText.substring(12); }
	
	public static String outputPlan(SimpleNode root)
	{	
		return outputPlan(root, 0);
	}
	
	private static String outputPlan(SimpleNode root, int depth)
	{	StringBuffer buf = new StringBuffer(300);
		buf.append(spaces(depth*3));
		buf.append(root.toString());
		buf.append("\n");
		
		for (int i=0; i < root.jjtGetNumChildren(); i++)
			buf.append(outputPlan((SimpleNode) root.jjtGetChild(i), depth+1));
		
		return buf.toString();
	}
		
	public static String spaces(int count)
	{
		StringBuffer sp = new StringBuffer(count);
		for(int i = 0; i < count; i++)
			sp.append(" ");
		return sp.toString();
	}
		
	private SelectionPredicate buildSelPredicate(SimpleNode root, Relation inputRel) throws SQLException
	{
		SelectionPredicate pred = null;

		if (root == null)
			return null;

		if (root instanceof ASTCop)
		{	// Comparison operation  such as <, >, =			
			String nodeContent = (String) root.toString().substring(15);

			Predicate p = null;		
			if (nodeContent.equals("="))
				p = new Equal();
			else if (nodeContent.equals("<="))
				p = new LessEqual();
			else if (nodeContent.equals(">="))
				p = new GreaterEqual();
			else if (nodeContent.equals(">"))
				p = new Greater();
			else if (nodeContent.equals("<"))
				p = new Less();
			else if (nodeContent.equals("!=") || nodeContent.equals("<>"))
				p = new NotEqual(); 
							
			SimpleNode leftChild = (SimpleNode) root.jjtGetChild(0);
			SimpleNode rightChild = (SimpleNode) root.jjtGetChild(1);
							
			Expression left, right;
			left = buildExpression(leftChild, inputRel);
			right = buildExpression(rightChild, inputRel);
						
			SelectionExprPredicate exprPred = new SelectionExprPredicate(left, right, p);
			return exprPred;
		} // end base case
		else
		{	
			if (root instanceof ASTAnd)
			{
				return new And(buildSelPredicate((SimpleNode)root.jjtGetChild(0), inputRel), buildSelPredicate((SimpleNode) root.jjtGetChild(1), inputRel));
			}			
		}
		return pred;
	}

	/**
	 * Builds an expression directly from a parse tree node (root).  The expression may be either an integer or string quoted value or an attribute name.
	 * @param root
	 * @param rel
	 * @return
	 * @throws SQLException
	 */
	private Expression buildExpression(SimpleNode root, Relation rel) throws SQLException
	{
		
		if (root instanceof ASTInteger)
		{
			return new ConstantValue(new Integer(root.toString().substring(9)));			
		}
		else if (root instanceof ASTString)
		{
			return new ConstantValue(new String(root.toString().substring(8)));			
		}
		else
		{	// Assume it is an attribute name
			String fieldName = extractIdentifier(root.toString());			
			Attribute a = rel.findAttributeByName(fieldName);
			// Find the attribute in the input relation
			if (a == null)
				throw new SQLException("Attribute: "+fieldName+" not a valid attribute in the query.");
			int fieldIndex = rel.getAttributeIndex(a);			    
			return new ExtractAttribute(fieldIndex);						
		}
	}
	
	
}
