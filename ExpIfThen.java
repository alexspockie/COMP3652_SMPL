import java.util.ArrayList;
public class ExpIfThen extends Exp {

	ArrayList<Exp> args;
    Exp c;//changed from ExpCompare

    public ExpIfThen(ArrayList<Exp> args, Exp c) {
	super(c.toString(), args);
	this.c = c;
    this.args = args;
    }

    public String toString() {
        return "call";
    }

    public Exp getLog(){//changed from ExpCompare
        return this.c;
    }

    public ArrayList<Exp> getArgs(){
        return this.args;
    }


    public <S, T> T visit(Visitor<S,T> v, S arg) throws VisitException, NoSuchMethodException {
	return v.visitExpIfThen(this, arg);
    }

}
