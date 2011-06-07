package operations;

import interpreter.InterpreterException;

import java.util.HashMap;

import stack.Stack;

public class OpDup implements OperatorModel {

	@Override
	public void operate(Stack<String> stack, HashMap<String, Float> map)
			throws InterpreterException {
		String Operand = stack.pop();
		stack.push(Operand);
		stack.push(Operand);
	}

}
