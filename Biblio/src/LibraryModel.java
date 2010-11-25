
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
	 */
	public void add(String info) throws LibException;

	public void load(String filename);

	public BooksModel getBooks(String author);
}
