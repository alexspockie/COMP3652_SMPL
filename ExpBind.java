public class ExpBind extends Exp{
    String id;
    Exp expression;
    public ExpBind(String var, Exp e){
        super(var,e);
        this.id=var;
        this.expression=e;
    }
    public Exp getExp(){
        return this.expression;
    }
    public String getId(){
        return this.id;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
      
        return v.visitExpBind(this,arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
