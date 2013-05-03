package list;

public class HashEntry{
	
	/*
	 * HashEntry is a container for DListNode's item field when DListNodes are used for hash tables.
	 */
	public Object key;
	public int value;
	public DListNode node;
	
	/*
	 * Two param constructor sets a key and a value. 
	 */
	public HashEntry(Object k, int v){
		key = k;
		value = v;
	}
	
}
