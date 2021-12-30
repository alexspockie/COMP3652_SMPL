public class ExpComp extends ExpBinOp {

	boolean temp;
	String c;

    public ExpComp(Exp e1, Exp e2, String c) {
	super(c, e1, e2);
	this.c = c;
    }

    public void setVal(boolean ans){
    	temp = ans;
    }

    public boolean getVal(){
    	return this.temp;
    }

    public String getC(){
    	return this.c;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException, NoSuchMethodException {
	return v.visitExpComp(this, arg);
    }

}
