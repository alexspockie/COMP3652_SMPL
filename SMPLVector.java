import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents vectors
 */
public class SMPLVector extends SMPLDataType<ArrayList<SMPLDataType>> {

    public SMPLVector(ArrayList<SMPLDataType> data) {
        super(data, true);
    }

    public SMPLVector(SMPLDataType ... data) {
        super(new ArrayList<>(Arrays.asList(data)), true);
    }
}
    

