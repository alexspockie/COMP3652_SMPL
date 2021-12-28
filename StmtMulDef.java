import java.util.ArrayList;

public class StmtMulDef extends Statement {
ArrayList<String> ids;
Exp expression;
    public StmtMulDef(ArrayList<String> var,Exp e) {
        super(e);
       ids=var;
       expression=e;
    }
    public ArrayList<String> getIds(){
        return ids;
    }
    public Exp getExp(){
        return expression;
    }
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException,NoSuchMethodException  {
        return v.visitStmtMulDef(this, arg);
        }
    
}
