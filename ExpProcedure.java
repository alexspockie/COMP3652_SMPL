import java.util.ArrayList;

public class ExpProcedure extends Exp {
    String var;
    ArrayList<String> params;
    StmtSequence statements;
    Exp expression;
    ProcForm form;

    public ExpProcedure(ArrayList<String> pe, StmtSequence s, ProcForm form) {
        super("procedure", s);
        params = pe;
        statements = s;
        this.form = form;
    }

    public ExpProcedure(ArrayList<String> pe, Exp e, ProcForm form) {
        super("procedure", e);
        params = pe;
        expression = e;
        this.form = form;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpProcedure(this, arg);
    }

    public String toString() {
        return "";
    }

    /**
     * @return the form
     */
    public ProcForm getForm() {
        return form;
    }

    public ArrayList<String> getParams() {
        return this.params;
    }

    public StmtSequence getStatements() {
        return this.statements;
    }

    public Exp getExpression() {
        return this.expression;
    }
}
