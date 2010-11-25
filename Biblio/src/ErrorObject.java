public class ErrorObject {

	private final String message;
	private final int line;

	public ErrorObject(String message, int line) {
		this.message = message;
		this.line = line;
	}

	public String getMessage() {
		return message;
	}

	public int getLine() {
		return line;
	}
}
