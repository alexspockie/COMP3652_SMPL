import java.util.ArrayList;

public class ExpCase extends Exp {
    ArrayList<ExpCClause> predicates;
    public ExpCase(ArrayList<ExpCClause> pred){
        super("case");
        predicates=pred;
    }
    public ArrayList<ExpCClause> getPred(){
        return predicates;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
    
        return v.visitExpCase(this, arg);
    }

    @Override
    public String toString() {
    
        return null;
    }
    
}
