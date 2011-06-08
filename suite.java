


class Suite{
// 1
//11
//21
//1211
//111221
//312211
//13112221
//1113213211
//...


//Definition du problème:
//On doit creer une fonction qui représente cette suite.
//On doit donc stocker la suite d'avant pour pouvoir imprimer la nouvelle.
//On doit ensuite compter le nombre d'occurence qui se suivent d'un chiffre
//Ensuite on doit imprimer ce nombre d'occurences puis imprimer le chiffre
//et ainsi de suite.

//Decomposition en sous-problèmes:
//	- fonction qui compte le nombre d'occurences successives a partir d'une position donnée et renvoie ce nbre
//		int countSuccOcc(char[] a, int pos)
//	- fonction principale
//		



	private static int countSuccOcc(char[] a, int pos){
		//PRE: a != NULL et a.length!=0 && 0<=pos<a.length
		//POST: a est inchangé, pos est inchangé
		//RESULTAT: Renvoie le nombre d'occurences successives de la lettre

		//INIT
		int count=0;
		int curPos= pos;
		char theChar = a[pos];
		//INV: 0<=count<a.length && 0<=curPos<a.length && count contient le nombre de caracteres rencontrés qui sont les meme que theChar
		//H curPos==a.length || a[curPos] != theChar 
		while(curPos<a.length && a[curPos] == theChar){
			count++;
			curPos++;
		}
		return count;
	}

	public static void start(int nbLevel){
		//PRE: NONE
		//POST: NONE
		//RESULTAT: imprime la suite

		//INIT:
		char[] a= new char[]{'1'};
		int curLevel = 0;

		//INV: curLevel<=nbLevel && a contient l'impresion du level précedent 
		//H: curLevel = nbLevel

		while(curLevel != nbLevel){
			//INIT
			int curPos = 0;
			char[] temp = new char[a.length*2];
			int tempLength = 0;
			//INV: 0<=curPos<=a.length 0=<tempLength<a.length*2
			//H:curPos>=a.length 
			while(curPos<a.length){
				int nb = countSuccOcc(a, curPos);
				char cNb[] = (Integer.toString(nb)).toCharArray();
				//INV: 0=<i<nb.length && nb[i] == temp[curPos+i] && 0<=tempLength<temp.length 
				//H: i==nb.length
				int i=0;
				while(i<cNb.length){
					temp[tempLength++] = cNb[i];
					i++;
				}
				temp[tempLength++] = a[curPos];
				curPos+= nb;
			}
			char[] temp2 = new char[tempLength];
			//INIT
			int k=0;
			//INV: 0<=k<temp.length 
			//H: k==tempLength
			while(k<tempLength){
				temp2[k] = temp[k];
				k++;
			}
			System.out.println(temp2);
			a = temp2;
			curLevel++;
		}
	}

	public static void main(String[] args){
		start(50);
	}


}


