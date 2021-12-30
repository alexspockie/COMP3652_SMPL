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

    /**
     * Invoked for the + operator. Adds this and o together and resturns the result.
     * @param o
     * @return the result
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLDataType add(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator + not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    /**
     * Invoked for the - operator. Subtracts o from this and returns the result.
     * @param o
     * @return 
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber subtract(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator - not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    /**
     * Invoked for the * operator
     * @param o
     * @return The product of this multiplied by o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber multiply(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator * not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    /**
     * Invoked for the / operator
     * @param o
     * @return The product of this divided by o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber divide(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator / not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    /**
     * Invoked for the % operator
     * @param o
     * @return The product of this modulus o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber mod(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator % not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    /**
     * Invoked for the ^ operator
     * @param o
     * @return The product of this raised to the power of o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber pow(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator ^ not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    /**
     * Invoked for the unary - operator. 
     * @return The negative of the value of this
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLNumber negate() throws NoSuchMethodException {
        throw new NoSuchMethodException("Operator - not allowed on type "+ this.getClass());
    }

    // bitwise

    public SMPLInt bitwiseOp(BitwiseOp op, SMPLDataType o) throws NoSuchMethodException {
        if (o != null) {
            return op.apply(this, o);
        } else {
            return op.apply(this);
        }
    }

    // relational

    /**
     * Invoked for the comparisons
     * @param o
     * @param cmp The comparison operator
     * @return The result of this compared to o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLBoolean relationalCmp(Cmp cmp, SMPLDataType o) throws NoSuchMethodException {
        return cmp.apply(this, o);
    }

    /**
     * Invoked for the 'and' operator
     * @param o
     * @return The result of this AND o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLBoolean logicalAnd(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Logical operator and not allowed between types "+ this.getClass() + " and " + o.getClass());
    }

    /**
     * Invoked for the 'or' operator
     * @param o
     * @return The resut of this OR o
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLBoolean logicalOr(SMPLDataType o) throws NoSuchMethodException {
        throw new NoSuchMethodException("Logical operator or not allowed between types "+ this.getClass() + " and " + o.getClass());
    }


    /**
     * Invoked for the 'not' operator
     * @return The resut of NOT this
     * @throws NoSuchMethodException If this method is not allowed for this datatype
     */
    public SMPLBoolean logicalNot() throws NoSuchMethodException {
        throw new NoSuchMethodException("Logical operator not not allowed on type "+ this.getClass());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
