
public class StmtDefinition extends Statement {

    String var;
    ExpVecCall expV;
    public void setVar(String var) {
        this.var = var;
    }

    public StmtDefinition(String id, Exp e) {
	super(":=", e);
	var = id;
	// exp = e;
    }

    public StmtDefinition(ExpVecCall v, Exp e) {
        super(":=", e);
        expV = v;
    }

    public String getVar() {
        return var;
    }
    
    public ExpVecCall getExpVecCall() {
        return this.expV;
    }

    public Exp getExp() {
	return getSubTree(0);
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException,NoSuchMethodException  {
	return v.visitStmtDefinition(this, arg);
    }

    public String toString() {
	return String.format("%s := %s", getVar(), getExp().toString());
    }
}
