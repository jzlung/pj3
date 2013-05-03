/* WUGraph.java */

package graph;
import dict.*;
import list.*;

/**
 * The WUGraph class represents a weighted, undirected graph.  Self-edges are
 * permitted.
 */

public class WUGraph {
	private HashTableChained vTable;			//Hash table of Vertices
	private HashTableChained eTable;			//Hash table of Edges
	private DList vList;							//DList of Vertices

	/**
	 * WUGraph() constructs a graph having no vertices or edges.
	 *
	 * Running time:  O(1).
	 */
	public WUGraph(){
		vTable = new HashTableChained();
		eTable = new HashTableChained();
		vList = new DList();
	}

	/**
	 * vertexCount() returns the number of vertices in the graph.
	 *
	 * Running time:  O(1).
	 */
	public int vertexCount(){
		return vList.length();
	}

	/**
	 * edgeCount() returns the number of edges in the graph.
	 *
	 * Running time:  O(1).
	 */
	public int edgeCount(){
		return eTable.size();
	}

	/**
	 * getVertices() returns an array containing all the objects that serve
	 * as vertices of the graph.  The array's length is exactly equal to the
	 * number of vertices.  If the graph has no vertices, the array has length
	 * zero.
	 *
	 * (NOTE:  Do not return any internal data structure you use to represent
	 * vertices!  Return only the same objects that were provided by the
	 * calling application in calls to addVertex().)
	 *
	 * Running time:  O(|V|).
	 */
	public Object[] getVertices(){
		int l = vList.length();
		DListNode curr = (DListNode) vList.front();
		Object[] vertices = new Object[l];
		for (int i = 0; i < l; i++){
			ListEntry x = (ListEntry) curr.item();
			vertices[i] = x.vertex;
			curr = (DListNode) curr.next();
		}
		return vertices;
	}

	/**
	 * addVertex() adds a vertex (with no incident edges) to the graph.  The
	 * vertex's "name" is the object provided as the parameter "vertex".
	 * If this object is already a vertex of the graph, the graph is unchanged.
	 *
	 * Running time:  O(1).
	 */
	public void addVertex(Object vertex){
		HashEntry h = vTable.insert(vertex, Integer.MIN_VALUE);
		if (h != null){
			ListEntry newEntry = new ListEntry(vertex);
			vList.insertBack(newEntry);
			h.node = ((DListNode) vList.back());
		}
	}

	/**
	 * removeVertex() removes a vertex from the graph.  All edges incident on the
	 * deleted vertex are removed as well.  If the parameter "vertex" does not
	 * represent a vertex of the graph, the graph is unchanged.
	 *
	 * Running time:  O(d), where d is the degree of "vertex".
	 */
	public void removeVertex(Object vertex){

		HashEntry h = vTable.find(vertex);

		if (h != null){
			DListNode vNode = h.node;

			vTable.remove(h.key);

			DList allEdges = ((ListEntry) vNode.item()).adjList;

			if (allEdges.length() != 0){
				DListNode curr = (DListNode) allEdges.front();
				int counter = 0;
				while (counter < allEdges.length()){

					DListNode s = ((AdjEntry) curr.item()).start;
					DListNode e = ((AdjEntry) curr.item()).end;
					Object u = ((ListEntry) s.item()).vertex;
					Object v = ((ListEntry) e.item()).vertex;

					VertexPair vP = new VertexPair(u,v);
					eTable.remove((eTable.find(vP)).key); 
				
					if (((AdjEntry) curr.item()).partner != curr) {
						((AdjEntry) curr.item()).partner.remove();
					}
					curr = (DListNode) curr.next();
					counter++;
				}
			}
			vNode.remove();
		}

	}

