public class ExpCar extends Exp{

    protected ExpCar(Exp exp) {
        super("car", exp);
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpCar(this, arg);
    }

    @Override
    public String toString() {
        return "<" + getName() + ">" + getSubTree(0).toString();
    }
    
}
