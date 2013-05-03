/* Kruskal.java */

import graph.*;
import list.*;
import set.*;
import dict.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

  /**
   * minSpanTree() returns a WUGraph that represents the minimum spanning tree
   * of the WUGraph g.  The original WUGraph g is NOT changed.
   * @return a WUGraph that is the Kruskal's minimum spanning tree of the input graph
   */
  public static WUGraph minSpanTree(WUGraph g) {
	  WUGraph T = new WUGraph();
	  int numVertices = g.vertexCount();
	  Object[] gVertices = g.getVertices();		// Add all vertices to the new graph in O(E).
	  for (int i = 0; i < gVertices.length; i++) {
		  T.addVertex(gVertices[i]);
	  }
	  System.out.println("gVertices is an object array of length: " + gVertices.length);
	  
	  LinkedQueue allEdges = new LinkedQueue();				// Make LinkedQueue of all Edges in O(V + E log E).
	  for (int j = 0; j < gVertices.length; j++) {		// Iterate through all vertices
		  Neighbors current = g.getNeighbors(gVertices[j]);		// Pick a vertex
		  for (int k = 0; k < current.neighborList.length; k++) {		// Iterate thru all its neighbors
			  Edge insertThis = new Edge(gVertices[j], current.neighborList[k], current.weightList[k]);		// Create edge object to store the vertex, its neighbor, and its weight
			  allEdges.enqueue(insertThis);		// Add it to the list of edges
		  }
	  }
	  System.out.println("allEdges: " + allEdges.toString());
	  
	  mergeSort(allEdges); 						// Sort the edges by weight in O(E log E). 
	  System.out.println("allEdges after mergeSort: " + allEdges.toString());
	  
	  int sizeEstimate = 4 * numVertices / 3;
	  HashTableChained htable = new HashTableChained(sizeEstimate);			// Map vertices to Hash Table, give them unique integer value for DJ, in O(V). 
	  for (int m = 0; m < gVertices.length; m++) {
		  Vertex insertMe = new Vertex(gVertices[m], g.getNeighbors(gVertices[m]));
		  htable.insert(gVertices[m], m);
	  }
	  System.out.println("htable with " + sizeEstimate + " buckets and " + htable.size() + " items");
	  
	  DisjointSets V = new DisjointSets(numVertices);
	  System.out.println("Disjoint Sets with all roots: " + V.toString());
	  
	  while (!allEdges.isEmpty()) {
		  Edge curr = (Edge) allEdges.dequeue();
		  System.out.println("current edge vertex1: " + curr.vertex1);
		  System.out.println("current edge vertex2: " + curr.vertex2);
		  int v1 = htable.find(curr.vertex1()).value();
		  System.out.println("v1: " + v1);
		  int v2 = htable.find(curr.vertex2()).value();
		  System.out.println("v2: " + v1);
		  if (v1 == v2 || ((V.find(v1) == V.find(v2)) && V.find(v1) >= 0 && V.find(v2) >= 0)) {
			  continue;
		  }
		  if (V.find(v1) != v2 || V.find(v2) != v1 || V.find(v1) != V.find(v2)) {
		  	V.union(v1, v2);
		  	if (V.find(v1) == v2 || V.find(v2) == v1 || V.find(v1) == V.find(v2)) {
		  		T.addEdge(curr.vertex1, curr.vertex2, curr.weight);
		  	}
		  }
	  }  
	  System.out.println("Disjoint set should have only one root: " + V.toString());
	  
	  return T;
  }					// End minSpanTree()
  
//  public String arrayToString(int[] ints) {
//	  String results = "DJSet: ";
//	  for (int i = 0; i < ints.length; i++) {
//		  results = results + ints[i] + " ";
//	  }
//	  return results;
//  }
  
  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
	  LinkedQueue Q = new LinkedQueue();
	  while(!q.isEmpty()){
		  LinkedQueue temp = new LinkedQueue();
		  temp.enqueue(q.dequeue());
		  Q.enqueue(temp);
	  }
    return Q;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    LinkedQueue S = new LinkedQueue();
    System.out.println(((Edge) q2.front()).weight());
    while (!q1.isEmpty() || !q2.isEmpty()) {
    	if(((Edge) q1.front()).weight() < (Edge) q2.front()).weight()){
//    		if (((Comparable) ((Edge) q1.front()).weight()).compareTo((Comparable) ((Edge) q2.front()).weight()) < 0 ) {
    			S.enqueue(q1.dequeue());
    		} else if (((Comparable) ((Edge) q1.front()).weight()).compareTo((Comparable) ((Edge) q2.front()).weight()) > 0 ) {
    			S.enqueue(q2.dequeue());
    		} else {
    			S.enqueue(q1.dequeue());
    	}
    }
    S.append(q1);
    S.append(q2);
    return S;
  }
  
  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
	  if (q.size() <= 1) {
		  return;
	  }
	  
	    LinkedQueue qq = makeQueueOfQueues(q);
	    	while (qq.size() > 1) {
	    		Object q1 = qq.dequeue();
	    		Object q2 = qq.dequeue();
	    		qq.enqueue(mergeSortedQueues((LinkedQueue) q1, (LinkedQueue) q2));
	    	}
//	    	q = ((LinkedQueue) qq).front();
	    	q.append((LinkedQueue) qq.front());
  }
  

}