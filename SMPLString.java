/**
 * Represents strings
 */
public class SMPLString extends SMPLDataType<String>{

    public SMPLString(String data) {
        super(data, false);
    }

    @Override
    public SMPLString add(SMPLDataType o) throws NoSuchMethodException {
        return new SMPLString(data + o.getValue());
    }

}
