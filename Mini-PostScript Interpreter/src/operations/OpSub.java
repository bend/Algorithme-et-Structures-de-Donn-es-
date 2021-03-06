package operations;

import interpreter.InterpreterException;

import java.util.HashMap;

import stack.Stack;
import IO.TypeCast;

public class OpSub extends TypeCast implements OperatorModel {

	@Override
	public void operate(Stack<String> stack, HashMap<String, Float> map)
			throws InterpreterException {
		Float rightOperand = getFloat(stack);
		Float leftOperand = getFloat(stack);
		stack.push(Float.toString(leftOperand - rightOperand));
	}

}
