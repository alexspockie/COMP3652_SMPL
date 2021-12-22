import java.util.ArrayList;
/**
 * IR Class to represent a function call
 */
public class ExpFunCall extends Exp {
    
    // Implement this class
    String var;
    ArrayList<Exp> argList;

    public ExpFunCall(String id, ArrayList<Exp> args) {	// placeholder; can be removed eventually
	super(id,args);
	var = id;
	argList = args;
    }

    public String toString() {
		return "call";
    }

    public String getVar(){
    	return this.var;
    }

    public ArrayList<Exp> getArgList(){
    	return this.argList;
    }

    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException {
	return v.visitExpFunCall(this, arg);
    }

}

