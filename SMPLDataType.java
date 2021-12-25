/**
 * Represents all data types in SMPL
 */
abstract public class SMPLDataType<T> {

    protected T data;
    /**
     * @param data
     */
    public SMPLDataType(T data) {
        this.data = data;
    }

    /**
     * @return the data
     */
    public T getValue() {
        return data;
    }

    // Arithmetic

    public SMPLDataType add(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator + not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLNumber subtract(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator - not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLNumber multiply(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator * not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLNumber divide(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator / not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLNumber mod(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator % not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLNumber pow(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator ^ not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLNumber negate() throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator - not allowed on type "+ this.getClass());
    }

    // bitwise
    public SMPLInt bitwiseAnd(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator & not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLInt bitwiseOr(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator | not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLInt bitwiseNot() throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator ~ not allowed on type "+ this.getClass());
    }

    // relational
    public SMPLBoolean relationalCmp(Cmp cmp, SMPLDataType o) throws NoSuchMethodException {
        return cmp.apply(this, o);
    }

    public SMPLBoolean logicalAnd(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Logical operator and not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLBoolean logicalOr(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Logical operator or not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    public SMPLBoolean logicalNot() throws NoSuchMethodException {
        throw new NoSuchMethodException("Logical operator not not allowed on type "+ this.getClass());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
