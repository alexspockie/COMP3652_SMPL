import java.util.ArrayList;
import java.util.Arrays;

abstract public class SMPLOrderedImmutable extends SMPLOrdered {
    public SMPLOrderedImmutable(ArrayList<SMPLDataType> data) {
        super(data);
    }
    
    public SMPLOrderedImmutable(SMPLDataType ... data) {
        super(new ArrayList<>(Arrays.asList(data)));
    }

    @Override
    public SMPLList concat(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(getClass()+" are immutable");
    }
}
