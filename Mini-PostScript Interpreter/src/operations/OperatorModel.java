package operations;

import interpreter.InterpreterException;

import java.util.HashMap;

import stack.Stack;

public interface OperatorModel {
	/**
	 * méthode qui effectue l'operation sur la pile
	 * 
	 * @param stack
	 *            la pile
	 * @param map
	 *            ou sont stocké les variables déclarées
	 * @throws InterpreterException
	 *             si une erreur survient lors de l'operation
	 */

	public void operate(Stack<String> stack, HashMap<String, Float> map)
			throws InterpreterException;
}
