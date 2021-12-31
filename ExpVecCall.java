public class ExpVecCall extends Exp {
    ExpVar id;
    ExpVector vec;
    Exp num;
    
    public ExpVecCall(ExpVar name, Exp place){
        super("vector call",name);
        id=name;
        num=place;
        vec=null;
    }
    public ExpVecCall(ExpVector v, Exp place){
        super("vector call v2",place);
        vec=v;
        place=num;
    }
    public ExpVector getVec(){
        return vec;
    }
    public Boolean hasVector(){
        return vec!=null;
    }
    public ExpVar getId(){
        return id;
    }
    public Exp getNum(){
        return num;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpVecCall(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
