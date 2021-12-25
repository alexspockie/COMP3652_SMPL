import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents pairs in SMPL
 */
public class SMPLPair extends SMPLDataType<ArrayList<? extends SMPLDataType>>{

    public SMPLPair(SMPLDataType first, SMPLDataType second){
        super(new ArrayList<>(Arrays.asList(first, second)));
    }
}
