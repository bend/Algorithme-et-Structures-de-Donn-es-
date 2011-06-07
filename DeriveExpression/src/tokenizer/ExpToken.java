package tokenizer;

import Keys.Keys;

public class ExpToken implements ExpTokenModel {
	private final String token;

	public ExpToken(String s) {
		this.token = s;
	}

	@Override
	public boolean isOperand() {
		return !isFunction() && !isOperator() && !isOpeningParenth()
				&& !isClosingParenth();
	}

	@Override
	public boolean isFunction() {
		return Keys.isFunction(token);
	}

	@Override
	public boolean isOperator() {
		return Keys.isOperator(token);
	}

	@Override
	public boolean isOpeningParenth() {
		return token.equals("(");
	}

	@Override
	public boolean isClosingParenth() {
		return token.equals(")");
	}

	@Override
	public String toString() {
		return token;
	}

}
