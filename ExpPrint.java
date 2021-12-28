public class ExpPrint extends Exp {
    Exp expression;
    String printtype;
    public ExpPrint(Exp e, String ptype){
        super("print");
        expression=e;
        printtype=ptype;
    }
    public Exp getExp(){
        return expression;
    }
    public String getType(){
        return printtype;
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
      
        return v.visitPrint(this, arg);
    }

    @Override
    public String toString() {
       
        return null;
    }
    
}
