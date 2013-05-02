package list;

public class ListEntry{
	public Object vertex;
	public DList adjList;
	
	public ListEntry(Object v){
		vertex = v;
	}
	
	public ListEntry(Object v, DList l){
		this(v);
		adjList = l;
	}
}
