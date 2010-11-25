package operations;

import interpreter.InterpreterException;

import java.util.HashMap;

import stack.Stack;
import IO.TypeCast;

public class OpDiv extends TypeCast implements OperatorModel {

	@Override
	public void operate(Stack<String> stack, HashMap<String, Float> map)
			throws InterpreterException {
		Float rightOperand = getFloat(stack);
		Float leftOperand = getFloat(stack);
		if (rightOperand == 0)
			throw new InterpreterException("Division par 0 impossible");
		stack.push(Float.toString(leftOperand / rightOperand));
	}
}
