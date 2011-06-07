import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LibReader extends BufferedReader {

	private int line = 0;

	public LibReader(java.io.Reader in) {
		super(in);
	}

	public LibReader(String filename) throws FileNotFoundException {
		this(new FileReader(filename));
	}

	@Override
	public String readLine() throws IOException {
		line++;
		return super.readLine();
	}

	public int getLine() {
		return line;
	}
}
