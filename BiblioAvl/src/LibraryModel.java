import avltreepack.InvalidKeyException;

/**
 * 
 * @author Benoit Daccache
 * 
 */
public interface LibraryModel {

	/**
	 * ajoute les donn�es dans la Biblio
	 * 
	 * @param info
	 *            tableaux contenant les donn�es du fichier entre les espaces
	 * @throws LibException
	 * @throws InvalidKeyException
	 */
	public void add(String info) throws LibException, InvalidKeyException;

	public void load(String filename) throws InvalidKeyException;

	public BooksModel getBooks(String author) throws InvalidKeyException;
}
