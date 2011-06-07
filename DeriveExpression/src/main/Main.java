package main;

import java.io.IOException;

import tree.ExpressionTree;
import tree.FormalExpressionTree;
import IO.FileIO;
import exception.ExpressionException;

public class Main {

	private void start(String inputfilename) {

		String[] contenu = null;
		try {
			contenu = FileIO.lireContenu(inputfilename);
		} catch (IOException e) {
			System.out.println("Fichier non trouvé");
			System.exit(0);
		}
		for (int i = 0; i < contenu.length; i++) {
			FormalExpressionTree tree = null;
			if (contenu[i].isEmpty())
				continue;
			else
				try {
					tree = new ExpressionTree(contenu[i]);
					System.out.println("Expression : " + tree);
					System.out.println("Dérivée : " + tree.derive());
				} catch (ExpressionException e) {
					System.out.println(e.getMessage());
				}

		}

	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Veuillez donner le nom du fichier à traiter");
			System.exit(0);
		}
		new Main().start(args[0]);
	}

}
