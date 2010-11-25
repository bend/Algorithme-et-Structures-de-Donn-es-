import java.util.LinkedList;

/**
 * 
 * @author Benoit Daccache
 * 
 */
public class Books implements BooksModel {
	LinkedList<BookModel> books;

	public Books() {
		books = new LinkedList<BookModel>();
	}

	@Override
	public void addBook(String title) {
		books.add(new Book(title));
	}

	@Override
	public void newBook() {
		books.add(new Book());
	}

	@Override
	public void addBook(BookModel book) {
		books.add(book);
	}

	@Override
	public BookModel last() {
		return books.getLast();
	}

	@Override
	public BookModel[] getBooks() {
		BookModel[] vect = new BookModel[books.size()];
		int i = 0;
		for (BookModel book : books) {
			vect[i] = book;
			i++;
		}
		return vect;
	}
}
