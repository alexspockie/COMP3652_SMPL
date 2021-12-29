import java.util.ArrayList;
public class ExpIfThen extends Exp {

	ArrayList<Exp> args;
    ExpCompare c;

    public ExpIfThen(ArrayList<Exp> args, ExpCompare c) {
	super(c.toString(), args);
	this.c = c;
    this.args = args;
    }

    public String toString() {
        return "call";
    }

    public ExpCompare getLog(){
        return this.c;
    }

    public ArrayList<Exp> getArgs(){
        return this.args;
    }


    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException, NoSuchMethodException {
	return v.visitExpIfThen(this, arg);
    }

}
