package kruskalmaterials;
import graph.*;

public class Vertex {
	public Object object;
	public Neighbors neighbor;
	public int uniqueInteger;

	/*
	 * Two parameter constructor for the container.
	 */
	public Vertex(Object v, Neighbors n) {
		object = v;
		neighbor = n;
	}
}
