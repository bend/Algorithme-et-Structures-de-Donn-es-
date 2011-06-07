package avltreepack;

public class InvalidKeyException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public InvalidKeyException()
	{
		super("Clé non valide");
	}

}
