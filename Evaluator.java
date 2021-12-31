import java.util.*;

public class Evaluator implements Visitor<Environment, SMPLDataType> {
	/*
	 * For this visitor, the argument passed to all visit
	 * methods will be the environment object that used to
	 * be passed to the eval method in the first style of
	 * implementation.
	 */

	// allocate state here
	protected SMPLDataType result; // result of evaluation
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
			throws VisitException, NoSuchMethodException {
		// remember that env is the environment
		Statement s;
		ArrayList seq = sseq.getSeq();
		Iterator iter = seq.iterator();
		SMPLDataType result = defaultValue;
		while (iter.hasNext()) {
			s = (Statement) iter.next();
			result = s.visit(this, env);
		}
		// return last value evaluated
		return result;
	}

	public SMPLDataType visitStmtDefinition(StmtDefinition sd,
			Environment env)
			throws VisitException, NoSuchMethodException {
		SMPLDataType result;
		result = sd.getExp().visit(this, env); // get expressioon return SMPL data type
		env.put(sd.getVar(), result);
		return result;
	}

	@Override
	public SMPLDataType visitStmtExpDefn(StmtExpDefn proc, Environment arg) // X
			throws VisitException, NoSuchMethodException {
		if (proc.isProc()) { // potentially change result type to T
			// create a closure with proc
			Closure close = new Closure(proc.getProc(), arg);
			arg.put(proc.getVar(), new SMPLProcedure(close));
			// add closure to environment return nothing
		} else {
			// get expression evaluate it
			// return result as variable
			Exp exp = proc.getExpression();
			SMPLDataType result = exp.visit(this, arg);
			arg.put(proc.getVar(), result);
		}

		return new SMPLFloat(0d);
	}

	/*
	 * public SMPLDataType visitStmtFunDefn(StmtFunDefn fd, Environment env)
	 * throws VisitException, NoSuchMethodException {
	 * // to be implemented
	 * Closure close = new Closure(fd,env);
	 * env.put(fd.getVar(),new SMPLProcedure(close));
	 * return new SMPLFloat(0D);// add return type
	 * }
	 */
	public SMPLDataType visitExpProcedure(ExpProcedure proc, Environment env)// X
			throws VisitException, NoSuchMethodException {
		// return new SMPLFloat(0d); //fix
		Closure close = new Closure(proc, env);
		return new SMPLProcedure(close); // FIX FOR ADDITIONAL forms of proc
	}

	public SMPLDataType visitExpFunCall(ExpFunCall fc, Environment env)
			throws VisitException, NoSuchMethodException {// uses SMPLProcedure
		// to be implemented
		Closure close = SMPLProcedure.class.cast(env.get(fc.getVar())).getValue();
		ExpProcedure fun = close.getProc(); // no longer have stmt fun def so we need to change

		ArrayList<String> params = fun.getParams();
		ArrayList<SMPLDataType> vals = new ArrayList<SMPLDataType>();
		ArrayList<Exp> args = fc.getArgList();
		for (int i = 0; i < args.size(); i++) {
			vals.add(args.get(i).visit(this, env));
		}
		Environment env2 = new Environment(close.getClosingEnv(), params, vals);
		if (fun.getStatements() == null)
			return fun.getExpression().visit(this, env2);
		else
			return fun.getStatements().visit(this, env2);
	}

	public SMPLDataType visitExpCompare(ExpCompare exp, Environment env)// X
			throws VisitException, NoSuchMethodException { // X
		SMPLDataType val1, val2;
		val1 = exp.getExpL().visit(this, env);
		val2 = exp.getExpR().visit(this, env);
		if (exp.getC().apply(val1, val2).getValue())
			return new SMPLFloat(1D); //
		// return new SMPLBoolean(true);
		return new SMPLFloat(0D);
		// return new SMPLBoolean(false);*/
		// return exp.getC().apply(val1,val2); //return SMPLBoolean

	}

	public SMPLDataType visitExpComp(ExpComp exp, Environment env)
			throws VisitException, NoSuchMethodException { // X
		SMPLDataType val1, val2;
		SMPLFloat t = new SMPLFloat(1D);
		SMPLFloat f = new SMPLFloat(0D);
		val1 = exp.getExpL().visit(this, env);
		if (exp.getC() == "NOT") {
			if (val1.relationalCmp(Cmp.EQ, new SMPLFloat(1D)).getValue())
				return new SMPLFloat(0D);
			return new SMPLFloat(1D);
		}
		val2 = exp.getExpR().visit(this, env);
		if (exp.getC() == "AND") {
			if ((val1.relationalCmp(Cmp.EQ, new SMPLFloat(1D)).getValue())
					&& (val2.relationalCmp(Cmp.EQ, new SMPLFloat(1D)).getValue()))
				return new SMPLFloat(1D); //
			return new SMPLFloat(0D);
		}
		if ((val1.relationalCmp(Cmp.EQ, new SMPLFloat(1D)).getValue())
				|| (val2.relationalCmp(Cmp.EQ, new SMPLFloat(1D)).getValue()))
			return new SMPLFloat(1D); //
		return new SMPLFloat(0D);
	}

