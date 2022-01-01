import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SMPLTuple extends SMPLOrderedImmutable {

    public SMPLTuple(ArrayList<SMPLDataType> data) {
        super(data);
    }

    public SMPLTuple(SMPLDataType ... data) {
        super(new ArrayList<>(Arrays.asList(data)));
    }

    @Override
    public String toTag() {
        return String.format("<%s> %s", getClass().toString(), toString());
    }

    @Override
    public String toString() {
        return String.format("(%s)", String.join(", ", data.stream().map(Object::toString).collect(Collectors.toList())));
        
    }
}
