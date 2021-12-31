import java.util.ArrayList;

public class ExpLitBool extends Exp {
    
    private Boolean val;

    protected ExpLitBool(boolean constant) {
        super("boolean");
        val = constant;
    }

    /**
     * @return the val
     */
    public Boolean getVal() {
        return val;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {

        return v.visitExpLitBool(this, arg);
    }

    @Override
    public String toString() {
        return val.toString();
    }

    
    
}
