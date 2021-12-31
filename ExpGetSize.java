public class ExpGetSize extends Exp {

    protected ExpGetSize(Exp exp) {
        super("size", exp);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpGetSize(this, arg);
    }

    @Override
    public String toString() {
        return "<" + getName() + ">" + getSubTree(0).toString();
    }
}