	public SMPLDataType visitExpIfThen(ExpIfThen exp, Environment env)// X

			throws VisitException, NoSuchMethodException { // X
		// if (exp.getLog().visit(this,env).relationalCmp(Cmp.EQ, new
		// SMPLFloat(1D)).getValue()) //get ExpCompare, visits it
		if (exp.getLog().visit(this, env).relationalCmp(Cmp.EQ, new SMPLFloat(1D)).getValue()) // get ExpCompare, visits
																								// it
		{
			return exp.getArgs().get(0).visit(this, env);
		}
		if (exp.getArgs().size() > 1)
			return exp.getArgs().get(1).visit(this, env);
		return new SMPLFloat(0D); // this would return something

	}

	public SMPLDataType visitExpAdd(ExpAdd exp, Environment env)
			throws VisitException, NoSuchMethodException { // X
		SMPLDataType val1, val2;
		val1 = exp.getExpL().visit(this, env);
		val2 = exp.getExpR().visit(this, env);
		return val1.add(val2);
	}

	public SMPLDataType visitExpSub(ExpSub exp, Environment env)
			throws VisitException, NoSuchMethodException { // X
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

    public SMPLDataType visitExpPow(ExpPow exp, Environment env)
	throws VisitException, NoSuchMethodException { //X
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	val2 = exp.getExpR().visit(this, env);
	return val1.pow(val2);
    }

    public SMPLDataType visitExpBit(ExpBit exp, Environment env)
	throws VisitException, NoSuchMethodException { //X
	SMPLDataType val1, val2;
	val1 = exp.getExpL().visit(this, env);
	if (exp.getS() == "~")
		return exp.getC().apply(val1);
	val2 = exp.getExpR().visit(this, env);
	return exp.getC().apply(val1,val2);
    }

    public SMPLInt visitExpLit(ExpLitInt exp, Environment env)//Returns an SMPL Integer X
	throws VisitException, NoSuchMethodException {
	SMPLInt val1;
	val1=new SMPLInt(exp.getVal());
	return val1;
    }

	public SMPLFloat visitExpDouble(ExpLitDouble exp, Environment env) //returns an SMPL Float X
	throws VisitException, NoSuchMethodException {
	SMPLFloat val1;
	val1=new SMPLFloat(exp.getVal());
	return val1;
    }

    public SMPLDataType visitExpVar(ExpVar exp, Environment env)//X
	throws VisitException , NoSuchMethodException{
	return env.get(exp.getVar()); 
	//we get a string from the expression and use it to get whatever is in the environment
    }

	
	@Override
	public SMPLDataType visitExpBind(ExpBind bind, Environment arg) throws VisitException, NoSuchMethodException {// X
		Exp ep = bind.getExp();
		SMPLDataType result = ep.visit(this, arg);
		arg.put(bind.getId(), result);
		return new SMPLFloat(0d);
	}

	@Override
	public SMPLDataType visitExpCClause(ExpCClause clause, Environment arg) // X
			throws VisitException, NoSuchMethodException {
		if (clause.getLog() != null) {
			return clause.getLog().visit(this, arg);
		}
		return new SMPLFloat(0d);
	}

	@Override
	public SMPLDataType visitExpCall(ExpCall call, Environment arg) throws VisitException, NoSuchMethodException {
		ArrayList<? extends SMPLDataType> val;
		if (call.getL() == null) {
			val = (ArrayList<? extends SMPLDataType>) call.getLst().visit(this, arg).getValue();// arguments
		} else {
			// search environment for list
			val = (ArrayList<? extends SMPLDataType>) call.getL().visit(this, arg).getValue();// arguments
		}
		Closure close = SMPLProcedure.class.cast(call.getId().visit(this, arg)).getValue();// get closure
		ExpProcedure fun = close.getProc();// get procedure
		ArrayList<String> params = fun.getParams();

		Environment env2 = new Environment(close.getClosingEnv(), params, val);
		if (fun.getStatements() == null)
			return fun.getExpression().visit(this, env2);
		else
			return fun.getStatements().visit(this, env2);
	}

	@Override
	public SMPLDataType visitExpLet(ExpLet let, Environment arg) throws VisitException, NoSuchMethodException {// X

		// create a new environment
		Environment letenv = new Environment();
		letenv.setParent(arg);
		// evaluate bindings with new environment
		ArrayList<ExpBind> bindings = let.getBinds();
		for (int i = 0; i < bindings.size(); i++) {
			bindings.get(i).visit(this, letenv);
		}

		return let.getBody().visit(this, letenv); // might have to change to get an expression

	}

	@Override
	public SMPLDataType visitExpCase(ExpCase ecase, Environment arg) throws VisitException, NoSuchMethodException { // X
		// TODO Auto-generated method stub
		ArrayList<ExpCClause> clauses=ecase.getPred();
		for(int i=0;i<clauses.size();i++){
			if (clauses.get(i).isElse()==true || clauses.get(i).visit(this, arg).relationalCmp(Cmp.EQ, new SMPLInt(1)).getValue()){
				return clauses.get(i).getCons().visit(this,arg);
			}
		}
		return null;// should never reach here
	}

	@Override
	public SMPLDataType visitExpSequence(ExpSequence seq, Environment arg)
			throws VisitException, NoSuchMethodException { // x
		// evluate left to right return result of last
		ArrayList<Exp> lstexps = seq.getExps();
		for (int i = 0; i < lstexps.size(); i++) {
			SMPLDataType result = lstexps.get(i).visit(this, arg);
			if (i == (lstexps.size() - 1)) {
				return result;
			}
		}
		return null;
	}

	@Override
	public SMPLDataType visitMultiExp(MultiValExp exp, Environment arg) throws VisitException, NoSuchMethodException {
		ArrayList<Exp> exps = exp.getExps();
		ArrayList<SMPLDataType> result = new ArrayList<>();
		for (int i = 0; i < exps.size(); i++) {
			result.add(exps.get(i).visit(this, arg));
		}
		return new SMPLList(result); // prints a tuple of values?
		// return null;//return a list
	}

	@Override
	public SMPLDataType visitStmtMulDef(StmtMulDef muldef, Environment exp)
			throws VisitException, NoSuchMethodException {
		// extension
		return null;
	}

	@Override
	public SMPLDataType visitRead(ExpRead read, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		if(read.getReadType()=="string"){
			System.out.println("Enter string here:");
			SMPLString result=new SMPLString(sc.nextLine());
		}
		else{
			System.out.println("Enter int here:");
			SMPLInt result=new SMPLInt(sc.nextInt());
		}
		return result;
	}

	@Override
	public SMPLDataType visitPrint(ExpPrint print, Environment arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		Exp expr = print.getExp();
		SMPLDataType result = expr.visit(this, arg);
		if (print.getType() == "ln") {
			System.out.println(result.toString());
		} else {
			System.out.print(result.toString());
		}

		return new SMPLFloat(0d);
	}

	@Override
	public SMPLList visitExpList(ExpList lst, Environment arg) throws VisitException, NoSuchMethodException {
		ArrayList<Exp> args = lst.getList();
		SMPLDataType res2;
		ArrayList<SMPLDataType> flst = new ArrayList<>();
		for (int i = 0; i < args.size(); i++) {
			res2 = args.get(i).visit(this, arg);
			flst.add(res2);
		}
		return new SMPLList(flst);
	}

	public SMPLVector visitExpVector(ExpVector vec, Environment arg) throws VisitException, NoSuchMethodException {
		ArrayList<Exp> args = vec.getArgs();
		SMPLDataType res2;
		ArrayList<SMPLDataType> flst = new ArrayList<>();
		for (int i = 0; i < args.size(); i++) {
			res2 = args.get(i).visit(this, arg);
			flst.add(res2);
		}
		return new SMPLVector(flst);
	}

	public SMPLDataType visitExpVecCall(ExpVecCall vc, Environment arg) throws VisitException, NoSuchMethodException {
		ExpVar id = vc.getId();
		SMPLVector vec;
		if (!vc.hasVector()) {
			vec = (SMPLVector) id.visit(this, arg);
		} else {
			vec = new SMPLVector(vc.getVec().visit(this, arg));
		}

		ArrayList<? extends SMPLDataType> data = vec.getValue();
		Integer num = (Integer) vc.getNum().visit(this, arg).getValue();
		return data.get(num);
	}

	public SMPLDataType visitExpSize(ExpSize exp, Environment arg) throws VisitException, NoSuchMethodException {
		return new SMPLFloat(0d); // to be implemented
	}

	@Override
	public SMPLDataType visitExpPair(ExpPair expPair, Environment arg) throws VisitException, NoSuchMethodException {

		return new SMPLPair(expPair.getSubTree(0).visit(this, arg), expPair.getSubTree(1).visit(this, arg));
	}

	@Override
	public SMPLDataType visitExpCar(ExpCar expCar, Environment arg) throws VisitException, NoSuchMethodException {
		if (expCar.getSubTree(0).visit(this, arg).getClass() == SMPLPair.class) {
			SMPLPair p = SMPLPair.class.cast(expCar.getSubTree(0).visit(this, arg));
			return p.getValue().get(0);
		} else {
			throw new NoSuchMethodException("Builtin function car only accepts SMPL pairs");
		}
	}

	@Override
	public SMPLDataType visitExpCdr(ExpCdr expCdr, Environment arg) throws VisitException, NoSuchMethodException {
		if (expCdr.getSubTree(0).visit(this, arg).getClass() == SMPLPair.class) {
			SMPLPair p = SMPLPair.class.cast(expCdr.getSubTree(0).visit(this, arg));
			return p.getValue().get(1);
		} else {
			throw new NoSuchMethodException("Builtin function cdr only accepts SMPL pairs");
		}
	}

	@Override
	public SMPLDataType visitExpGetSize(ExpGetSize exp, Environment arg)
			throws VisitException, NoSuchMethodException {
		if (exp.getSubTree(0).visit(this, arg).getClass() == SMPLVector.class) {
			SMPLVector p = SMPLVector.class.cast(exp.getSubTree(0).visit(this, arg));
			return new SMPLInt(p.getValue().size());
		} else {
			throw new NoSuchMethodException("Builtin function size only accepts SMPL vectors");
		}
	}

	@Override
	public SMPLDataType visitExpIsEq(ExpIsEq exp, Environment arg) throws VisitException, NoSuchMethodException {
		SMPLDataType e1 = exp.getSubTree(0).visit(this, arg);
		SMPLDataType e2 = exp.getSubTree(1).visit(this, arg);

		if (e1.isCompound && e2.isCompound) {
			return new SMPLBoolean(e1.getValue() == e2.getValue());
		} else if (!e1.isCompound && !e2.isCompound) {
			return new SMPLBoolean(e1.getValue().equals(e2.getValue()));
		}else{
			throw new NoSuchMethodException(e1 + " and " + e2 + " cannot be compared for ");
		}
	}

	@Override
	public SMPLDataType visitExpIsEqv(ExpIsEqv exp, Environment arg) throws VisitException, NoSuchMethodException {
		SMPLDataType e1 = exp.getSubTree(0).visit(this, arg);
		SMPLDataType e2 = exp.getSubTree(1).visit(this, arg);

		if (e1.isCompound == true && e2.isCompound) {
			if ((e1.getClass() == SMPLPair.class && e2.getClass() == SMPLPair.class)
					|| (e1.getClass() == SMPLList.class && e2.getClass() == SMPLList.class)
					|| (e1.getClass() == SMPLVector.class && e2.getClass() == SMPLVector.class)) {
				return new SMPLBoolean(e1.getValue().equals(e2.getValue()));
			} else {
				throw new NoSuchMethodException(
								e1 + " and " + e2 + " cannot be compared for structural equivalence due to type mismatch");
			}

		} else if (!e1.isCompound && !e2.isCompound) {
			return new SMPLBoolean(e1.getValue().equals(e2.getValue()));
		} else {
			throw new NoSuchMethodException(e1 + " and " + e2 + " cannot be compared for structural equivalence");
		}
	}

	@Override
	public SMPLDataType visitExpIsPair(ExpIsPair exp, Environment arg)
			throws VisitException, NoSuchMethodException {
		return new SMPLBoolean(exp.getSubTree(0).visit(this, arg).getClass() == SMPLPair.class);
	}

	@Override
	public SMPLDataType visitExpSubstr(ExpSubstr exp, Environment arg)
			throws VisitException, NoSuchMethodException {
		try {
			SMPLString s = SMPLString.class.cast(exp.getSubTree(0).visit(this, arg));
			SMPLInt start = SMPLInt.class.cast(exp.getSubTree(1).visit(this, arg));
			SMPLInt end = SMPLInt.class.cast(exp.getSubTree(2).visit(this, arg));

			if (start.getValue() < 0 || start.getValue() >= s.getValue().length()) {
				throw new VisitException(
						"Start index out of range. Must be at least 0 and less than the length of the string");
			}

			if (end.getValue() > s.getValue().length()) {
				throw new VisitException(
						"End index out of range. Must be less than or equal to the length of the string");
			}

			if (start.getValue() > end.getValue()) {
				return new SMPLString("");
			}

			return new SMPLString(s.getValue().substring(start.getValue(), end.getValue()));

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public SMPLDataType visitExpLitString(ExpLitString exp, Environment arg)
			throws VisitException, NoSuchMethodException {
		return new SMPLString(exp.getVal());
	}

	@Override
	public SMPLDataType visitExpNegate(ExpNegate exp, Environment arg) throws VisitException, NoSuchMethodException {
		SMPLDataType val = exp.getSubTree(0).visit(this, arg);

		return val.negate();
	}
}
// might possibly need to add Booleans
