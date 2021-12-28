public class ExpCompare extends ExpBinOp {

	boolean temp;
	Cmp c;

    public ExpCompare(Exp e1, Exp e2, Cmp c) {
	super(c.toString(), e1, e2);
	this.c = c;
    }

    public void setVal(boolean ans){
    	temp = ans;
    }

    public boolean getVal(){
    	return this.temp;
    }

    public Cmp getC(){
    	return this.c;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException, NoSuchMethodException {
	return v.visitExpCompare(this, arg);
    }

}
