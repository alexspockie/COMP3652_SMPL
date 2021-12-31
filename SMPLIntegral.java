/**
 * Represents all integer like types in SMPL
 */
abstract public class SMPLIntegral<T> extends SMPLNumber<T> {

    public SMPLIntegral(T data) {
        super(data);
    }

    @Override
    public SMPLInt bitwiseOp(BitwiseOp op, SMPLDataType o) throws NoSuchMethodException {
        if (o != null) {
            return op.apply(this, o);
        } else {
            return op.apply(this);
        }
    }
}
