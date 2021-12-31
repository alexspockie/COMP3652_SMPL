public class ExpSubstr extends Exp{

    protected ExpSubstr(Exp e1, Exp e2, Exp e3) {
        super("substr", e1, e2, e3);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpSubstr(this, arg);
    }

    @Override
    public String toString() {
        return "<" + getName() + ">" + getSubTrees().toString();
    }
}
