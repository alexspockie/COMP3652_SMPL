public class ExpIsEq extends Exp{

    protected ExpIsEq(Exp e1, Exp e2) {
        super("equal?", e1, e2);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpIsEq(this, arg);
    }

    @Override
    public String toString() {
        return "<" + getName() + ">" + getSubTrees().toString();
    }
    
}
