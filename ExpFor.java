public class ExpFor extends Exp {

    public ExpFor(Exp init, Exp cond, Exp inc, Exp body) {
        super("for", init, cond, inc, body);
    }

    public Exp getExpInit() {
        return getSubTree(0);
    }

    public Exp getExpCond() {
        return getSubTree(1);
    }

    public Exp getExpInc() {
        return getSubTree(2);
    }

    public Exp getBody() {
        return getSubTree(3);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpFor(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}