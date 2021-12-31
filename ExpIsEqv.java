public class ExpIsEqv extends Exp {
    
    protected ExpIsEqv(Exp e1, Exp e2) {
        super("eqv?", e1, e2);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpIsEqv(this, arg);
    }

    @Override
    public String toString() {
        return "<" + getName() + ">" + getSubTrees().toString();
    }
}
