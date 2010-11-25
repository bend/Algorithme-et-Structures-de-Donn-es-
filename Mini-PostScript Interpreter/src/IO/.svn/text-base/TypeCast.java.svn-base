package IO;

import interpreter.InterpreterException;
import stack.EmptyStackException;
import stack.Stack;

public class TypeCast {
	/**
	 * Cast un String en Float si possible
	 * 
	 * @param stack
	 *            la pile ou est stocké le flaot sous forme de string
	 * @return le Float
	 * @throws InterpreterException
	 *             si la pile ne contient pas un float en top
	 */
	protected Float getFloat(Stack<String> stack) throws InterpreterException {
		try {
			return Float.parseFloat(stack.pop());
		} catch (NumberFormatException e) {
			throw new InterpreterException("Expected Float");
		} catch (EmptyStackException e1) {
			throw new InterpreterException("Pile Vide");
		}
	}
}
