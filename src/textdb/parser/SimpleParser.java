/* SimpleParser.java */
/* Generated By:JJTree&JavaCC: Do not edit this line. SimpleParser.java */
package textdb.parser;

public class SimpleParser/*@bgen(jjtree)*/implements SimpleParserTreeConstants, SimpleParserConstants {/*@bgen(jjtree)*/
  protected JJTSimpleParserState jjtree = new JJTSimpleParserState();ASTStart n = null;

  public void parseString() throws Exception {
      n = Start();
  }

  public static void main(String args[]) {
    System.out.println("Reading from standard input...");
    SimpleParser t = new SimpleParser(System.in);
    try {
      SimpleNode n = t.Start();
      n.dump("");
      System.out.println("Thank you.");
    } catch (Exception e) {
      System.out.println("Oops.");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  public ASTStart gettree() {
    return n;
  }

// Start of production rules
  final public 
ASTStart Start() throws ParseException {/*@bgen(jjtree) Start */
  ASTStart jjtn000 = new ASTStart(JJTSTART);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      SQLQuery();
jjtree.closeNodeScope(jjtn000, true);
                     jjtc000 = false;
{if ("" != null) return jjtn000;}
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
    throw new Error("Missing return statement in function");
}

/* 
 SQL SELECT query syntax
*/
  final public void SQLQuery() throws ParseException {
    Query();
}

  final public void Query() throws ParseException {
    Select();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case FROM:{
      From();
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case WHERE:{
      Where();
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ORDERBY:{
      OrderBy();
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      ;
    }
}

  final public void Select() throws ParseException {/*@bgen(jjtree) #Select(true) */
  ASTSelect jjtn000 = new ASTSelect(JJTSELECT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(SELECT);
      SQLSelectList();
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
}

  final public void SQLSelectList() throws ParseException {
    AdditiveExpression();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 33:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_1;
      }
      jj_consume_token(33);
      AdditiveExpression();
    }
}

  final public void From() throws ParseException {/*@bgen(jjtree) #From(> 0) */
  ASTFrom jjtn000 = new ASTFrom(JJTFROM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(FROM);
      FromItem();
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 33:{
          ;
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          break label_2;
        }
        jj_consume_token(33);
        FromItem();
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 0);
          }
    }
}

  final public void FromItem() throws ParseException {
    Identifier();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INNER:
      case JOIN:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_3;
      }
      JoinExpr();
    }
}

  final public void JoinExpr() throws ParseException {/*@bgen(jjtree) #JoinExpr(true) */
                              ASTJoinExpr jjtn000 = new ASTJoinExpr(JJTJOINEXPR);
                              boolean jjtc000 = true;
                              jjtree.openNodeScope(jjtn000);String s;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INNER:{
        jj_consume_token(INNER);
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        ;
      }
      jj_consume_token(JOIN);
      FromItem();
      On();
    } catch (Throwable jjte000) {
if (jjtc000) {
       jjtree.clearNodeScope(jjtn000);
       jjtc000 = false;
     } else {
       jjtree.popNode();
     }
     if (jjte000 instanceof RuntimeException) {
       {if (true) throw (RuntimeException)jjte000;}
     }
     if (jjte000 instanceof ParseException) {
       {if (true) throw (ParseException)jjte000;}
     }
     {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
       jjtree.closeNodeScope(jjtn000, true);
     }
    }
}

  final public void On() throws ParseException {/*@bgen(jjtree) #On(true) */
  ASTOn jjtn000 = new ASTOn(JJTON);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(ON);
      SQLOrExpr();
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
}

  final public void Where() throws ParseException {/*@bgen(jjtree) #Where(> 0) */
  ASTWhere jjtn000 = new ASTWhere(JJTWHERE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(WHERE);
      SQLOrExpr();
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 0);
          }
    }
}

