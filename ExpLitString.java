import java.util.ArrayList;

public class ExpLitString extends Exp {

    private String str;

    protected ExpLitString(String val) {
        super("string");
        str = val;
    }
    
    public String getVal(){
        return str;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpLitString(this,arg);
    }

    @Override
    public String toString() {
        return String.format("<%s> %s", getName(), str);
    }
    
}
