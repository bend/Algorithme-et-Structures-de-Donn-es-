package avltreepack;

public interface Dictionary<K,V> {
	
	/*
	 * Return the number of entries in D
	 */
	public int size();
	
	/*
	 * Test whether D is empty
	 */
	public boolean isEmpty();
	
	/*
	 * If D contains an entry with key equal to k
	 * then return such an entry, else return null
	 */
	public Entry<K,V> get(K key) throws InvalidKeyException;
	
	/*
	 * Return an iterable collection containing all entries with key equal to k
	 */
	public Iterable<Entry<K,V>> getAll(K key) throws InvalidKeyException;
	
	/*
	 * Insert an entry with key k and value v into D, returning the entry crated
	 */
	public Entry<K,V> put(K key, V value);
	
	
	/*
	 * Return an iterable collection of the key-value entries in D
	 */
	public Iterable<Entry<K,V>> entrySet(); 
	
	
	

}
