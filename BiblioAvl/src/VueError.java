import java.util.Observable;
import java.util.Observer;

public class VueError implements Observer {

	@Override
	public void update(Observable obj, Object arg) {
		String resp;
		if (arg instanceof ErrorObject) {
			printError((ErrorObject) arg);
		} else if (arg instanceof DataObject)
			printData((DataObject) arg);
	}

	private void printData(DataObject arg) {
		String s = "";
		for (int i = 0; i < arg.getMessage().length() + 4; i++)
			s += "*";
		System.out.println(s);
		System.out.println("* " + arg.getMessage() + " *");
		System.out.println(s);
	}

	private void printError(ErrorObject arg) {
		System.out.println("* " + arg.getMessage() + " <Line " + arg.getLine()
				+ "> *");
	}

}
