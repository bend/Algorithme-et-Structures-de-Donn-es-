import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import avltreepack.InvalidKeyException;

/**
 * 
 * @author Benoit Daccache
 * 
 */
public class Controller extends Observable {
	LibraryModel lib;
	VueError vE;
	VueBook vB;
	BufferedReader in;

	private void start(String inputfilename) throws IOException,
			InvalidKeyException {
		lib = new Library();
		in = new BufferedReader(new InputStreamReader(System.in));
		vE = new VueError();
		vB = new VueBook();
		this.addObserver(vE);
		this.addObserver(vB);
		show(new DataObject("output errors? Y/N"));
		String c = in.readLine();
		if (c.equalsIgnoreCase("Y"))
			((Observable) lib).addObserver(vE);
		else if (!c.equalsIgnoreCase("N"))
			show(new DataObject("Invalid character, output off"));
		show(new DataObject("Loading library"));
		lib.load(inputfilename);
		show(new DataObject("Library Loaded"));
		startUI();

	}

	private void startUI() throws IOException, InvalidKeyException {
		String commande;
		do {
			show(new DataObject("Enter the author name: "));
			commande = in.readLine().trim();
			BooksModel books = lib.getBooks(commande);
			if (books != null) {
				int i = 0;
				BookModel[] book = books.getBooks();

				for (BookModel b : book) {
					if (b.getTitle() != null)
						show(new DataObject(i + "- " + b.getTitle()));
					else
						show(i + "- No Title");
					i++;
				}
				show(new DataObject("Enter a book number for info"));
				commande = in.readLine();
				int r = Integer.parseInt(commande);
				show(books.getBooks()[r]);
			} else if (!commande.equals("exit"))
				show(new DataObject("No book for this author"));
		} while (!commande.equals("exit"));
	}

	private void show(Object str) {
		setChanged();
		notifyObservers(str);
	}

	public static void main(String[] args) throws IOException,
			InvalidKeyException {
		if (args.length == 0) {
			System.out.println("Veuillez donner le nom du fichier ˆ traiter");
			System.exit(0);
		}
		new Controller().start(args[0]);
	}

}
