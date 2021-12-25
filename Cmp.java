public enum Cmp {
	LT("<") {

		@Override
		public <T extends SMPLDataType> SMPLBoolean apply(T arg1, T arg2) throws NoSuchMethodException {

			double arg1d = SMPLNumber.toJavaDouble(arg1);
			double arg2d = SMPLNumber.toJavaDouble(arg2);

			return new SMPLBoolean(arg1d < arg2d);
		}
	},

	LE("<=") {
		public <T extends SMPLDataType> SMPLBoolean apply(T arg1, T arg2) throws NoSuchMethodException {
			double arg1d = SMPLNumber.toJavaDouble(arg1);
			double arg2d = SMPLNumber.toJavaDouble(arg2);
			return new SMPLBoolean(arg1d <= arg2d);
		}
	},

	EQ("=") {
		public <T extends SMPLDataType> SMPLBoolean apply(T arg1, T arg2) {
			return new SMPLBoolean(arg1 == arg2);
		}
	},

	NE("!=") {
		public <T extends SMPLDataType> SMPLBoolean apply(T arg1, T arg2) {
			return new SMPLBoolean(arg1 == arg2);
		}
	},

	GT(">") {
		public <T extends SMPLDataType> SMPLBoolean apply(T arg1, T arg2) throws NoSuchMethodException {
			double arg1d = SMPLNumber.toJavaDouble(arg1);
			double arg2d = SMPLNumber.toJavaDouble(arg2);
			return new SMPLBoolean(arg1d < arg2d);
		}
	},

	GE(">=") {
		public <T extends SMPLDataType> SMPLBoolean apply(T arg1, T arg2) throws NoSuchMethodException {
			double arg1d = SMPLNumber.toJavaDouble(arg1);
			double arg2d = SMPLNumber.toJavaDouble(arg2);
			return new SMPLBoolean(arg1d < arg2d);
		}
	};

	private String symbol;

	private Cmp(String sym) {
		symbol = sym;
	}

	/**
	 * Applies this comparision operator to arg1 and arg2
	 * @param <T> An SMPLDataType
	 * @param arg1
	 * @param arg2
	 * @return SMPLBoolean of the comparison result
	 * @throws NoSuchMethodException if this comparison is not allowed between the types of arg1 and/or arg2
	 */
	public abstract <T extends SMPLDataType> SMPLBoolean apply(T arg1, T arg2) throws NoSuchMethodException;

	public String toString() {
		return symbol;
	}
}
