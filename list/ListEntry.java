package list;

public class ListEntry{
	public Object vertex;
	public DList adjList;
	
	public ListEntry(Object v){
		vertex = v;
		adjList = new DList();
	}
	
	public ListEntry(Object v, DList l){
		this(v);
		adjList = l;
	}
}
