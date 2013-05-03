package list;

public class ListEntry{
	
	/*
	 * ListEntry is for the list of vertices, and stores pointers to the respective adjacency list 
	 * and the original vertex object the ListNode represents internally.
	 */
	public Object vertex;
	public DList adjList;
	
	/*
	 * One param constructor initializes this object, meant for the vertex list. The adjacency list is initialized to be an empty DList.
	 * @v is the vertex object that an adjacency list must be created for
	 */
	public ListEntry(Object v){
		vertex = v;
		adjList = new DList();
	}
}
