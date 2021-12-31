import java.util.ArrayList;

public class ExpList extends Exp{
    ArrayList<Exp> listcon;
    public ExpList(ArrayList<Exp> args){
        super("list of objects",args);
        listcon=args;
    }

    public ExpList(){
        super("list of objects",new ArrayList<>());
        listcon=new ArrayList<>();
    }

    public ArrayList<Exp> getList() {
        return listcon;
    }
    

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws VisitException, NoSuchMethodException {
        return v.visitExpList(this, arg);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}
