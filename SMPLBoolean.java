/**
 * Represents a boolean
 */
public class SMPLBoolean extends SMPLDataType<Boolean> {

    public SMPLBoolean(Boolean data) {
        super(data, false);
    }

    @Override
    public SMPLBoolean logicalAnd(SMPLDataType o) throws NoSuchMethodException {
        try {
            return new SMPLBoolean(SMPLBoolean.class.cast(o).getValue() && data);

        } catch (Exception e) {

            throw new NoSuchMethodException(o + " is not a boolean value");
        }
    }

    @Override
    public SMPLBoolean logicalNot() throws NoSuchMethodException {
        return new SMPLBoolean(!data);
    }

    @Override
    public SMPLBoolean logicalOr(SMPLDataType o) throws NoSuchMethodException {

        try {
            return new SMPLBoolean(SMPLBoolean.class.cast(o).getValue() || data);

        } catch (Exception e) {

            throw new NoSuchMethodException(o + " is not a boolean value");
        }
    }

}
