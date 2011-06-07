/**
 * 
 * @author Benoit Daccache
 * 
 */
public interface BooksModel {

	public void addBook(String title);

	public void addBook(BookModel Book);

	public void newBook();

	public BookModel last();

	public BookModel[] getBooks();

	@Override
	public String toString();

}
