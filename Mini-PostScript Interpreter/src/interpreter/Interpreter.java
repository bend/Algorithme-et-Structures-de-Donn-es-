package interpreter;

import java.util.HashMap;
import java.util.StringTokenizer;

import stack.NodeStack;
import stack.Stack;
import IO.Operators;

public class Interpreter implements InterpreterModel {

	private final Stack<String> stack;
	private final HashMap<String, Float> map;
	private final FactoryModel factory;
	private final Boolean[] isVariableDeclaration;

	/**
	 * Constructeur de la classe Initialize la pile, dictionnaire, et initialize
	 * le vecteur des operateurs
	 */
	public Interpreter() {
		stack = new NodeStack<String>();
		map = new HashMap<String, Float>();
		factory = new OperatorFactory();
		isVariableDeclaration = new Boolean[] { false, false };// nom de
																// varible,
																// valeur,
	}

	/**
	 * m�thode qui est appel�e et qui split le String et appelle la m�thode qui
	 * interpret
	 * 
	 * @param line
	 *            ligne de code
	 */
	@Override
	public void interpret(String line) throws InterpreterException {
		StringTokenizer tokens = new StringTokenizer(line);
		while (tokens.hasMoreTokens()) {
			startInterpret(tokens.nextToken());
		}
	}

	/**
	 * interprete le token re�u en parametre
	 * 
	 * @param nextToken
	 * @throws InterpreterException
	 */
	private void startInterpret(String nextToken) throws InterpreterException {
		if (nextToken.startsWith("/")) {
			if (Operators.isOperator(nextToken.substring(1)))
				throw new InterpreterException(
						"Nom de variable affect� a un operateur");
			stack.push(nextToken);
			isVariableDeclaration[0] = true;
		} else if (isVariableDeclaration[1]) {// la valeur a �t� d�clar�e
			if (!nextToken.equals("def"))
				throw new InterpreterException("def attendu");
			factory.giveInstance(nextToken).operate(stack, map);
			isVariableDeclaration[0] = false;
			isVariableDeclaration[1] = false;
		} else if (isVariableDeclaration[0]) {// le nom de variable est d�clar�
			if (!isNumber(nextToken))
				throw new InterpreterException("Valeur attendue");
			stack.push(nextToken);
			isVariableDeclaration[1] = true;
		} else if (isNumber(nextToken)) {
			stack.push(nextToken);
		} else if (Operators.isOperator(nextToken)) {
			factory.giveInstance(nextToken).operate(stack, map);
		} else if (map.get(nextToken) != null) {
			stack.push(Float.toString(map.get(nextToken)));
		} else
			throw new InterpreterException("Variable non d�clar�e");
	}

	/**
	 * renvoie true si c'est un chiffre ou nombre, false sinon
	 * 
	 * @param nextToken
	 * @return true or false
	 */
	private boolean isNumber(String nextToken) {
		try {
			Float.parseFloat(nextToken);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
