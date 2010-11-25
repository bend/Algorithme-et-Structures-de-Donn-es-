package operations;

import interpreter.InterpreterException;

import java.util.HashMap;

import stack.Stack;

public class OpDef implements OperatorModel {

	@Override
	public void operate(Stack<String> stack, HashMap<String, Float> map)
			throws InterpreterException {
		float val = Float.parseFloat(stack.pop());
		String varName = stack.pop();
		if (varName.startsWith("/"))
			map.put(varName.substring(1), val);
		else
			throw new InterpreterException(
					"Erreur lors de la déclaration de la variable");
	}

}
