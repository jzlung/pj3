package list;

public class HashEntry{
	protected Object key;
	protected int value;
	protected DListNode node;
	
	public HashEntry(Object k, int v){
		key = k;
		value = v;
	}
	public HashEntry(DListNode v){
		node = v;
	}
	
	public Object key(){
		return key;
	}
	
	public int value(){
		return value;
	}
	
	public DListNode node(){
		return node;
	}
	
	public void setNode(DListNode v){
		node = v;
	}
	
}
