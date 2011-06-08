class BubleSort{
// Theorie du problème. 
// Soit un tableau d'entier non ordonné nommé a: et contenant des elements
// Le but de ce programme est d'ordonner de manière croissante ce tableau en effectuant des permutations.
// Soit i l'indice du ieme element du tableau, avec 0<=i<a.length. Si a[i]>a[i+1] alors on effectue une permuataation: a[i] = a[i+1] et a[i+1] = a[i].
//
// Précondition: le tableau est non vide et a une taille positive
// Postcondition: le tableau est ordonné de manière croissante
// 
// En-tête: static int[] perm(int[] a);
//
	static int[] perm(int[] a){
		//PRE: a.length != 0 && Pour tout 0=<i<a.length; a[i]!=null 
		//POST: Pour Tout 0=<i<a.length, a[i]<a[i+1] (le tableau est ordonné)
		// Resulat: renvoie le tableau ordonné de manière croissante
		
		//H: 
		int i=0;
		//INV: 0<=i<a.length&& a[i]<a[i+1]

		//H: i==a.length

		while(i<a.length-1){
			if(a[i]>a[i+1]){
				int temp = a[i];
				a[i] = a[i+1];
				a[i+1] = temp;
				i=0;
			}else i++;
		}
		return a;

	}
	public static void main(String[] args){
		int[] a = new int[]{1,3,7,2,4,9,8,6,8,3,5,6,7,8,4,5,2,3,7,3,5,7};
		a = perm(a);
		for(int i=0; i<a.length;i++)
			System.out.print(a[i]+" ");
			System.out.println("");
	}




}
