/**
 * Represents strings
 */
public class SMPLString extends SMPLDataType<String>{

    public SMPLString(String data) {
        super(data);
    }

    @Override
    public SMPLString add(SMPLDataType o) throws NoSuchMethodException {
        return new SMPLString(data + o.getValue());
    }

}
