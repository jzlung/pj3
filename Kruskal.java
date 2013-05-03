/* Kruskal.java */

import graph.*;
import list.*;
import set.*;
import dict.*;
import kruskalmaterials.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

	/**
	 * minSpanTree() returns a WUGraph that represents the minimum spanning tree
	 * of the WUGraph g.  The original WUGraph g is NOT changed.
	 * 
	 * This method gets a list of the edges of the graph, sorts them, and adds them to the new graph until the graph is well-connected.
	 * @return a WUGraph that is the Kruskal's minimum spanning tree of the input graph
	 */
	public static WUGraph minSpanTree(WUGraph g) {
		WUGraph T = new WUGraph();
		int numVertices = g.vertexCount();
		Object[] gVertices = g.getVertices();		// Add all vertices to the new graph in O(E).
		for (int i = 0; i < gVertices.length; i++) {
			T.addVertex(gVertices[i]);
		}
		LinkedQueue allEdges = new LinkedQueue();				// Make LinkedQueue of all Edges in O(V + E log E).
		for (int j = 0; j < gVertices.length; j++) {		// Iterate through all vertices
			Neighbors current = g.getNeighbors(gVertices[j]);		// Pick a vertex
			for (int k = 0; k < current.neighborList.length; k++) {		// Iterate thru all its neighbors
				Edge insertThis = new Edge(gVertices[j], current.neighborList[k], current.weightList[k]);		// Create edge object to store the vertex, its neighbor, and its weight
				allEdges.enqueue(insertThis);		// Add it to the list of edges
			}
		}
		mergeSort(allEdges); 						// Sort the edges by weight in O(E log E).
		int sizeEstimate = 4 * numVertices / 3;
		HashTableChained htable = new HashTableChained(sizeEstimate);			// Map vertices to Hash Table, give them unique integer value for DJ, in O(V). 
		for (int m = 0; m < gVertices.length; m++) {
			htable.insert(gVertices[m], m);
		}
		DisjointSets V = new DisjointSets(numVertices);
		while (!allEdges.isEmpty()) {				// Iterate through/dequeue all the edges
			Edge curr = (Edge) allEdges.dequeue();
			int v1 = htable.find(curr.vertex1).value;
			int v2 = htable.find(curr.vertex2).value;
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
		return T;
	}


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
		while (!q1.isEmpty() && !q2.isEmpty()) {
			if (((Edge) q1.front()).weight < ((Edge) q2.front()).weight) {
				S.enqueue(q1.dequeue());
			} else if (((Edge) q1.front()).weight > ((Edge) q2.front()).weight) {
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
		q.append((LinkedQueue) qq.front());
	}


}