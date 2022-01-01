/**
 * Represents all numbers
 */
abstract public class SMPLNumber<T> extends SMPLDataType<T> {

    public SMPLNumber(T data) {
        super(data, false);
    }

    @Override
    public SMPLBoolean relationalCmp(Cmp cmp, SMPLDataType o) throws NoSuchMethodException {
        return cmp.apply(this, o);
    }

    public static int toJavaInt(SMPLDataType n) throws NoSuchMethodException {
        if (n.getClass() == SMPLInt.class) {
            return ((SMPLInt) n).getValue();
        } else if (n.getClass() == SMPLChar.class) {
            return ((SMPLChar) n).getValue();
        } else {
            throw new NoSuchMethodException(n.toTag() + " is not an integer");
        }
    }

    /**
     * Converts an SMPLDatatype to a Java Double type
     * @param n The number to be converted
     * @return n as a Java double
     * @throws NoSuchMethodException If n is not a number type
     */
    public static double toJavaDouble(SMPLDataType n) throws NoSuchMethodException {
        if (n.getClass() == SMPLInt.class) {
            return ((SMPLInt) n).getValue();
        } else if (n.getClass() == SMPLChar.class) {
            return ((SMPLChar) n).getValue();
        } else if (n.getClass() == SMPLFloat.class) {
            return ((SMPLFloat) n).getValue();
        } else {
            throw new NoSuchMethodException(n.toTag() +" is not a number");
        }
    }

}
