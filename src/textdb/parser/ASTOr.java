/* Generated By:JJTree: Do not edit this line. ASTOr.java */
package textdb.parser;

public class ASTOr extends SimpleNode {
  public ASTOr(int id) {
    super(id);
  }

  public ASTOr(SimpleParser p, int id) {
    super(p, id);
  }

  public void setType(int t) {
    	type = t;
  }

  public String toString() {	
	  if (type == 1)
		  return "Or";
	  else
		  return "Xor";
  }
  
  private int type = 1;		// Default is OR otherwise XOR (2)
}