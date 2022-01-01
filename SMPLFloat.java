/**
 * Represents floating point numbers
 */
public class SMPLFloat extends SMPLNumber<Double> {

    public SMPLFloat(Double data) {
        super(data);
    }

    @Override
    public SMPLDataType add(SMPLDataType o) throws NoSuchMethodException {
        try {
            return super.add(o);
        } catch (Exception e) {

            Double val = SMPLNumber.toJavaDouble(o);

            return new SMPLFloat(val + data);
        }
    }

    @Override
    public SMPLNumber subtract(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        return new SMPLFloat(data - val);
    }

    @Override
    public SMPLNumber multiply(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        return new SMPLFloat(val * data);
    }

    @Override
    public SMPLNumber mod(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        return new SMPLFloat(data % val);
    }

    @Override
    public SMPLNumber negate() throws NoSuchMethodException {
        return new SMPLFloat(-data);
    }

    @Override
    public SMPLNumber pow(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        return new SMPLFloat(Math.pow(data, val));
    }

    @Override
    public SMPLNumber divide(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        return new SMPLFloat(data / val);
    }

}
