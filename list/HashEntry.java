package list;

public class HashEntry{
	
	/*
	 * HashEntry is a container for DListNode's item field when DListNodes are used for hash tables. 
	 * HashEntry is the item for hash table entries (DListNode.item), and stores pointers to the corresponding ListNode 
	 * (Vertex list ListNodes for the vertex hash table, and Adjacency List ListNodes for the edge hash table), 
	 * as well as the key and value. For vertices and edges, the key is simply the Object or VertexPair 
	 * that was passed in initially, and the value represents the weight of the edge (the values for vertices are set to -infinity). 
	 */
	public Object key;
	public int value;
	public DListNode node;
	
	/*
	 * Two param constructor sets a key and a value. 
	 * @k is the object itself, functioning as its own key
	 * @v is an integer mapping value
	 */
	public HashEntry(Object k, int v){
		key = k;
		value = v;
	}
	
}
