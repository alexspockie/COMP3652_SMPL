import java.util.ArrayList;

public class ExpLet extends Exp {
    ArrayList<ExpBind> binding;
    StmtSequence body;
    public ExpLet(ArrayList<ExpBind> binds, StmtSequence bod ){
        super("bind",bod);
        binding=binds;
        body=bod;
    }

    public ArrayList<ExpBind> getBinds(){
        return binding;
    }
    public StmtSequence getBody(){
        return body;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
   
        return v.visitExpLet(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
}
