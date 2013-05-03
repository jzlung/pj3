package kruskalmaterials;
import graph.*;

public class Vertex {
	
	/*
	 * Vertex class is a container that stores a references to:
	 *  @object is the original external vertex object that was passed in
	 *  @neighbor is a Neighbors object that keeps track of that vertex's connections
	 *  @field uniqueInteger is the integer "internal object"  to which the external object is mapped by the hash table; 
	 *  internally we deal with the vertices as integers for ease and DisjointSets purposes. 
	 */
	
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
