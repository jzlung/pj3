package list;

public class AdjEntry{
	/*
	 * Container for adjacency list's ListNodes' item fields
	 * @partner points to the corresponding ListNode in the other vertex's adjacency list, as detailed in the project diagram given.
	 * @start points to the ListNode in the vertex list of the first vertex in the edge
	 * @end points to the ListNode in the vertex list of the second vertex in the edge
	 */
	
	public DListNode partner;
	public DListNode start;
	public DListNode end;
	
	/* Two param constructor for Adjacency List Entry container
	 * @param s is the vertex whose adjacency list you are working with
	 * @param e is the vertex who the original is linking to
	 */
	
	public AdjEntry(DListNode s, DListNode e){
		start = s;
		end = e;
	}
	
	/* Three-param constructor calls the two-param constructor
	 * @param p is the partner half-edge reference in another adjacency list,
	 * 		used when removing edges/vertices
	 */
	
	public AdjEntry(DListNode p, DListNode s, DListNode e){
		this(s,e);
		partner = p;
	}
}
