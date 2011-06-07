package interpreter;

import operations.OperatorModel;

public class OperatorFactory implements FactoryModel {

	@Override
	public OperatorModel giveInstance(String nextToken) {
		String operation = "operations.Op"
				+ Character.toUpperCase(nextToken.charAt(0))
				+ nextToken.substring(1);
		try {
			return (OperatorModel) Class.forName(operation, true,
					this.getClass().getClassLoader()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
