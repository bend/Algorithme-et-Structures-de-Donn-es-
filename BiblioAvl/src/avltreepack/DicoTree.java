package avltreepack;


public class DicoTree<K, V> implements Dictionary<K, V> {

	private AVLTree<K, V> superRoot;

	public DicoTree() {
		superRoot = new AVLTree<K, V>(null);
	}

	public AVLTree<K, V> superRoot() {
		return superRoot;
	}

	public void setSuperRoot(AVLTree<K, V> tree) {
		superRoot = tree;
	}

	/*
	 * Return the number of entries in D
	 */
	@Override
	public int size() {
		return superRoot.size();
	}

	/*
	 * Test whether D is empty
	 */
	@Override
	public boolean isEmpty() {
		return superRoot.isEmpty();
	}

	/*
	 * If D contains an entry with key equal to k then return such an entry,
	 * else return null
	 */
	@Override
	public Entry<K, V> get(K key) throws InvalidKeyException {
		return superRoot.get(key);
	}

	/*
	 * Return an iterable collection containing all entries with key equal to k
	 */
	@Override
	public Iterable<Entry<K, V>> getAll(K key) throws InvalidKeyException {
		return superRoot.getAll(key);
	}

	/*
	 * Insert an entry with key k and value v into D, returning the entry crated
	 */
	@Override
	public Entry<K, V> put(K key, V value) {
		return superRoot.put(key, value, this);
	}

	/*
	 * Return an iterable collection of the key-value entries in D
	 */
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return superRoot.entrySet();
	}

	/*
	 * public static void main(String[]args) { DicoTree<String,String> test =
	 * new DicoTree<String,String>(); Entry<String,String> osef = new
	 * SimpleEntry<String,String>(); osef =test.put("jean", "a"); osef
	 * =test.put("aean", "a"); System.out.println("bam : "+
	 * test.superRoot.root().getKey()); osef =test.put("bean", "a");
	 * System.out.println("bim : "+ test.superRoot.root().getKey()); osef =
	 * test.put("vean", "a"); osef = test.put("dean", "a"); osef =
	 * test.put("aean", "a"); osef = test.put("dean", "a"); osef =
	 * test.put("zean", "a");System.out.println("ici"); osef = test.put("zean",
	 * "a"); System.out.println("1 : "+ test.superRoot.root().getKey());
	 * System.out.println("2 : "+ test.superRoot.rightTree().root().getKey());
	 * System.out.println("3 : "+
	 * test.superRoot.rightTree().rightTree().getHeight());
	 * System.out.println("4 : "+
	 * test.superRoot.rightTree().rightTree().leftTree().getHeight()); osef =
	 * test.put("zean", "a"); osef = test.put("zean", "a");
	 * Iterable<Entry<String,String>> yeah = new
	 * LinkedList<Entry<String,String>>(); yeah = test.entrySet();
	 * Iterator<Entry<String,String>> yea = yeah.iterator();
	 * 
	 * while (yea.hasNext()) { Entry<String,String> elem = yea.next();
	 * System.out.println(elem.getKey()); } //System.out.println("next"); yeah =
	 * new LinkedList<Entry<String,String>>(); try {yeah = test.getAll("jean");}
	 * catch(Exception e){System.out.println("ouille");} yea = yeah.iterator();
	 * 
	 * while (yea.hasNext()) { Entry<String,String> elem = yea.next();
	 * //System.out.println(elem.getKey()); }
	 * System.out.println(test.superRoot().getHeight());
	 * 
	 * }
	 */

}
