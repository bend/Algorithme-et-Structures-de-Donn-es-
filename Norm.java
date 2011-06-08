class Norm{

public static int[] nbSpaceAndBlanc(char[] a){
	//PRE a n'est pas vide && a !=NULL && a.length>0 && a contient au moint 2 mots
	//POST: a est inchangé
	//RESULTAT: la fonction retourne un tableau int[nbSpace, nbBlanc]
	//INIT:
	int i = 0;
	int nbWords = 0;
	int nbBlanc = 0;
	if (a[0]==' ') nbWords= 0; else nbWords = 1;

	//INV: 0=<i<a.length-1 && nbWords contient le nombre de mots dans a[0..i]
	//	   && nbBlanc contient le nombre de cases vides dans a[0..i]
	
	// H: i=a.length
	while (i!=a.length - 1) {
		if (a[i] == ' ')
			nbBlanc++;
		if (a[i]==' ' & a[i + 1]!=' ' ) 
			nbWords++;
		i++; 
	}
	return new int[]{nbWords-1, nbBlanc};
}

public static int countNbSpace(char[] a, int i){
	//PRE: a!=Null et a.length>0
	//	  0<=i<a.length
	//POST: a inchangé et from, to inchangé
	//RESULTAT: Renvoie le nombre de blancs entre i et le mot avant i
	//H: 
	int j = i-1;
	int nb = 0;
	//INV: nb contient le nombre d'espaces dans a[j..i]
	//H: j>0 && a[j] !=' '
	while(j>0 && a[j] == ' '){
		j--;
		nb++;
	}
	return nb;
}

public static void shiftLeft(char[] a , int from, int to, int nb){
	//PRE: a != NULL && a.length != 0 && 0<=from<to<a.length && 0<nb<=NBP+1
	//POST: a[from-nb..to-nb] est remplacé par a[from..to] 
	
	//Init:
		int i = from;
	
	//INV: 0=<i<a.length && a[i] ==' ' && from=<i<=to
	//H: i == to
	
	while(i!= to){
		a[i-nb] = a[i];
		a[i] =' ';
		i++;
	}

}

public static void shiftRight(char[] a, int from, int to, int nb){
	//PRE: a != NULL && a.length != 0 && 0<=from<to<a.length && 0<nb<=NBP+1
	//POST: a[from+nb..to+nb] est remplacé par a[from..to] 
	//Init:
	int i = to - nb;
	//INV: 0<=i<a.length && a[i] == ' '  && from=<i<=to
	
	//H: i==from
	while(i!=from){
		a[i+nb] = a[i];
		a[i] =' ';
		i--;
	}
}

public static char[] Normalize(char[] a){
	//PRE: a !=NULL & a.length!=0 & a contient au moins 1 mot 
	//POST: a est normalize.

	int[] tab =  nbSpaceAndBlanc(a);
	int nbSpace = tab[0];
	int nbBlanc = tab[1];


	//Premiere étape: Mettre tout a droite en laissant 1 espace entre chaque mot
	//2eme étape: Parcourir les mots et rajouter des espaces la ou il faut
	//Init: 

	
	//Enlever tous les blancs du début 
	
	//INV: a[n] == ' ' && 0=<a.length && a[0..n] == ' '
	//H: a[n] != ' ' || n=a.length)
	if(a[0] == ' '){
		int n=0;
		while(a[n] == ' ' && n!=a.length)
			n++;
		shiftLeft(a, n,a.length,n);
	}

	//INIT
	int i = a.length-1;
	//INV: a[i+1..a.length] est pre-normalisé; cad il y a 1 espace entre chaque mot &&
	//H: i==0
	while(i!=1){
		if(a[i] != ' ' && a[i-1]==' '){
			int s = countNbSpace(a,i);
			if(s>1)
				shiftLeft(a, i-1, a.length, s-1);
			i= i-(s+1);
		}else i--;
	}


	//Init
	i = a.length-1;
	int nbplus=0;
	int nbbps = nbBlanc/nbSpace;
	
	//INV: a[i+1..a.length]  est normalisé  && 1<i<a.length
	//H: i==0;
	boolean needMore = (nbbps*nbSpace != nbBlanc);
	while(i!=1){
		if(a[i] != ' ' && a[i-1]==' '){
			if(needMore){
				shiftRight(a, i-1, a.length-1, nbbps);
				nbplus++;
			  	needMore = ((nbbps*nbSpace) + nbplus != nbBlanc);
			}else
				if(nbbps>1)
					shiftRight(a, i-1, a.length-1 ,nbbps-1);
		}
		i--;
	}
	return a;
}

public static void main(String[] args){
	char[] i =  "testtes test".toCharArray();
	System.out.println(Normalize(i));
}

}

