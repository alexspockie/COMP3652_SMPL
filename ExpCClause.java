public class ExpCClause extends Exp {
    ExpCompare logExp;
    Exp consequent;
    public ExpCClause(ExpCompare pred,Exp cons){
        super("clause of a case",cons);
        this.logExp=pred;
        this.consequent=cons;

    }
    public ExpCClause(Exp cons){
        //this is for the else clause 
        super("else clause of a case",cons);
        this.consequent=cons;
        this.logExp=null; //so if the predicate is null then it is an esle clause
    }
    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpCClause(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
