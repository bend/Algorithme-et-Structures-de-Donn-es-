package main;

import interpreter.Interpreter;
import interpreter.InterpreterException;
import interpreter.InterpreterModel;

import java.io.IOException;
import java.util.EmptyStackException;

import IO.FileIO;

public class Main {

	private void start(String inputfilename) throws IOException,
			InterpreterException {
		InterpreterModel interpreter = new Interpreter();
		new FileIO();
		String[] res = FileIO.lireContenu(inputfilename);
		for (int i = 0; i < res.length; i++) {
			interpreter.interpret(res[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		try {
			if (args[0].endsWith(".mps"))
				new Main().start(args[0]);
			else
				System.out.println("invalid file, mps file expected\n");
		} catch (InterpreterException e) {
			System.out.println(e.getMessage());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Expected '.mps' input file\n");
		} catch (EmptyStackException e) {
			System.out.println("Empty Stack");
		}
	}
}
