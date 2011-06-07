package operations;

import interpreter.InterpreterException;

import java.util.HashMap;

import stack.EmptyStackException;
import stack.Stack;

public class OpPop implements OperatorModel {

	@Override
	public void operate(Stack<String> stack, HashMap<String, Float> map)
			throws InterpreterException {
		try {
			stack.pop();
		} catch (EmptyStackException e) {
			throw new InterpreterException("Pile Vide");
		}
	}
}
