package avltreepack;

public class SimpleEntry<K,V> implements Entry<K, V> {
	
	private K key;
	private V value;
	
	public SimpleEntry()
	{
		key = null;
		value = null;
	}
	
	public SimpleEntry(K key, V value)
	{
		this.key=key;
		this.value=value;
	}
	
	public K getKey()
	{
		return key;
	}
	
	public V getValue()
	{
		return value;
	}

}
