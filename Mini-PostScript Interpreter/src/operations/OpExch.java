package operations;

import java.util.HashMap;

import stack.Stack;

public class OpExch implements OperatorModel {

	@Override
	public void operate(Stack<String> stack, HashMap<String, Float> map) {
		String rightOperand = stack.pop();
		String leftOperand = stack.pop();
		stack.push(rightOperand);
		stack.push(leftOperand);
	}

}
