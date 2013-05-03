package list;

public class ListEntry{
	public Object vertex;
	public DList adjList;
	
	/*
	 * One param constructor initializes this object, meant for the vertex list. The adjacency list is initialized to be an empty DList.
	 */
	public ListEntry(Object v){
		vertex = v;
		adjList = new DList();
	}
}
