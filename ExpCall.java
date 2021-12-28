public class ExpCall extends Exp{
    ExpProcedure procedure;
    SMPLList args;

    public ExpCall(ExpProcedure proc, SMPLList lst){
        super("call proc on", proc);
        procedure=proc;
        args=lst;
    }
    public SMPLList getLst(){
        return args;
    }
    public ExpProcedure getProc(){
        return procedure;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpCall(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
