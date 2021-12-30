public class ExpCall extends Exp{
    ExpProcedure procedure;
    ExpVar id,l;
    SMPLList args;

    public ExpCall(ExpProcedure proc, SMPLList lst){
        super("call proc on", proc);
        procedure=proc;
        args=lst;
        id=null;
        l=null;
    }
    public ExpCall(ExpVar v,SMPLList lst){
        super("call proc on", v);
        procedure=null;
        args=lst;
        id=v;
        l=null;
    }
    public ExpCall(ExpVar v,ExpVar lst){
        super("call proc on",v);
        this.l=lst;
        procedure=null;
        args=null;
        id=v;

    }
    public Boolean hasVar(){ //for function id
        return id!=null;
    }
    public Boolean hasLVar(){//is list given as an variable
        return l!=null;
    }
    public ExpVar getId(){
        return id;
    }
    public SMPLList getLst(){
        return args;
    }
    public ExpVar getL(){//return variable for list
        return l;
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
