package list;

public class HashEntry{
	protected Object key;
	protected Object value;
	protected DListNode vertex;
	
	public HashEntry(Object k, Object v){
		key = k;
		value = v;
	}
	public HashEntry(DListNode v){
		vertex = v;
	}
	
	public Object key(){
		return key;
	}
	
	public Object value(){
		return value;
	}
	
}
