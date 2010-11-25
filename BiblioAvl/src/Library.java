import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import avltreepack.DicoTree;
import avltreepack.Dictionary;
import avltreepack.Entry;
import avltreepack.InvalidKeyException;

/**
 * 
 * @author Benoit Daccache
 * 
 */
public class Library extends Observable implements LibraryModel {

	private final Dictionary<String, BooksModel> lib;
	private LibReader in;
	private boolean isAdded = false;
	private BookModel book;

	public Library() {
		lib = new DicoTree<String, BooksModel>();
	}

	/**
	 * @deprecated
	 * @param filename
	 * @throws InvalidKeyException
	 */
	@Deprecated
	public void load2(String filename) throws InvalidKeyException {
		long current = System.currentTimeMillis();
		try {
			in = new LibReader(filename);
			String ligne = null;
			ligne = in.readLine();
			while (ligne != null) {
				ArrayList<String> liste = new ArrayList<String>();
				do {
					liste.add(ligne);
				} while ((ligne = in.readLine()) != null && !ligne.equals(""));
				add(liste);
				while ((ligne = in.readLine()) != null && ligne.equals("")) {
					;// on saute la ligne
				}
			}
			in.close();
			long time = System.currentTimeMillis() - current;
			show(lib.size() + " authors added");
			System.out.println("Time : " + time + " ms");
			show("Lines read :" + in.getLine());
		} catch (IOException e) {
			show(e.getMessage());
		}

	}

	@Override
	public void load(String filename) throws InvalidKeyException {
		long current = System.currentTimeMillis();
		try {
			in = new LibReader(filename);
			String ligne = null;
			ligne = in.readLine();
			while (ligne != null) {
				book = new Book();
				isAdded = false;
				do {
					add(ligne);
				} while ((ligne = in.readLine()) != null && !ligne.equals(""));
				while ((ligne = in.readLine()) != null && ligne.equals("")) {
					;// on saute la ligne
				}
				if (!isAdded && book.getTitle() != null) {
					addBook(book, "unknown author");
					show("Error: Book \'"
							+ book.getTitle()
							+ "\' added with author name \'unknown author\' <Line "
							+ (in.getLine() - 1) + ">");
				}
			}
			in.close();
			long time = System.currentTimeMillis() - current;
			show(lib.size() + " authors added");
			System.out.println("Time : " + time + " ms");
			show("Lines read :" + in.getLine());
		} catch (IOException e) {
			show(e.getMessage());
		}

	}

	@Override
	public void add(String str) throws InvalidKeyException {
		char c = 0;
		try {
			if (str.startsWith("%") && str.charAt(2) == ' ')
				c = str.charAt(1);
			else {
				show(new ErrorObject("Error while parsing file: Expected %",
						in.getLine()));
				return;
			}
			switch (c) {
			case 'A':
				String author = str.substring(3).trim();
				book.addAuthor(author);
				addBook(book, author);
				isAdded = true;
				break;
			case 'T':
				book.setTitle(str.substring(3).trim());
				break;
			case 'E':
				book.setEditor(str.substring(3).trim());
				break;
			case 'D':
				book.setDate(str.substring(3).trim());
				break;
			case 'K':
				book.setCat(str.substring(3).trim());
				break;
			case 'W':
				book.setRef(str.substring(3).trim());
				break;
			case 'C':
				book.setCity(str.substring(3).trim());
				break;
			default:
				show(new ErrorObject("Unknown identificator ", in.getLine()));
				break;
			}
		} catch (StringIndexOutOfBoundsException e) {
			show("Error while parsing file <Line " + (in.getLine()) + ">");
			return;

		}

	}

	/**
	 * @deprecated
	 * @param info
	 * @throws InvalidKeyException
	 */
	@Deprecated
	public void add(ArrayList<String> info) throws InvalidKeyException {
		BookModel book = new Book();
		boolean isAdded = false;
		int i = 0;
		for (String str : info) {
			char c = 0;
			try {
				if (str.startsWith("%") && str.charAt(2) == ' ')
					c = str.charAt(1);
				else {
					show("Error while parsing file: Expected % <Line "
							+ (in.getLine() - info.size() + i) + ">");
					return;
				}
				switch (c) {
				case 'A':
					String author = str.substring(3).trim();
					book.addAuthor(author);
					addBook(book, author);
					isAdded = true;
					break;
				case 'T':
					book.setTitle(str.substring(3).trim());
					break;
				case 'E':
					book.setEditor(str.substring(3).trim());
					break;
				case 'D':
					book.setDate(str.substring(3).trim());
					break;
				case 'K':
					book.setCat(str.substring(3).trim());
					break;
				case 'W':
					book.setRef(str.substring(3).trim());
					break;
				default:
					show("Unknown identificator %" + c + " <Line "
							+ (in.getLine() - info.size() + i) + ">");
					break;
				// throw new LibException("Error in File");

				}
			} catch (StringIndexOutOfBoundsException e) {
				show("Error while parsing file <Line "
						+ (in.getLine() - info.size() + i) + ">");
				return;
			}
			i++;
		}
		if (!isAdded && book.getTitle() != null) {
			show("Error: Book \'" + book.getTitle()
					+ "\' not added because no author found <Line "
					+ (in.getLine() - 1) + ">");
			return;
		}

	}

	private void show(Object str) {
		setChanged();
		notifyObservers(str);
	}

	/**
	 * Ajoute le livre dans la bib
	 * 
	 * @param book
	 *            Le livre a ajouter
	 * @param author
	 *            l'auteur
	 * @throws InvalidKeyException
	 */
	private void addBook(BookModel book, String author)
			throws InvalidKeyException {
		try {
			lib.get(author.toLowerCase()).getValue().addBook(book);
		} catch (NullPointerException e) {
			lib.put(author.toLowerCase(), new Books());

		}
		/*
		 * if (lib.get(author.toLowerCase()) == null) {
		 * lib.put(author.toLowerCase(), new Books()); }
		 * lib.get(author.toLowerCase()).getValue().addBook(book);
		 */
	}

	// @Override
	/**
	 * Cherche la première clé qui contient la séquence author et retourne la
	 * valeur associée.
	 */
	/*
	 * public BooksModel getBooks2(String author) { Iterator<String> it =
	 * lib.keySet().iterator(); String name = null; while (it.hasNext()) {
	 * String v = it.next(); if (v.contains(author.toLowerCase())) { name = v;
	 * break; } } return lib.get(name).getValue(); }
	 */

	/**
	 * retourne la valeur associée à la clé.
	 * 
	 * @param author
	 * @return
	 * @throws InvalidKeyException
	 */
	@Override
	public BooksModel getBooks(String author) throws InvalidKeyException {
		Entry<String, BooksModel> e = lib.get(author.toLowerCase());
		return e.getValue();
	}
}
