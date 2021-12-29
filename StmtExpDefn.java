//import java.util.ArrayList;
/**
 * IR Class to represent a function definition
 */

public class StmtExpDefn extends Statement{
    String var;
    Exp expression;
    ExpProcedure proc;

    public StmtExpDefn(String id, Exp epression) {
	super("assign exp", epression);
	var = id;
    expression=epression;
    proc=null;
    }

    public StmtExpDefn(String id, ExpProcedure epression) {
        super("assign exp", epression);
        var = id;
        proc=epression;
        }
    
    public ExpProcedure getProc(){
        return proc;
    }
    public Boolean isProc(){
        return proc==null;
    }
   //check if expression is an expprocedure?

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



