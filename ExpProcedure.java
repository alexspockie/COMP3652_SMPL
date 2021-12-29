import java.util.ArrayList;

public class ExpProcedure extends Exp {
    String var;
    ArrayList<String> params;
    StmtSequence statements;
    Exp expression;

    public ExpProcedure(ArrayList<String> pe, StmtSequence s){
        //constructor
        super("procedure", s);
        //var = id;
        params = pe;
        statements = s;
    }
    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpProcedure(this, arg);
        }
    public String toString(){
        return "";
    }
    public ExpProcedure(ArrayList<String> pe, Exp e) {
        super("procedure", e);
        params = pe;
        expression = e;
    }
   
    
        /*public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException {
        return v.visitStmtFunDefn(this, arg);
        }*/
    
        public ArrayList<String> getParams(){
            return this.params;
        }
    
        public StmtSequence getStatements(){
            return this.statements;
        }
    
        public Exp getExpression(){
            return this.expression;
        }
}
