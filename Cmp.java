public enum Cmp {
    LT("<") {
	public boolean apply(double arg1, double arg2) {
	    return arg1 < arg2;
	}
    },

    LE("<=") {
	public boolean apply(double arg1, double arg2) {
	    return arg1 <= arg2;
	}
    },

    EQ("=") {
	public boolean apply(double arg1, double arg2) {
	    return arg1 == arg2;
	}
    },

    NE("!=") {
	public boolean apply(double arg1, double arg2) {
	    return arg1 != arg2;
	}
    },

    GT(">") {
	public boolean apply(double arg1, double arg2) {
	    return arg1 > arg2;
	}
    },

    GE(">=") {
	public boolean apply(double arg1, double arg2) {
	    return arg1 >= arg2;
	}
    }
    ;

    private String symbol;

    private Cmp(String sym) {
	symbol = sym;
    }

    public abstract boolean apply(double arg1, double arg2);

    public String toString() {
	return symbol;
    }
}
