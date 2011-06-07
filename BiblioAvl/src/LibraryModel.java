import avltreepack.InvalidKeyException;

/**
 * 
 * @author Benoit Daccache
 * 
 */
public interface LibraryModel {

	/**
	 * ajoute les données dans la Biblio
	 * 
	 * @param info
	 *            tableaux contenant les données du fichier entre les espaces
	 * @throws LibException
	 * @throws InvalidKeyException
	 */
	public void add(String info) throws LibException, InvalidKeyException;

	public void load(String filename) throws InvalidKeyException;

	public BooksModel getBooks(String author) throws InvalidKeyException;
}
