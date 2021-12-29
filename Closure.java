
/**
 * Runtime representation for a procedure object (under static scoping)
 * @param T the type of values stored in the closing environment.
 */
public class Closure {
   // private StmtFunDefn function; //stmtfundef no longer used in parser
    private ExpProcedure proc;
    private Environment closingEnv;
/*
    public Closure(StmtFunDefn fun, Environment env) {
	function = fun;
	closingEnv = env;
    }*/
    public Closure(ExpProcedure prc,Environment env){
        proc=prc;
        closingEnv=env;
    }

    /*public StmtFunDefn getFunction() {
	return function;
    }*/
    public ExpProcedure getProc(){
        return proc;
    }

    public Environment getClosingEnv() {
	return closingEnv;
    }
}
