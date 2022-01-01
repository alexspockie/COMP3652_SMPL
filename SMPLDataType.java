/**
 * Represents all data types in SMPL
 */
abstract public class SMPLDataType<T> {

    protected T data;
    protected Boolean isCompound;

    /**
     * @param data
     */
    public SMPLDataType(T data, boolean compound) {
        this.data = data;
        this.isCompound = compound;
    }

    /**
     * @return the data
     */
    public T getValue() {
        return data;
    }

    // Arithmetic

    /**
     * Invoked for the + operator. Adds this and o together and resturns the result.
     * 
     * @param o
     * @return the result
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLDataType add(SMPLDataType o) throws NoSuchMethodException {
        if ((this.getClass() == SMPLString.class) || (o.getClass() == SMPLString.class)) {
            return new SMPLString(this.getValue().toString() + o.getValue().toString());
        } else {
            throw new NoSuchMethodException(
                    "Operator + not allowed between types " + this.toTag() + " and " + o.toTag());
        }
    }

    /**
     * Invoked for the - operator. Subtracts o from this and returns the result.
     * 
     * @param o
     * @return
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber subtract(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                "Operator - not allowed between types " + this.toTag() + " and " + o.toTag());
    }

    /**
     * Invoked for the * operator
     * 
     * @param o
     * @return The product of this multiplied by o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber multiply(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                "Operator * not allowed between types " + this.toTag() + " and " + o.toTag());
    }

    /**
     * Invoked for the / operator
     * 
     * @param o
     * @return The product of this divided by o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber divide(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                "Operator / not allowed between types " + this.toTag() + " and " + o.toTag());
    }

    /**
     * Invoked for the % operator
     * 
     * @param o
     * @return The product of this modulus o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber mod(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                "Operator % not allowed between types " + this.toTag() + " and " + o.toTag());
    }

    /**
     * Invoked for the ^ operator
     * 
     * @param o
     * @return The product of this raised to the power of o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber pow(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                "Operator ^ not allowed between types " + this.toTag() + " and " + o.toTag());
    }

    /**
     * Invoked for the unary - operator.
     * 
     * @return The negative of the value of this
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber negate() throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator - not allowed on type " + this.toTag());
    }

    // bitwise

    public SMPLInt bitwiseOp(BitwiseOp op, SMPLDataType o) throws NoSuchMethodException {
        if (o == null) {
            throw new NoSuchMethodException("Bitwise operators not allowed on type " + this.toTag());
        } else {
            throw new NoSuchMethodException(
                    "Bitwise operators not allowed between type " + this.toTag() + " and type " + o.toTag());
        }
    }

    // relational

    /**
     * Invoked for the comparisons
     * 
     * @param o
     * @param cmp The comparison operator
     * @return The result of this compared to o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLBoolean relationalCmp(Cmp cmp, SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                String.format("Comparison not allowed between types %s and %s", this.toTag(), o.toTag()));
    }

    /**
     * Invoked for the 'and' operator
     * 
     * @param o
     * @return The result of this AND o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLBoolean logicalAnd(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                "Logical operator and not allowed between types " + this.toTag() + " and " + o.toTag());
    }

    /**
     * Invoked for the 'or' operator
     * 
     * @param o
     * @return The resut of this OR o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLBoolean logicalOr(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                "Logical operator or not allowed between types " + this.toTag() + " and " + o.toTag());
    }

    /**
     * Invoked for the 'not' operator
     * 
     * @return The resut of NOT this
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLBoolean logicalNot() throws NoSuchMethodException {
        throw new NoSuchMethodException("Logical operator not not allowed on type " + this.toTag());
    }

    /**
     * Invoked for the @ operator
     * Modifies this list internally and returns a reference to this list.
     * @return this list
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLList concat(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException(
                "List concatenation not allowed between types " + this.toTag() + " and " + o.toTag());
    }

    public String toTag() {
        return String.format("<%s> %s", getClass().toString(), data.toString());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
