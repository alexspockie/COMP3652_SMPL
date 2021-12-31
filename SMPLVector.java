import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents vectors
 */
public class SMPLVector extends SMPLDataType<ArrayList<? extends SMPLDataType>> {

    public SMPLVector(ArrayList<? extends SMPLDataType> data) {
        super(data);
        isCompound = true;
    }

    public SMPLVector(SMPLDataType ... data) {
        super(new ArrayList<>(Arrays.asList(data)));
    }
}
    

