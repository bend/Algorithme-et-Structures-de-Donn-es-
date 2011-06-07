package tokenizer;

public interface ExpTokenModel {

	/**
	 * renvoie true si le token est un opérande
	 * 
	 * @return
	 */
	public boolean isOperand();

	/**
	 * return true si le token est un sin ou cos
	 * 
	 * @return
	 */
	public boolean isFunction();

	/**
	 * return true si le token est un + - / * et ^
	 * 
	 * @return
	 */
	public boolean isOperator();

	/**
	 * return true si le token est '('
	 * 
	 * @return
	 */
	public boolean isOpeningParenth();

	/**
	 * return true si le token est ')'
	 * 
	 * @return
	 */
	public boolean isClosingParenth();

	@Override
	public String toString();
}
