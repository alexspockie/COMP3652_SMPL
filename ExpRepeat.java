public class ExpRepeat extends Exp {
    public ExpRepeat(Exp cond, Exp body) {
        super("repeat", cond, body);
    }

    public Exp getExpCond() {
        return getSubTree(0);
    }

    public Exp getBody() {
        return getSubTree(1);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpRepeat(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
