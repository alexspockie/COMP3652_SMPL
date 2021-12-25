/**
 * Represents all data types in SMPL
 */
abstract public class SMPLIntegral<T> extends SMPLNumber<T> {

    public SMPLIntegral(T data) {
        super(data);
    }

    @Override
    public SMPLInt bitwiseAnd(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Not Implemented");

    }

    @Override
    public SMPLInt bitwiseNot() throws NoSuchMethodException {
        throw new NoSuchMethodException("Not Implemented");

    }

    @Override
    public SMPLInt bitwiseOr(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Not Implemented");

    }

    public static int toJavaInt(SMPLDataType n) throws NoSuchMethodException {
        if (n.getClass() == SMPLInt.class) {
            return ((SMPLInt) n).getValue();
        } else if (n.getClass() == SMPLChar.class) {
            return ((SMPLChar) n).getValue();
        } else {
            throw new NoSuchMethodException();
        }
    }
}
