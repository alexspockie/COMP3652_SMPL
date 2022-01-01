import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents lists
 * 
 * 
 * Not sure if this actually needs to be implemented as a list of SMPLPairs as described
 */
public class SMPLList extends SMPLDataType<ArrayList<SMPLDataType>> {

    public SMPLList(ArrayList<SMPLDataType> data) {
        super(data,true);
    }

    public SMPLList(SMPLDataType ... data) {
        super(new ArrayList<>(Arrays.asList(data)), true);
    }

    @Override
    public SMPLList concat(SMPLDataType o) throws NoSuchMethodException {
        if (o.getClass() == SMPLList.class) {
            data.addAll(SMPLList.class.cast(o).getValue());
            return this;
        }else{
            throw new NoSuchMethodException(o.toTag() + " is not a list");
        }
    }
    
}
    

