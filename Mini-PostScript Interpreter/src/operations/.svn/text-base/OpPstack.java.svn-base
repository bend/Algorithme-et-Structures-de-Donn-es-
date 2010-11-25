package operations;

import java.io.IOException;
import java.util.HashMap;

import stack.Stack;
import IO.FileIO;

public class OpPstack implements OperatorModel {

	// @Override
	/*
	 * public void operate(NodeStack<String> stack, HashMap<String, Float> map)
	 * throws InterpreterException { System.out.println(stack.toString()); }
	 */
	@Override
	public void operate(Stack<String> stack, HashMap<String, Float> map) {
		try {
			FileIO.ecrireContenu("output.txt", stack.toString());
		} catch (IOException e) { // TODO Auto-generated catch block
									// e.printStackTrace();
		}

	}

}
