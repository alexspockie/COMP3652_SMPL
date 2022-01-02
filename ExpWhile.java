public class ExpWhile extends Exp{

    public ExpWhile(Exp cond, Exp body) {
        super("while", cond, body);
    }

    public Exp getExpCond() {
        return getSubTree(0);
    }

    public Exp getBody() {
        return getSubTree(1);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpWhile(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