/*
TODO: Add OrderBy support here.
*/
  final public void OrderBy() throws ParseException {/*@bgen(jjtree) #OrderBy(> 0) */
  ASTOrderBy jjtn000 = new ASTOrderBy(JJTORDERBY);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(ORDERBY);
      AdditiveExpression();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASC:
      case DESC:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ASC:{
          ASC();
          break;
          }
        case DESC:{
          DESC();
          break;
          }
        default:
          jj_la1[7] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        ;
      }
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 33:{
          ;
          break;
          }
        default:
          jj_la1[9] = jj_gen;
          break label_4;
        }
        jj_consume_token(33);
        AdditiveExpression();
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ASC:
        case DESC:{
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case ASC:{
            ASC();
            break;
            }
          case DESC:{
            DESC();
            break;
            }
          default:
            jj_la1[10] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
          }
        default:
          jj_la1[11] = jj_gen;
          ;
        }
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 0);
      }
    }
}

  final public void ASC() throws ParseException {/*@bgen(jjtree) ASC */
                   ASTASC jjtn000 = new ASTASC(JJTASC);
                   boolean jjtc000 = true;
                   jjtree.openNodeScope(jjtn000);Token t;
    try {
ASTASC jjtn001 = new ASTASC(JJTASC);
     boolean jjtc001 = true;
     jjtree.openNodeScope(jjtn001);
      try {
        t = jj_consume_token(ASC);
      } finally {
if (jjtc001) {
       jjtree.closeNodeScope(jjtn001, true);
     }
      }
jjtree.closeNodeScope(jjtn000, true);
                   jjtc000 = false;
jjtn000.value=t.image;
    } finally {
if (jjtc000) {
       jjtree.closeNodeScope(jjtn000, true);
     }
    }
}

  final public void DESC() throws ParseException {/*@bgen(jjtree) DESC */
                     ASTDESC jjtn000 = new ASTDESC(JJTDESC);
                     boolean jjtc000 = true;
                     jjtree.openNodeScope(jjtn000);Token t;
    try {
ASTDESC jjtn001 = new ASTDESC(JJTDESC);
     boolean jjtc001 = true;
     jjtree.openNodeScope(jjtn001);
      try {
        t = jj_consume_token(DESC);
      } finally {
if (jjtc001) {
       jjtree.closeNodeScope(jjtn001, true);
     }
      }
jjtree.closeNodeScope(jjtn000, true);
                     jjtc000 = false;
jjtn000.value=t.image;
    } finally {
if (jjtc000) {
       jjtree.closeNodeScope(jjtn000, true);
     }
    }
}

  final public void AdditiveExpression() throws ParseException {/*@bgen(jjtree) #Add(> 1) */
  ASTAdd jjtn000 = new ASTAdd(JJTADD);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      MultiplicativeExpression();
      label_5:
      while (true) {
        if (jj_2_1(2)) {
          ;
        } else {
          break label_5;
        }
        Aop();
        MultiplicativeExpression();
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
          }
    }
}

  final public void Aop() throws ParseException {/*@bgen(jjtree) Aop */
  ASTAop jjtn000 = new ASTAop(JJTAOP);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 34:{
        jj_consume_token(34);
jjtree.closeNodeScope(jjtn000, true);
                          jjtc000 = false;
jjtn000.setName("+");
        break;
        }
      case 35:{
        jj_consume_token(35);
jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
jjtn000.setName("-");
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, true);
          }
    }
}

  final public void Mop() throws ParseException {/*@bgen(jjtree) Mop */
  ASTMop jjtn000 = new ASTMop(JJTMOP);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 36:{
        jj_consume_token(36);
jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
jjtn000.setName("*");
        break;
        }
      case 37:{
        jj_consume_token(37);
jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
jjtn000.setName("/");
        break;
        }
      case 38:{
        jj_consume_token(38);
jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
jjtn000.setName("%");
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
}

  final public void MultiplicativeExpression() throws ParseException {/*@bgen(jjtree) #Mult(> 1) */
  ASTMult jjtn000 = new ASTMult(JJTMULT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      SQLTerm();
      label_6:
      while (true) {
        if (jj_2_2(2)) {
          ;
        } else {
          break label_6;
        }
        Mop();
        SQLTerm();
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
          }
    }
}

  final public void SQLTerm() throws ParseException {
    if (jj_2_3(2)) {
      jj_consume_token(OPENPAREN);
      SQLOrExpr();
      jj_consume_token(CLOSEPAREN);
    } else if (jj_2_4(2)) {
      SQLLiteral();
    } else if (jj_2_5(2)) {
      Identifier();
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void SQLOrExpr() throws ParseException {/*@bgen(jjtree) #Or(> 1) */
  ASTOr jjtn000 = new ASTOr(JJTOR);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      SQLAndExpr();
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case OR:
        case XOR:{
          ;
          break;
          }
        default:
          jj_la1[14] = jj_gen;
          break label_7;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case OR:{
          jj_consume_token(OR);
          SQLAndExpr();
          break;
          }
        case XOR:{
          jj_consume_token(XOR);
jjtn000.setType(2);
          SQLAndExpr();
          break;
          }
        default:
          jj_la1[15] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
    }
    }
}

  final public void SQLAndExpr() throws ParseException {/*@bgen(jjtree) #And(> 1) */
  ASTAnd jjtn000 = new ASTAnd(JJTAND);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      SQLNotExpr();
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case AND:{
          ;
          break;
          }
        default:
          jj_la1[16] = jj_gen;
          break label_8;
        }
        jj_consume_token(AND);
        SQLNotExpr();
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
    }
    }
}

  final public void SQLNotExpr() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NOT:{
      Not();
      break;
      }
    case OPENPAREN:
    case INTEGER_LITERAL:
    case FLOATING_POINT_LITERAL:
    case STRING_LITERAL:
    case IDENTIFIER:{
      SQLCompareExpr();
      break;
      }
    default:
      jj_la1[17] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void Not() throws ParseException {/*@bgen(jjtree) #Not(> 0) */
  ASTNot jjtn000 = new ASTNot(JJTNOT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(NOT);
      SQLCompareExpr();
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 0);
    }
    }
}

  final public void SQLCompareExpr() throws ParseException {/*@bgen(jjtree) #Cop(> 1) */
                                  ASTCop jjtn000 = new ASTCop(JJTCOP);
                                  boolean jjtc000 = true;
                                  jjtree.openNodeScope(jjtn000);Token t;Token u = null;
    try {
      AdditiveExpression();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 39:{
          t = jj_consume_token(39);
          break;
          }
        case 40:{
          t = jj_consume_token(40);
          break;
          }
        case 41:{
          t = jj_consume_token(41);
          break;
          }
        case 42:{
          t = jj_consume_token(42);
          break;
          }
        case 43:{
          t = jj_consume_token(43);
          break;
          }
        case 44:{
          t = jj_consume_token(44);
          break;
          }
        case 45:{
          t = jj_consume_token(45);
          break;
          }
        default:
          jj_la1[18] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        AdditiveExpression();
String name = t.image; if (u != null) name += u.image; jjtn000.setName(name);
        break;
        }
      default:
        jj_la1[19] = jj_gen;
        ;
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
          } else {
            jjtree.popNode();
          }
          if (jjte000 instanceof RuntimeException) {
            {if (true) throw (RuntimeException)jjte000;}
          }
          if (jjte000 instanceof ParseException) {
            {if (true) throw (ParseException)jjte000;}
          }
          {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
            jjtree.closeNodeScope(jjtn000, jjtree.nodeArity() > 1);
          }
    }
}

  final public void SQLLiteral() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case STRING_LITERAL:{
      StringLiteral();
      break;
      }
    case INTEGER_LITERAL:{
      IntLiteral();
      break;
      }
    case FLOATING_POINT_LITERAL:{
      FloatLiteral();
      break;
      }
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void IntLiteral() throws ParseException {/*@bgen(jjtree) Integer */
                              ASTInteger jjtn000 = new ASTInteger(JJTINTEGER);
                              boolean jjtc000 = true;
                              jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(INTEGER_LITERAL);
jjtree.closeNodeScope(jjtn000, true);
                        jjtc000 = false;
jjtn000.setName(t.image);
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
}

  final public void FloatLiteral() throws ParseException {/*@bgen(jjtree) Real */
                             ASTReal jjtn000 = new ASTReal(JJTREAL);
                             boolean jjtc000 = true;
                             jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(FLOATING_POINT_LITERAL);
jjtree.closeNodeScope(jjtn000, true);
                               jjtc000 = false;
jjtn000.setName(t.image);
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
}

  final public void StringLiteral() throws ParseException {/*@bgen(jjtree) String */
                                ASTString jjtn000 = new ASTString(JJTSTRING);
                                boolean jjtc000 = true;
                                jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(STRING_LITERAL);
jjtree.closeNodeScope(jjtn000, true);
                       jjtc000 = false;
jjtn000.setName(t.image);
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
}

  final public Token Identifier() throws ParseException {/*@bgen(jjtree) MyID */
                            ASTMyID jjtn000 = new ASTMyID(JJTMYID);
                            boolean jjtc000 = true;
                            jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(IDENTIFIER);
jjtree.closeNodeScope(jjtn000, true);
                   jjtc000 = false;
jjtn000.setName(t.image);  {if ("" != null) return t;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
}

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_1()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_2()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_3()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_4()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_5()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_3R_Not_231_3_31()
 {
    if (jj_scan_token(NOT)) return true;
    return false;
  }

  private boolean jj_3R_SQLLiteral_243_36_24()
 {
    if (jj_3R_FloatLiteral_253_3_28()) return true;
    return false;
  }

  private boolean jj_3R_SQLNotExpr_226_4_29()
 {
    if (jj_3R_Not_231_3_31()) return true;
    return false;
  }

  private boolean jj_3R_SQLNotExpr_226_3_25()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_SQLNotExpr_226_4_29()) {
    jj_scanpos = xsp;
    if (jj_3R_SQLNotExpr_226_12_30()) return true;
    }
    return false;
  }

  private boolean jj_3R_SQLAndExpr_221_3_21()
 {
    if (jj_3R_SQLNotExpr_226_3_25()) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_3R_Mop_194_3_11()) return true;
    if (jj_3R_SQLTerm_207_3_12()) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_Aop_187_9_9()) return true;
    if (jj_3R_MultiplicativeExpression_202_9_10()) return true;
    return false;
  }

  private boolean jj_3R_SQLOrExpr_216_3_13()
 {
    if (jj_3R_SQLAndExpr_221_3_21()) return true;
    return false;
  }

  private boolean jj_3R_Identifier_263_3_15()
 {
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  private boolean jj_3_4()
 {
    if (jj_3R_SQLLiteral_243_3_14()) return true;
    return false;
  }

  private boolean jj_3R_SQLLiteral_243_21_23()
 {
    if (jj_3R_IntLiteral_248_3_27()) return true;
    return false;
  }

  private boolean jj_3_5()
 {
    if (jj_3R_Identifier_263_3_15()) return true;
    return false;
  }

  private boolean jj_3R_StringLiteral_258_3_26()
 {
    if (jj_scan_token(STRING_LITERAL)) return true;
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_scan_token(OPENPAREN)) return true;
    if (jj_3R_SQLOrExpr_216_3_13()) return true;
    return false;
  }

  private boolean jj_3R_MultiplicativeExpression_202_9_10()
 {
    if (jj_3R_SQLTerm_207_3_12()) return true;
    return false;
  }

  private boolean jj_3R_SQLTerm_207_3_12()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3_5()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_FloatLiteral_253_3_28()
 {
    if (jj_scan_token(FLOATING_POINT_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_Mop_196_11_20()
 {
    if (jj_scan_token(38)) return true;
    return false;
  }

  private boolean jj_3R_Mop_195_11_19()
 {
    if (jj_scan_token(37)) return true;
    return false;
  }

  private boolean jj_3R_IntLiteral_248_3_27()
 {
    if (jj_scan_token(INTEGER_LITERAL)) return true;
    return false;
  }

  private boolean jj_3R_SQLLiteral_243_3_22()
 {
    if (jj_3R_StringLiteral_258_3_26()) return true;
    return false;
  }

  private boolean jj_3R_Aop_188_11_17()
 {
    if (jj_scan_token(35)) return true;
    return false;
  }

  private boolean jj_3R_SQLLiteral_243_3_14()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_SQLLiteral_243_3_22()) {
    jj_scanpos = xsp;
    if (jj_3R_SQLLiteral_243_21_23()) {
    jj_scanpos = xsp;
    if (jj_3R_SQLLiteral_243_36_24()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_SQLCompareExpr_236_9_32()
 {
    if (jj_3R_AdditiveExpression_182_9_33()) return true;
    return false;
  }

  private boolean jj_3R_Mop_194_4_18()
 {
    if (jj_scan_token(36)) return true;
    return false;
  }

  private boolean jj_3R_Mop_194_3_11()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_Mop_194_4_18()) {
    jj_scanpos = xsp;
    if (jj_3R_Mop_195_11_19()) {
    jj_scanpos = xsp;
    if (jj_3R_Mop_196_11_20()) return true;
    }
    }
    return false;
  }

  private boolean jj_3R_Aop_187_10_16()
 {
    if (jj_scan_token(34)) return true;
    return false;
  }

  private boolean jj_3R_Aop_187_9_9()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_Aop_187_10_16()) {
    jj_scanpos = xsp;
    if (jj_3R_Aop_188_11_17()) return true;
    }
    return false;
  }

  private boolean jj_3R_AdditiveExpression_182_9_33()
 {
    if (jj_3R_MultiplicativeExpression_202_9_10()) return true;
    return false;
  }

  private boolean jj_3R_SQLNotExpr_226_12_30()
 {
    if (jj_3R_SQLCompareExpr_236_9_32()) return true;
    return false;
  }

  /** Generated Token Manager. */
  public SimpleParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x200,0x400,0x2000,0x0,0x0,0x18000,0x8000,0x1800,0x1800,0x0,0x1800,0x1800,0x0,0x0,0x500000,0x500000,0x80000,0x1b220000,0x0,0x0,0xb000000,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x0,0x2,0x2,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0xc,0x70,0x0,0x0,0x0,0x0,0x3f80,0x3f80,0x0,};
	}
  final private JJCalls[] jj_2_rtns = new JJCalls[5];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public SimpleParser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SimpleParser(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new SimpleParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jjtree.reset();
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public SimpleParser(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new SimpleParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new SimpleParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jjtree.reset();
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public SimpleParser(SimpleParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(SimpleParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jjtree.reset();
	 jj_gen = 0;
	 for (int i = 0; i < 21; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   if (++jj_gc > 100) {
		 jj_gc = 0;
		 for (int i = 0; i < jj_2_rtns.length; i++) {
		   JJCalls c = jj_2_rtns[i];
		   while (c != null) {
			 if (c.gen < jj_gen) c.first = null;
			 c = c.next;
		   }
		 }
	   }
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error {
    @Override
    public Throwable fillInStackTrace() {
      return this;
    }
  }
  static private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
	 if (jj_scanpos == jj_lastpos) {
	   jj_la--;
	   if (jj_scanpos.next == null) {
		 jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
	   } else {
		 jj_lastpos = jj_scanpos = jj_scanpos.next;
	   }
	 } else {
	   jj_scanpos = jj_scanpos.next;
	 }
	 if (jj_rescan) {
	   int i = 0; Token tok = token;
	   while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
	   if (tok != null) jj_add_error_token(kind, i);
	 }
	 if (jj_scanpos.kind != kind) return true;
	 if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
	 return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
	 if (pos >= 100) {
		return;
	 }

	 if (pos == jj_endpos + 1) {
	   jj_lasttokens[jj_endpos++] = kind;
	 } else if (jj_endpos != 0) {
	   jj_expentry = new int[jj_endpos];

	   for (int i = 0; i < jj_endpos; i++) {
		 jj_expentry[i] = jj_lasttokens[i];
	   }

	   for (int[] oldentry : jj_expentries) {
		 if (oldentry.length == jj_expentry.length) {
		   boolean isMatched = true;

		   for (int i = 0; i < jj_expentry.length; i++) {
			 if (oldentry[i] != jj_expentry[i]) {
			   isMatched = false;
			   break;
			 }

		   }
		   if (isMatched) {
			 jj_expentries.add(jj_expentry);
			 break;
		   }
		 }
	   }

	   if (pos != 0) {
		 jj_lasttokens[(jj_endpos = pos) - 1] = kind;
	   }
	 }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[46];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 21; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 46; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 jj_endpos = 0;
	 jj_rescan_token();
	 jj_add_error_token(0, 0);
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
	 jj_rescan = true;
	 for (int i = 0; i < 5; i++) {
	   try {
		 JJCalls p = jj_2_rtns[i];

		 do {
		   if (p.gen > jj_gen) {
			 jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
			 switch (i) {
			   case 0: jj_3_1(); break;
			   case 1: jj_3_2(); break;
			   case 2: jj_3_3(); break;
			   case 3: jj_3_4(); break;
			   case 4: jj_3_5(); break;
			 }
		   }
		   p = p.next;
		 } while (p != null);

		 } catch(LookaheadSuccess ls) { }
	 }
	 jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
	 JJCalls p = jj_2_rtns[index];
	 while (p.gen > jj_gen) {
	   if (p.next == null) { p = p.next = new JJCalls(); break; }
	   p = p.next;
	 }

	 p.gen = jj_gen + xla - jj_la; 
	 p.first = token;
	 p.arg = xla;
  }

  static final class JJCalls {
	 int gen;
	 Token first;
	 int arg;
	 JJCalls next;
  }

}
