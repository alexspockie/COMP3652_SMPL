/**
 * Represents integers
 */
public class SMPLInt extends SMPLIntegral<Integer> {

    public SMPLInt(Integer data) {
        super(data);
    }

    @Override
    public SMPLNumber add(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        if(o.getClass() == SMPLFloat.class){
            return new SMPLFloat(val + data);
        }else{
            return new SMPLInt(Double.valueOf(val + data).intValue());
        }
    }

    @Override
    public SMPLNumber subtract(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        if(o.getClass() == SMPLFloat.class){
            return new SMPLFloat(data - val);
        }else{
            return new SMPLInt(Double.valueOf(data - val).intValue());
        }
    }

    @Override
    public SMPLNumber multiply(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        if(o.getClass() == SMPLFloat.class){
            return new SMPLFloat(val * data);
        }else{
            return new SMPLInt(Double.valueOf(val * data).intValue());
        }
    }

    @Override
    public SMPLNumber mod(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        if(o.getClass() == SMPLFloat.class){
            return new SMPLFloat(data % val);
        }else{
            return new SMPLInt(Double.valueOf(data % val).intValue());
        }
    }

    @Override
    public SMPLNumber negate() throws NoSuchMethodException {
        return new SMPLInt(-data);
    }

    @Override
    public SMPLNumber pow(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        if(o.getClass() == SMPLFloat.class){
            return new SMPLFloat(Math.pow(data, val));
        }else{
            return new SMPLInt(Double.valueOf(Math.pow(data, val)).intValue());
        }
    }

    @Override
    public SMPLNumber divide(SMPLDataType o) throws NoSuchMethodException {
        Double val = SMPLNumber.toJavaDouble(o);

        if(o.getClass() == SMPLFloat.class){
            return new SMPLFloat(data / val);
        }else{
            return new SMPLInt(Double.valueOf(data / val).intValue());
        }
    }




}
