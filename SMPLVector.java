import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents vectors
 */
public class SMPLVector extends SMPLOrderedMutable {

    public SMPLVector(ArrayList<SMPLDataType> data) {
        super(data);
    }

    public SMPLVector(SMPLDataType ... data) {
        super(new ArrayList<>(Arrays.asList(data)));
    }

    @Override
    public SMPLList concat(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Vectors have fixed sizes");
    }
}
    

