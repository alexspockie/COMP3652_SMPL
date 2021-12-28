import java.util.*;

public class Evaluator implements Visitor<Environment, SMPLDataType> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected SMPLDataType result;	// result of evaluation
    private SMPLDataType defaultValue;

    protected Evaluator() {
	this(new SMPLFloat(Double.NaN));
    }

    public Evaluator(SMPLDataType defaultVal) {
	// perform initialisations here
	this.defaultValue = defaultVal;
	result = defaultValue;
    }

    public Environment getDefaultState() {
	return Environment.makeGlobalEnv();
    }

    public SMPLDataType visitArithProgram(ArithProgram p, Environment env)
	throws VisitException {
	result = p.getSeq().visit(this, env);
	return result;
    }

    public SMPLDataType visitStatement(Statement s, Environment env)
    throws VisitException, NoSuchMethodException {
	return s.getExp().visit(this, env);
    }

    public SMPLDataType visitStmtSequence(StmtSequence sseq, Environment env)
	throws VisitException , NoSuchMethodException{
	// remember that env is the environment
	Statement s;
	ArrayList seq = sseq.getSeq();
	Iterator iter = seq.iterator();
	SMPLDataType result = defaultValue;
	while(iter.hasNext()) {
	    s = (Statement) iter.next();
	    result = s.visit(this, env);
	}
	// return last value evaluated
	return result;
    }

    public SMPLDataType visitStmtDefinition(StmtDefinition sd,
				      Environment env)
	throws VisitException, NoSuchMethodException{
	SMPLDataType result;
	result = sd.getExp().visit(this, env);
	env.put(sd.getVar(), result);
	return result;
    }

    /*public SMPLDataType visitStmtFunDefn(StmtFunDefn fd, Environment env)
	throws VisitException, NoSuchMethodException {
	// to be implemented
		Closure close = new Closure(fd,env);
    	env.put(fd.getVar(),new SMPLProcedure(close));
		return new SMPLFloat(0D);// add return type 
    }*/
	public SMPLDataType visitExpProcedure(ExpProcedure proc, Environment env)
	throws VisitException, NoSuchMethodException{
		return new SMPLFloat(0d); //fix
	}

    public SMPLDataType visitExpFunCall(ExpFunCall fc, Environment env)
	throws VisitException, NoSuchMethodException {
	// to be implemented
		Closure close = SMPLProcedure.class.cast(env.get(fc.getVar())).getValue();
    	StmtFunDefn fun = close.getFunction();
    	ArrayList<String> params = fun.getParams();
    	ArrayList<SMPLDataType> vals = new ArrayList<SMPLDataType>();
    	ArrayList<Exp> args = fc.getArgList();
    	for (int i=0;i<args.size();i++){
    		vals.add(args.get(i).visit(this,env));
    	}
    	Environment env2 = new Environment(close.getClosingEnv(),params,vals);
    	if (fun.getStatements() == null)
    		return fun.getExpression().visit(this,env2);
    	else 
    		return fun.getStatements().visit(this,env2);
    }

    public SMPLDataType visitExpCompare(ExpCompare exp, Environment env)
	throws VisitException, NoSuchMethodException{
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	/*if (exp.getC().apply(val1,val2).getValue())//???
		//return new SMPLFloat(1D); //possuble change to SMPL Boolean
		return new SMPLBoolean(true);
	//return new SMPLFloat(0D);
	return new SMPLBoolean(false);*/
	return exp.getC().apply(val1,val2);

    }

    public SMPLDataType visitExpIfThen(ExpIfThen exp, Environment env)
	throws VisitException, NoSuchMethodException{
	if (exp.getLog().visit(this,env).relationalCmp(Cmp.EQ, new SMPLInt(1)).getValue())
		return exp.getArgs().get(0).visit(this, env);
	if (exp.getArgs().size()>1)
		return exp.getArgs().get(1).visit(this, env);
	return new SMPLFloat(0D);

    }

    public SMPLDataType visitExpAdd(ExpAdd exp, Environment env)
	throws VisitException, NoSuchMethodException{
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.add(val2);	
	}

    public SMPLDataType visitExpSub(ExpSub exp, Environment env)
	throws VisitException, NoSuchMethodException{
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
		return val1.subtract(val2);
    }

    public SMPLDataType visitExpMul(ExpMul exp, Environment env)
	throws VisitException, NoSuchMethodException {
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.multiply(val2);
    }

    public SMPLDataType visitExpDiv(ExpDiv exp, Environment env)
	throws VisitException, NoSuchMethodException {
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.divide(val2);
    }

    public SMPLDataType visitExpMod(ExpMod exp, Environment env)
	throws VisitException, NoSuchMethodException {
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.mod(val2);
    }

    public SMPLDataType visitExpLit(ExpLitInt exp, Environment env)//Returns an SMPL Integer
	throws VisitException, NoSuchMethodException {
	SMPLDataType val1;
	val1=new SMPLInt(exp.getVal());
	return val1;
    }

	public SMPLDataType visitExpDouble(ExpLitDouble exp, Environment env) //returns an SMPL Float
	throws VisitException, NoSuchMethodException {
	SMPLDataType val1;
	val1=new SMPLFloat(exp.getVal());
	return val1;
    }

    public SMPLDataType visitExpVar(ExpVar exp, Environment env)
	throws VisitException , NoSuchMethodException{
	return env.get(exp.getVar()); //might need to change into an SMPL String
    }
}
//might possibly need to add Booleans
