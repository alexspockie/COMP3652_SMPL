import java.util.ArrayList;

public class ExpVector extends Exp{
    ArrayList<Exp> lst;
    public ExpVector(ArrayList<Exp> args){
        super("vector");
        lst=args;
    }
    public ArrayList<Exp> getArgs(){
        return lst;
    }
    
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
     return v.visitExpVector(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
