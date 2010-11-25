package tokenizer;

import java.util.ArrayList;

import exception.ExpressionException;

public interface TokenizerModel {
	/**
	 * méthode qui parse le String sur sin cos, ( ) etc.. et renvoie les Tokens
	 * 
	 * @return une Collection d'éléments contenant les Tokens
	 */
	public ArrayList<ExpTokenModel> tokenize() throws ExpressionException;

}
