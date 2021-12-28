public class ExpRead extends Exp {
    String read_type;
    public ExpRead(String readtype){
        super(readtype);
        read_type=readtype;
    }
    public String getReadType(){
        return read_type;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        // TODO Auto-generated method stub
        return v.visitRead(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
