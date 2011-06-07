package tokenizer;

import java.util.ArrayList;

import Keys.Keys;
import exception.ExpressionException;

public class Tokenizer implements TokenizerModel {
	private String fonction;
	private final ArrayList<ExpTokenModel> tokens;

	public Tokenizer(String str) {
		this.fonction = str;
		tokens = new ArrayList<ExpTokenModel>();
	}

	@Override
	public ArrayList<ExpTokenModel> tokenize() throws ExpressionException {
		while (getNextChar() != null) {
			String e = getNextChar();
			if (Keys.isKey(e) || e.equals("x")) {
				tokens.add(new ExpToken(e));
				next();
			} else if (isNumber(e)) {
				String temp = e;
				next();
				e = getNextChar();
				while (isNumber(e)) {
					temp += e;
					next();
					e = getNextChar();
				}
				tokens.add(new ExpToken(temp));
			} else {
				String s = "";
				for (int i = 0; i < 3; i++) {
					s += getNextChar();
					next();
				}
				if (s.equals("sin") || s.equals("cos")) {
					tokens.add(new ExpToken(s));
				} else
					throw new ExpressionException("Invalid expression: " + s
							+ " !");
			}
		}
		return tokens;
	}

	private String getNextChar() {
		if (fonction.length() == 0)
			return null;
		return fonction.substring(0, 1);
	}

	private void next() {
		fonction = fonction.substring(1);
	}

	private boolean isNumber(String nextToken) {
		try {
			Integer.parseInt(nextToken);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
