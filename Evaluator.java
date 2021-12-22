import java.util.*;

public class Evaluator implements Visitor<Environment<Double>, Double> {
    /* For this visitor, the argument passed to all visit
       methods will be the environment object that used to
       be passed to the eval method in the first style of
       implementation. */

    // allocate state here
    protected Double result;	// result of evaluation
    private Double defaultValue;
    private Class<Double> myClass;

    protected Evaluator() {
	this(Double.NaN);
    }

    public Evaluator(Double defaultVal) {
	// perform initialisations here
	this.defaultValue = defaultVal;
	myClass = Double.class;
	result = defaultValue;
    }

    public Environment<Double> getDefaultState() {
	return Environment.makeGlobalEnv(myClass);
    }

    public Double visitArithProgram(ArithProgram p, Environment<Double> env)
	throws VisitException {
	result = p.getSeq().visit(this, env);
	return result;
    }

    public Double visitStatement(Statement s, Environment<Double> env)
    throws VisitException {
	return s.getExp().visit(this, env);
    }

    public Double visitStmtSequence(StmtSequence sseq, Environment<Double> env)
	throws VisitException {
	// remember that env is the environment
	Statement s;
	ArrayList seq = sseq.getSeq();
	Iterator iter = seq.iterator();
	Double result = defaultValue;
	while(iter.hasNext()) {
	    s = (Statement) iter.next();
	    result = s.visit(this, env);
	}
	// return last value evaluated
	return result;
    }

    public Double visitStmtDefinition(StmtDefinition sd,
				      Environment<Double> env)
	throws VisitException {
	Double result;
	result = sd.getExp().visit(this, env);
	env.put(sd.getVar(), result);
	return result;
    }

    public Double visitStmtFunDefn(StmtFunDefn fd, Environment<Double> env)
	throws VisitException {
	// to be implemented
		Closure close = new Closure(fd,env);
    	env.addClosure(fd.getVar(),close);
	return 0D;
    }

    public Double visitExpFunCall(ExpFunCall fc, Environment<Double> env)
	throws VisitException {
	// to be implemented
		Closure close = env.getClosure(fc.getVar());
    	StmtFunDefn fun = close.getFunction();
    	ArrayList<String> params = fun.getParams();
    	ArrayList<Double> vals = new ArrayList<Double>();
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

    public Double visitExpCompare(ExpCompare exp, Environment<Double> env)
	throws VisitException {
	Double val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	if (exp.getC().apply(val1,val2))
		return 1D;
	return 0D;

    }

    public Double visitExpIfThen(ExpIfThen exp, Environment<Double> env)
	throws VisitException {
	if (exp.getLog().visit(this,env) == 1)
		return exp.getArgs().get(0).visit(this, env);
	if (exp.getArgs().size()>1)
		return exp.getArgs().get(1).visit(this, env);
	return 0D;

    }

    public Double visitExpAdd(ExpAdd exp, Environment<Double> env)
	throws VisitException {
	Double val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1 + val2;
	}

    public Double visitExpSub(ExpSub exp, Environment<Double> env)
	throws VisitException {
	Double val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1 - val2;
    }

    public Double visitExpMul(ExpMul exp, Environment<Double> env)
	throws VisitException {
	Double val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1 * val2;
    }

    public Double visitExpDiv(ExpDiv exp, Environment<Double> env)
	throws VisitException {
	Double val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1 / val2;
    }

    public Double visitExpMod(ExpMod exp, Environment<Double> env)
	throws VisitException {
	Double val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1 % val2;
    }

    public Double visitExpLit(ExpLit exp, Environment<Double> env)
	throws VisitException {
	return new Double(exp.getVal());
    }

    public Double visitExpVar(ExpVar exp, Environment<Double> env)
	throws VisitException {
	return env.get(exp.getVar());
    }
}
