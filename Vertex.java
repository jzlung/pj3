import graph.*;

public class Vertex {
	protected Object object;
	protected Neighbors neighbor;
	protected int d;
	
	protected int uniqueInteger;

	public Vertex(Object v, Neighbors n) {
		object = v;
		neighbor = n;
		d = n.neighborList.length;
	}
	
	public Neighbors neighbors() {
		return neighbor;
	}
	
	public Vertex returnVertex() {
		return (Vertex) object;
	}
	
	public void setInt(int i) {
		uniqueInteger = i;
	}
	
	public int getInt(int i) {
		return uniqueInteger;
	}
}