	/**
	 * isVertex() returns true if the parameter "vertex" represents a vertex of
	 * the graph.
	 *
	 * Running time:  O(1).
	 */
	public boolean isVertex(Object vertex){
		HashEntry h = vTable.find(vertex);
		if (h == null){
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * degree() returns the degree of a vertex.  Self-edges add only one to the
	 * degree of a vertex.  If the parameter "vertex" doesn't represent a vertex
	 * of the graph, zero is returned.
	 *
	 * Running time:  O(1).
	 */
	public int degree(Object vertex){
		HashEntry h = vTable.find(vertex);
		if (h == null){
			return 0;
		}
		else {
			ListEntry e = (ListEntry) h.node.item();
			return e.adjList.length();
		}
	}

	/**
	 * getNeighbors() returns a new Neighbors object referencing two arrays.  The
	 * Neighbors.neighborList array contains each object that is connected to the
	 * input object by an edge.  The Neighbors.weightList array contains the
	 * weights of the corresponding edges.  The length of both arrays is equal to
	 * the number of edges incident on the input vertex.  If the vertex has
	 * degree zero, or if the parameter "vertex" does not represent a vertex of
	 * the graph, null is returned (instead of a Neighbors object).
	 *
	 * The returned Neighbors object, and the two arrays, are both newly created.
	 * No previously existing Neighbors object or array is changed.
	 *
	 * (NOTE:  In the neighborList array, do not return any internal data
	 * structure you use to represent vertices!  Return only the same objects
	 * that were provided by the calling application in calls to addVertex().)
	 *
	 * Running time:  O(d), where d is the degree of "vertex".
	 */
	public Neighbors getNeighbors(Object vertex){
		HashEntry h = vTable.find(vertex);
		if (h == null){
			return null;
		}
		if (((ListEntry) h.node.item()).adjList.length() == 0){
			return null;
		}
		else {
			int deg = degree(vertex);
			Neighbors n = new Neighbors();
			n.neighborList = new Object[deg];
			n.weightList = new int[deg];

			ListEntry l = (ListEntry) h.node.item();
			DListNode curr = (DListNode) l.adjList.front();

			for(int i = 0; i < deg; i++){
				n.neighborList[i] = ((ListEntry)((AdjEntry) curr.item()).end.item()).vertex; 
				n.weightList[i] = weight(vertex, n.neighborList[i]);
				curr = (DListNode) curr.next();
			}
			return n;
		}
	}

	/**
	 * addEdge() adds an edge (u, v) to the graph.  If either of the parameters
	 * u and v does not represent a vertex of the graph, the graph is unchanged.
	 * The edge is assigned a weight of "weight".  If the edge is already
	 * contained in the graph, the weight is updated to reflect the new value.
	 * Self-edges (where u == v) are allowed.
	 *
	 * Running time:  O(1).
	 */
	public void addEdge(Object u, Object v, int weight){
		HashEntry uu = vTable.find(u);
		HashEntry vv = vTable.find(v);
		if (uu != null && vv != null){

			VertexPair newPair = new VertexPair(u,v);
			HashEntry h = eTable.find(newPair);

			if (h == null){
				h = eTable.insert(newPair, weight);

				DListNode U = uu.node;
				DListNode V = vv.node;
				AdjEntry add = new AdjEntry(U,V);
				AdjEntry add1 = new AdjEntry(V,U);

				if (U == V){
					((ListEntry) U.item()).adjList.insertBack(add);
					h.node = ((DListNode) ((ListEntry) U.item()).adjList.back());

					DListNode UU = (DListNode) ((ListEntry) U.item()).adjList.back();
					((AdjEntry) UU.item()).partner = UU;
				}
				else {
					((ListEntry) U.item()).adjList.insertBack(add);
					((ListEntry) V.item()).adjList.insertBack(add1);

					h.node = ((DListNode) ((ListEntry) U.item()).adjList.back());

					DListNode UU = (DListNode) ((ListEntry) U.item()).adjList.back();
					DListNode VV = (DListNode) ((ListEntry) V.item()).adjList.back();
					((AdjEntry) UU.item()).partner = VV;
					((AdjEntry) VV.item()).partner = UU;
				}

			}
			else {
				h.value = weight;
			}
		}
	}

	public String arrayToString(int[] ints) {
		String result = " ";
		for (int i = 0; i < ints.length; i++) {
			result = result + ints[i] + " ";
		}
		return result;
	}

	/**
	 * removeEdge() removes an edge (u, v) from the graph.  If either of the
	 * parameters u and v does not represent a vertex of the graph, the graph
	 * is unchanged.  If (u, v) is not an edge of the graph, the graph is
	 * unchanged.
	 *
	 * Running time:  O(1).
	 */
	public void removeEdge(Object u, Object v) {
		VertexPair removal = new VertexPair(u, v);
		HashEntry found = eTable.find(removal);

		if (found != null) {
			DListNode firstVertex = found.node;
			DListNode secondVertex = ((AdjEntry) firstVertex.item()).partner;

			if(firstVertex == secondVertex){
				firstVertex.remove();
			}
			else {
				firstVertex.remove();
				secondVertex.remove();
			}
			eTable.remove(removal);
		}
	}


	/**
	 * isEdge() returns true if (u, v) is an edge of the graph.  Returns false
	 * if (u, v) is not an edge (including the case where either of the
	 * parameters u and v does not represent a vertex of the graph).
	 *
	 * Running time:  O(1).
	 */
	public boolean isEdge(Object u, Object v) {
		VertexPair checkEdge = new VertexPair(u,v);
		return eTable.find(checkEdge) != null;
	}

	/**
	 * weight() returns the weight of (u, v).  Returns zero if (u, v) is not
	 * an edge (including the case where either of the parameters u and v does
	 * not represent a vertex of the graph).
	 *
	 * (NOTE:  A well-behaved application should try to avoid calling this
	 * method for an edge that is not in the graph, and should certainly not
	 * treat the result as if it actually represents an edge with weight zero.
	 * However, some sort of default response is necessary for missing edges,
	 * so we return zero.  An exception would be more appropriate, but
	 * also more annoying.)
	 *
	 * Running time:  O(1).
	 */
	public int weight(Object u, Object v) {
		VertexPair findWeight = new VertexPair(u, v);
		HashEntry found = eTable.find(findWeight);
		if (found == null) {
			return 0;
		}
		return found.value;
	}


}
