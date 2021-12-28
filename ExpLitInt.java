public class ExpLitInt extends Exp {

    int val;

    public ExpLitInt(Integer v) {
	super(v.toString());
	val = v.intValue();
    }

    public int getVal() {
	return val;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
	return v.visitExpLit(this, arg);
    }

    public String toString() {
	return Integer.toString(val);
    }
}

