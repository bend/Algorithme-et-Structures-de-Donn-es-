package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

	public static String[] lireContenu(String nomFichier) throws IOException {

		BufferedReader in = new BufferedReader(new java.io.FileReader(
				nomFichier));
		ArrayList<String> liste = new ArrayList<String>();
		String ligne;
		while ((ligne = in.readLine()) != null) {
			liste.add(ligne);
		}
		in.close();
		String tab[] = new String[liste.size()];
		for (int i = 0; i < tab.length; i++) {
			tab[i] = liste.get(i);
		}
		return tab;
	}

	public static void ecrireContenu(String nomFichier, String contenu)
			throws IOException {
		BufferedWriter outFile = new BufferedWriter(new FileWriter(nomFichier,
				true));
		outFile.write(contenu);
		outFile.newLine();
		outFile.close();
	}
}
