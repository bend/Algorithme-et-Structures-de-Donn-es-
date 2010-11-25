package interpreter;

public interface InterpreterModel {

	/**
	 * m�thode qui est appel�e et qui split le String et appelle la m�thode qui
	 * interprete
	 * 
	 * @param line
	 *            ligne de code
	 */
	public void interpret(String line) throws InterpreterException;

}
