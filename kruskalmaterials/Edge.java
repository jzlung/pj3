package kruskalmaterials;

public class Edge {
	
	/*
	 * The Edge class stores the weight of the edge as well as both incident vertices.
	 * This edge is weighted and undirected, so the order of the two vertices is arbitrary.
	 * @vertex1 is first vertex
	 * @vertex2 is second vertex
	 * @weight is the weight of the edge (value significant only to the outside calling application)
	 */
	public Object vertex1;
	public Object vertex2;
	public int weight;
	
	/*
	 * Three param container contains the two vertices of the undirected edge and its weight.
	 * @v1 is one vertex
	 * @v2 is the other vertex
	 * @weighted is the weight of the edge
	 */
	public Edge(Object v1, Object v2, int weighted) {
		this.vertex1 = v1;
		this.vertex2 = v2;
		weight = weighted;
	}
	
}
