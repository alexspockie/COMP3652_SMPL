public class ExpLitChar extends Exp {

    Character ch;

    protected ExpLitChar(Character c) {
        super("char");
        ch = c;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpLitChar(this, arg);
    }

    @Override
    public String toString() {
        return ch.toString();
    }
    
}
