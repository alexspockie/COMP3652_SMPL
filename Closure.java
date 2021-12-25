
/**
 * Runtime representation for a procedure object (under static scoping)
 * @param T the type of values stored in the closing environment.
 */
public class Closure {
    private StmtFunDefn function;
    private Environment closingEnv;

    public Closure(StmtFunDefn fun, Environment env) {
	function = fun;
	closingEnv = env;
    }

    public StmtFunDefn getFunction() {
	return function;
    }

    public Environment getClosingEnv() {
	return closingEnv;
    }
}
