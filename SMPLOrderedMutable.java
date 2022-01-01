import java.util.ArrayList;
import java.util.Arrays;

abstract public class SMPLOrderedMutable extends SMPLOrdered {
    public SMPLOrderedMutable(ArrayList<SMPLDataType> data) {
        super(data);
    }
    
    public SMPLOrderedMutable(SMPLDataType ... data) {
        super(new ArrayList<>(Arrays.asList(data)));
    }
}
