/**
 * Represents Procedures
 */
public class SMPLProcedure extends SMPLDataType<Closure> {
    public SMPLProcedure(Closure data) {
        super(data, false);
    }

    @Override
    public String toTag() {
        return String.format("<%s> %s", getClass().toString(), data.getProc().getName());
    }

    @Override
    public String toString() {
        return toTag();
    }
}
