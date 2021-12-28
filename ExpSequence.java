import java.util.ArrayList;

public class ExpSequence extends Exp{
 

    ArrayList<Exp> seq;
    public ExpSequence(ArrayList<Exp> exps){
        super("exp sequence");
        seq=exps;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
       
        return v.visitExpSequence(this, arg);
    }
    public ArrayList<Exp> getExps(){
        return seq;
    }

    @Override
    public String toString() {
     
        return null;
    }
    
}
