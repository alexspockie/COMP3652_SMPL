import java.util.ArrayList;
public class MultiValExp extends Exp{
    ArrayList<Exp> seq;
    public MultiValExp(ArrayList<Exp> lst){
        super("multival expression");
        seq=lst;
    }
    public ArrayList<Exp> getExps(){
        return seq;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        // TODO Auto-generated method stub
        return v.visitMultiExp(this, arg);
    }

    @Override
    public String toString() {
      return null;
    }
    
}
