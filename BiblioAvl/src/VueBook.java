import java.util.Observable;
import java.util.Observer;

public class VueBook implements Observer {

	@Override
	public void update(Observable obj, Object arg) {
		String resp;
		if (arg instanceof BookModel) {
			resp = arg.toString();
			System.out.println("******Book******");
			System.out.println(resp);
		}

	}

}
