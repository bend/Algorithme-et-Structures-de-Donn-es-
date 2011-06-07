package Keys;

public class Keys {
	private final static String[] keys = { "cos", "sin", "+", "^", "/", "-",
			"(", ")", "*" };

	private final static String[] operators = { "+", "-", "*", "/", "^" };

	private final static String[] functions = { "cos", "sin" };

	public static boolean isKey(String op) {
		for (String str : keys) {
			if (str.equals(op))
				return true;
		}
		return false;
	}

	public static boolean isOperator(String op) {
		for (String str : operators) {
			if (str.equals(op))
				return true;
		}
		return false;
	}

	public static boolean isFunction(String op) {
		for (String str : functions) {
			if (str.equals(op))
				return true;
		}
		return false;
	}
}
