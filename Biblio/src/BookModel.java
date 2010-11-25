import java.util.LinkedList;

/**
 * 
 * @author Benoit Daccache
 * 
 */
public interface BookModel {
	// Setters
	public void setEditor(String editor);

	public void setTitle(String title);

	public void setDate(String date);

	public void setRef(String ref);

	public void setCat(String cat);

	public void setCity(String trim);

	// getters

	public String getEditor();

	public String getDate();

	public String getTitle();

	public String getCat();

	public String getRef();

	public String getCity();

	public LinkedList<String> getAuthors();// a changer

	public void addAuthor(String name);

	@Override
	public String toString();

}
