//import java.util.ArrayList;
/**
 * IR Class to represent a function definition
 */

public class StmtExpDefn extends Statement{
    String var;
    Exp expression;

    public StmtExpDefn(String id, Exp epression) {
	super("assign exp", epression);
	var = id;
    expression=epression;
    }

   

    public String toString(){
    	return "";
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException,NoSuchMethodException  {
	return v.visitStmtExpDefn(this, arg);    }

    public String getVar(){
    	return this.var;
    }

    public Exp getExpression(){
    	return this.expression;
    }

}



