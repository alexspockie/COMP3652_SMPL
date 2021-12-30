import java.io.Reader;
import java.io.StringReader;
import lib3652.util.Interpreter;
import lib3652.util.ResultType;
import lib3652.util.Result;
import lib3652.util.TokenException;

public class ArithInterpreter extends AssessmentVisitor<Environment,
							 SMPLDataType> {
    /**
     * Create a new Arithmetic Interpreter with a default global environment.
     */ 
    public ArithInterpreter() {
	super(new Evaluator(new SMPLFloat(0D)));
    }

    public Result toResult(SMPLDataType r) {
	return new Result(ResultType.V_REAL, r);
    }
}
