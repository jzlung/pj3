package kruskalmaterials;

public class Edge {
	public Object vertex1;
	public Object vertex2;
	public int weight;
	
	/*
	 * Three param container contains the two vertices of the undirected edge and its weight
	 */
	public Edge(Object v1, Object v2, int weighted) {
		this.vertex1 = v1;
		this.vertex2 = v2;
		weight = weighted;
	}
	
}
