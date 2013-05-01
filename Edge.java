
public class Edge {
	protected Object vertex1;
	protected Object vertex2;
	protected int weight;
	
	public int weight() {
		return weight;
	}
	
	public Object vertex1() {
		return vertex1;
	}
	
	public Object vertex2() {
		return vertex2;
	}
	
	public Edge(Object v1, Object v2, int weighted) {
		this.vertex1 = v1;
		this.vertex2 = v2;
		weight = weighted;
	}
	
	public String toString() {
		String result = "from " + vertex1 + " to " + vertex2 + " with weight " + weight;
		return result;
	}
}
