public enum BitwiseOp {
    AND("&") {

        @Override
        public <T extends SMPLDataType> SMPLInt apply(T arg1, T arg2) throws NoSuchMethodException {
            int i = SMPLNumber.toJavaInt(arg1);
            int i2 = SMPLNumber.toJavaInt(arg2);

            return new SMPLInt(i & i2);
        }

        @Override
        public <T extends SMPLDataType> SMPLInt apply(T arg1) throws NoSuchMethodException {
            throw new NoSuchMethodException("& requires two operands");
        }
    },

    OR("|") {

        @Override
        public <T extends SMPLDataType> SMPLInt apply(T arg1, T arg2) throws NoSuchMethodException {
            int i = SMPLNumber.toJavaInt(arg1);
            int i2 = SMPLNumber.toJavaInt(arg2);

            return new SMPLInt(i | i2);
        }

        @Override
        public <T extends SMPLDataType> SMPLInt apply(T arg1) throws NoSuchMethodException {
            throw new NoSuchMethodException("& requires two operands");
        }
    },

    NOT("~") {

        @Override
        public <T extends SMPLDataType> SMPLInt apply(T arg1, T arg2) throws NoSuchMethodException {
            throw new NoSuchMethodException("~ requires one operand");

        }

        @Override
        public <T extends SMPLDataType> SMPLInt apply(T arg1) throws NoSuchMethodException {
            int i = SMPLNumber.toJavaInt(arg1);

            return new SMPLInt(~i);
        }
    };

    private String symbol;

    private BitwiseOp(String sym) {
        symbol = sym;
    }

    /**
     * Applies this bitwise operator to arg1 and arg2
     * 
     * @param <T>  An SMPLDataType
     * @param arg1
     * @param arg2
     * @return SMPLInt of the result
     * @throws NoSuchMethodException if this operator is not allowed on the types of
     *                               arg1 and/or arg2
     */
    public abstract <T extends SMPLDataType> SMPLInt apply(T arg1, T arg2) throws NoSuchMethodException;

    /**
     * Applies this bitwise operator to arg1
     * 
     * @param <T>  An SMPLDataType
     * @param arg1
     * @return SMPLInt of the result
     * @throws NoSuchMethodException if this operator is not allowed on the type of
     *                               arg1
     */
    public abstract <T extends SMPLDataType> SMPLInt apply(T arg1) throws NoSuchMethodException;

    public String toString() {
        return symbol;
    }
}
