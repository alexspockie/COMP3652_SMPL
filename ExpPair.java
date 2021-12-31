

public class ExpPair extends Exp{

    protected ExpPair(Exp e1, Exp e2) {
        super("pair", e1, e2);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpPair(this, arg);
    }

    @Override
    public String toString() {
        return "<pair>"+getSubTrees().toString();
    }
    
}
