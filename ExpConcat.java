public class ExpConcat extends Exp {

    protected ExpConcat(Exp e1, Exp e2) {
        super("@", e1, e2);
    }

    public Exp getLeft(){
        return getSubTree(0);
    }

    public Exp getRight(){
        return getSubTree(1);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpConcat(this, arg);
    }

    @Override
    public String toString() {
        return getLeft() +" @ "+ getRight();
    }
    
}
