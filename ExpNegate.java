public class ExpNegate extends Exp {
    protected ExpNegate(Exp exp) {
        super("-", exp);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpNegate(this, arg);
    }

    @Override
    public String toString() {
        return String.format("%s %s", getName(), getSubTree(0).toString());
    }
    
}
