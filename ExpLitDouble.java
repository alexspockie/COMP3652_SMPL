public class ExpLitDouble extends Exp {
    double val;

    public ExpLitDouble(Double v) {
	super(v.toString());
	val = v.doubleValue();
    }

    public Double getVal() {
	return val;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
	return v.visitExpDouble(this, arg);
    }

    public String toString() {
	return Double.toString(val);
    }
}

