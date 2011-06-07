import java.util.LinkedList;

/**
 * 
 * @author Benoit Daccache
 * 
 */
public class Book implements BookModel {
	private final LinkedList<String> authors;// %A
	private String title;// %T
	private String editor;// %E
	private String date;// %D
	private String ref;// %W
	private String cat;// %K
	private String city;

	@Override
	public String getRef() {
		return ref;
	}

	@Override
	public void setRef(String ref) {
		this.ref = ref;
	}

	@Override
	public String getCat() {
		return cat;
	}

	@Override
	public void setCat(String cat) {
		this.cat = cat;
	}

	public Book() {
		authors = new LinkedList<String>();
	}

	public Book(String title) {
		this();
		setTitle(title);
	}

	@Override
	public String getEditor() {
		return editor;
	}

	@Override
	public void setEditor(String editor) {
		this.editor = editor;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public LinkedList<String> getAuthors() {
		return authors;
	}

	@Override
	public void addAuthor(String name) {
		this.authors.push(name);
	}

	@Override
	public String toString() {
		String toString = "title: " + title + "\nauthors:\n\t";
		for (String s : authors)
			toString += s + "\t\n";
		toString += "Editor: " + editor + "\nDate: " + date + "\nRef: " + ref
				+ "\nCategories: " + cat + "\nAddress: " + city;
		return toString;
	}

	@Override
	public void setCity(String trim) {
		this.city = trim;
	}

	@Override
	public String getCity() {
		return city;
	}

}