public class ExpCdr extends Exp{

    protected ExpCdr(Exp exp) {
        super("cdr", exp);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpCdr(this, arg);
    }

    @Override
    public String toString() {
        return "<" + getName() + ">" + getSubTree(0).toString();
    }
    
}
