import java.util.ArrayList;
import java.util.Arrays;

abstract public class SMPLOrdered extends SMPLDataType<ArrayList<SMPLDataType>>{

    public SMPLOrdered(ArrayList<SMPLDataType> data) {
        super(data, true);
    }
    
    public SMPLOrdered(SMPLDataType ... data) {
        super(new ArrayList<>(Arrays.asList(data)), true);
    }
}
