/**
 * Represents all numbers
 */
abstract public class SMPLNumber<T> extends SMPLDataType<T> {

    public SMPLNumber(T data) {
        super(data);
    }

    @Override
    public SMPLNumber add(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Not implemented");
    }

    @Override
    public SMPLNumber subtract(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Not implemented");
    }

    @Override
    public SMPLNumber multiply(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Not implemented");
    }

    @Override
    public SMPLNumber pow(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Not Implemented");
    }

    @Override
    public SMPLNumber negate() throws NoSuchMethodException {
        throw new NoSuchMethodException("Not Implemented");

    }

    @Override
    public SMPLNumber divide(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Not Implemented");
    }

    @Override
    public SMPLNumber mod(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Not Implemented");

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
            throw new NoSuchMethodException(n +" is not a number");
        }
    }

}
