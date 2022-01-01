import java.util.*;

public class ToScheme implements Visitor<Void, String> {
    //this is our evaluator class
    String result;

    public ToScheme() {
	result = "";
    }

    public String getResult() {
	return result;
    }

    public Void getDefaultState() {
	return null;
    }

    // program
    public String visitArithProgram(ArithProgram p, Void arg)
	throws VisitException,NoSuchMethodException {
	result = (String) p.getSeq().visit(this, arg);
	return result;
    }

    // statements
    public String visitStatement(Statement stmt, Void arg)
	throws VisitException,NoSuchMethodException {
	return stmt.getExp().visit(this, arg);
    }

    public String visitStmtSequence(StmtSequence exp, Void arg)
	throws VisitException,NoSuchMethodException {
	
	ArrayList stmts = exp.getSeq();
	if (stmts.size() == 1)
	    return ((Statement) stmts.get(0)).visit(this,
						    arg);
	else {
	    Iterator iter = stmts.iterator();
	    String result = "(begin ";
	    Statement stmt;
	    while (iter.hasNext()) {
		stmt = (Statement) iter.next();
		result += (String) stmt.visit(this, arg) +
		    "\n";
	    }
	    result += ")";
	    return result;
	}
    }

    public String visitStmtDefinition(StmtDefinition sd, Void arg)
	throws VisitException, NoSuchMethodException {
	String valExp = (String) sd.getExp().visit(this,
						   arg);
	return "(define " + sd.getVar() + " " +
	    valExp + ")";
    }

    public String visitStmtFunDefn(StmtFunDefn fd, Void env)
	throws VisitException {
	// to be implemented
	return "";
    }

    public String visitExpFunCall(ExpFunCall fc, Void env)
	throws VisitException {
	// to be implemented
	return "";
    }

    public String visitExpCompare(ExpCompare exp, Void arg)
	throws VisitException, NoSuchMethodException{
	String left = exp.getExpL().visit(this, arg);
	String right = exp.getExpR().visit(this, arg);
	return "("+exp.getName()+" " + left + " " + right + ")";
    }
    public String visitExpComp(ExpComp exp, Void arg)
	throws VisitException, NoSuchMethodException{
		//to be implemented
	return "";
    }

    public String visitExpSize(ExpSize exp, Void arg)
	throws VisitException, NoSuchMethodException{
		//to be implemented
	return "";
    }

    public String visitExpIfThen(ExpIfThen exp, Void env)
	throws VisitException {
	// to be implemented
	return "";
    }

    // expressions
    public String visitExpAdd(ExpAdd exp, Void arg)
	throws VisitException, NoSuchMethodException {
	String left = exp.getExpL().visit(this, arg);
	String right = exp.getExpR().visit(this, arg);
	return "(+ " + left + " " + right + ")";
    }
    public String visitExpSub(ExpSub exp, Void arg)
	throws VisitException, NoSuchMethodException {
	String left = exp.getExpL().visit(this, arg);
	String right = exp.getExpR().visit(this, arg);
	return "(- " + left + " " + right + ")";
    }

    public String visitExpMul(ExpMul exp, Void arg)
	throws VisitException , NoSuchMethodException{
	String left = exp.getExpL().visit(this, arg);
	String right = exp.getExpR().visit(this, arg);
	return "(* " + left + " " + right + ")";
    }

    public String visitExpDiv(ExpDiv exp, Void arg)
	throws VisitException , NoSuchMethodException{
	String left = exp.getExpL().visit(this, arg);
	String right = exp.getExpR().visit(this, arg);
	return "(/ " + left + " " + right + ")";
    }

    public String visitExpMod(ExpMod exp, Void arg)
	throws VisitException, NoSuchMethodException{
	String left = exp.getExpL().visit(this, arg);
	String right = exp.getExpR().visit(this, arg);
	return "(mod " + left + " " + right + ")";
    }
    public String visitExpPow(ExpPow exp, Void arg)
	throws VisitException, NoSuchMethodException {
	String left = exp.getExpL().visit(this, arg);
	String right = exp.getExpR().visit(this, arg);
	return "(^ " + left + " " + right + ")";
    }

    public String visitExpBit(ExpBit exp, Void env)
	throws VisitException {
	// to be implemented
	return "";
    }

    public String visitExpLit(ExpLitInt exp, Void arg)
	throws VisitException, NoSuchMethodException{
	return "" + exp.getVal();
    }

    public String visitExpVar(ExpVar exp, Void arg)
	throws VisitException, NoSuchMethodException {
	return exp.getVar();
    }

	@Override
	public String visitStmtExpDefn(StmtExpDefn proc, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpBind(ExpBind bind, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpProcedure(ExpProcedure proc, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpCClause(ExpCClause clause, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpCall(ExpCall call, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpLet(ExpLet let, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpCase(ExpCase ecase, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpSequence(ExpSequence seq, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitMultiExp(MultiValExp exp, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitStmtMulDef(StmtMulDef muldef, Void exp) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitRead(ExpRead read, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitPrint(ExpPrint print, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpDouble(ExpLitDouble exp, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpList(ExpList lst, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpVector(ExpVector vec, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpVecCall(ExpVecCall vc, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpPair(ExpPair expPair, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpCar(ExpCar expCar, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpCdr(ExpCdr expCdr, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpGetSize(ExpGetSize expGetSize, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpIsEq(ExpIsEq expIsEq, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpIsEqv(ExpIsEqv expIsEqv, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpIsPair(ExpIsPair expIsPair, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpSubstr(ExpSubstr expSubstr, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpLitString(ExpLitString expLitString, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpNegate(ExpNegate exp, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpLitBool(ExpLitBool exp, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpLitChar(ExpLitChar exp, Void arg) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitExpConcat(ExpConcat exp, Void env) throws VisitException, NoSuchMethodException {
		// TODO Auto-generated method stub
		return null;
	}

}
