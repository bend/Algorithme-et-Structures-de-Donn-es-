
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
	 */
	public void add(String info) throws LibException;

	public void load(String filename);

	public BooksModel getBooks(String author);
}
