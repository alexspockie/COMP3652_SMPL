public class ExpIsPair extends Exp {
    protected ExpIsPair(Exp exp) {
        super("pair?", exp);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpIsPair(this, arg);
    }

    @Override
    public String toString() {
        return "<" + getName() + ">" + getSubTree(0).toString();
    }
}
