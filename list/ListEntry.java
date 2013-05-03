package list;

public class ListEntry{
	public Object vertex;
	public DList adjList;
	
	public Object getVertex() {
		return vertex;
	}
	
	public ListEntry(Object v){
		vertex = v;
		adjList = new DList();
	}
	
	public ListEntry(Object v, DList l){
		this(v);
		adjList = l;
	}
}
