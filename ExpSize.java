public class ExpSize extends Exp {
    Exp size;
    Exp consequent;
    public ExpSize(Exp pred,Exp cons){//changed from ExpCompare
        super("clause of a case",cons);
        this.size=pred;
        this.consequent=cons;

    }
    public Exp getSize(){//changed from ExpCompare
        return size;
    }
    public Exp getCons(){
        return consequent;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpSize(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
