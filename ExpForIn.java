public class ExpForIn extends Exp {

    String var;

    public ExpForIn(String var, Exp iter, Exp body) {
        super("for in", iter, body);
        this.var = var;
    }

    public String getVar() {
        return var;
    }

    public Exp getExpIter() {
        return getSubTree(0);
    }

    public Exp getBody() {
        return getSubTree(1);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpForIn(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
