package interpreter;

import operations.OperatorModel;

public interface FactoryModel {
	/**
	 * Cette m�thode permet de retourner un objet qui correspond a l'operation
	 * donn�e
	 * 
	 * @param nextToken
	 *            String contenant l'operation
	 * @return l'instance de l'operation correspondante au token
	 */
	public OperatorModel giveInstance(String nextToken);

}
