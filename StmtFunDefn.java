import java.util.ArrayList;
/**
 * IR Class to represent a function definition
 */


public class StmtFunDefn extends Statement {

	String var;
    ArrayList<String> params;
    StmtSequence statements;
    Exp expression;

    public StmtFunDefn(String id, ArrayList<String> pe, StmtSequence s) {
	super("fun def", s);
	var = id;
    params = pe;
    statements = s;
    }

    public StmtFunDefn(String id, ArrayList<String> pe, Exp e) {
	super("fun def", e);
	var = id;
    params = pe;
    expression = e;
    }

    public String toString(){
    	return "";
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException {
	return v.visitStmtFunDefn(this, arg);
    }

    public String getVar(){
    	return this.var;
    }

    public ArrayList<String> getParams(){
    	return this.params;
    }

    public StmtSequence getStatements(){
    	return this.statements;
    }

    public Exp getExpression(){
    	return this.expression;
    }

}
