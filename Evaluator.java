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
	throws VisitException, NoSuchMethodException {
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
	result = sd.getExp().visit(this, env); //get expressioon return SMPL data type
	env.put(sd.getVar(), result);
	return result;
    }
	@Override
	public SMPLDataType visitStmtExpDefn(StmtExpDefn proc, Environment arg)
			throws VisitException, NoSuchMethodException {
		if (proc.isProc()){
				//create a closure with proc
		Closure close=new Closure(proc.getProc(),arg); 
		arg.put(proc.getVar(),new SMPLProcedure(close));
		//add closure to environment return nothing		

		}
		//do this for other expressions
		//do this for procedures
	
		return new SMPLFloat(0d);
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
		//return new SMPLFloat(0d); //fix
		return new SMPLInt(0); //need to return an smpl data type
	}

    public SMPLDataType visitExpFunCall(ExpFunCall fc, Environment env)
	throws VisitException, NoSuchMethodException {//uses SMPLProcedure
	// to be implemented
		Closure close = SMPLProcedure.class.cast(env.get(fc.getVar())).getValue();
    	StmtFunDefn fun = close.getFunction(); //no longer have stmt fun def so we need to change
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
	throws VisitException, NoSuchMethodException{ //X
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	if (exp.getC().apply(val1,val2).getValue())
		return new SMPLFloat(1D); //
		//return new SMPLBoolean(true);
	return new SMPLFloat(0D);
	//return new SMPLBoolean(false);*/
	//return exp.getC().apply(val1,val2); //return SMPLBoolean

    }

    public SMPLDataType visitExpIfThen(ExpIfThen exp, Environment env)
	throws VisitException, NoSuchMethodException{ //X
	if (exp.getLog().visit(this,env).relationalCmp(Cmp.EQ, new SMPLInt(1)).getValue()) //get ExpCompare, visits it
		return exp.getArgs().get(0).visit(this, env);
	if (exp.getArgs().size()>1)
		return exp.getArgs().get(1).visit(this, env);
	return new SMPLFloat(0D); //this would return something 

    }

    public SMPLDataType visitExpAdd(ExpAdd exp, Environment env)
	throws VisitException, NoSuchMethodException{ //X
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.add(val2);	
	}

    public SMPLDataType visitExpSub(ExpSub exp, Environment env)
	throws VisitException, NoSuchMethodException{ //X
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
		return val1.subtract(val2);
    }

    public SMPLDataType visitExpMul(ExpMul exp, Environment env)
	throws VisitException, NoSuchMethodException { //X
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.multiply(val2);
    }

    public SMPLDataType visitExpDiv(ExpDiv exp, Environment env)
	throws VisitException, NoSuchMethodException { //X
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.divide(val2);
    }

    public SMPLDataType visitExpMod(ExpMod exp, Environment env)
	throws VisitException, NoSuchMethodException { //X
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.mod(val2);
    }

    public SMPLDataType visitExpLit(ExpLitInt exp, Environment env)//Returns an SMPL Integer X
	throws VisitException, NoSuchMethodException {
	SMPLDataType val1;
	val1=new SMPLInt(exp.getVal());
	return val1;
    }

	public SMPLDataType visitExpDouble(ExpLitDouble exp, Environment env) //returns an SMPL Float X
	throws VisitException, NoSuchMethodException {
	SMPLDataType val1;
	val1=new SMPLFloat(exp.getVal());
	return val1;
    }

    public SMPLDataType visitExpVar(ExpVar exp, Environment env)//X
	throws VisitException , NoSuchMethodException{
	return env.get(exp.getVar()); 
	//we get a string from the expression and use it to get whatever is in the environment
    }

	
	@Override
	public SMPLDataType visitExpBind(ExpBind bind, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitExpCClause(ExpCClause clause, Environment arg)
			throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitExpCall(ExpCall call, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitExpLet(ExpLet let, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitExpCase(ExpCase ecase, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitExpSequence(ExpSequence seq, Environment arg)
			throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitMultiExp(MultiValExp exp, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitStmtMulDef(StmtMulDef muldef, Environment exp)
			throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitRead(ExpRead read, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SMPLDataType visitPrint(ExpPrint print, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}
}
//might possibly need to add Booleans
