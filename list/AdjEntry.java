package list;

public class AdjEntry{
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
