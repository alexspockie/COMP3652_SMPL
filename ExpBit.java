public class ExpBit extends ExpBinOp {
	String s;
	BitwiseOp c;
    public ExpBit(Exp e1, Exp e2, BitwiseOp c) {
	super(c.toString(), e1, e2);
	this.s = c.toString();
	this.c = c;
    }
    public BitwiseOp getC(){
    	return this.c;
    }

    public String getS(){
    	return this.s;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException, NoSuchMethodException {
	return v.visitExpBit(this, arg);
    }

}