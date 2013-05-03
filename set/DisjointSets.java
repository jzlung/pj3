/* DisjointSets.java */

package set;

/**
 *  A disjoint sets ADT.  Performs union-by-size and path compression.
 *  Implemented using arrays, stores the parent reference or the negative number of items if root.
 *  Elements are represented by ints, numbered from zero.
 **/

public class DisjointSets {

	private int[] array;

	/**
	 *  Construct a disjoint sets object.
	 *
	 *  @param numElements the initial number of elements--also the initial
	 *  number of disjoint sets, since every element is initially in its own set.
	 **/
	public DisjointSets(int numElements) {
		array = new int [numElements];
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
	}

	/**
	 *  union() unites two disjoint sets into a single set.  A union-by-size
	 *  heuristic is used to choose the new root. 
	 *
	 *  @param root1 the root of the first set.
	 *  @param root2 the root of the other set.
	 **/
	public void union(int root1, int root2) {	  
		if (root1 == root2 || (find(root1) == find(root2) && find(root1) >= 0 && find(root2) >= 0)) { 
			return;
		}
		if(root1 >= 0) {									//	If they are not roots, make them their respective roots. 
			root1 = find(root1);
		}
		if(root2 >= 0) {
			root2 = find(root2);
		}

		if(root1 == root2) {
			return;
		}
		if (array[root2] < array[root1]) {            		     // root2 has larger tree
			array[root2] += array[root1];        				// update # of items in root2's tree
			array[root1] = root2;                              // make root2 new root
		} else {                                			  // root1 has equal or larger tree
			array[root1] += array[root2];       			 // update # of items in root1's tree
			array[root2] = root1;                           // make root1 new root
		}
	}

	/**
	 *  find() finds the (int) name of the set containing a given element.
	 *  Performs path compression along the way.
	 *
	 *  @param x the element sought.
	 *  @return the set containing x.
	 **/
	public int find(int x) {
		if (array[x] < 0) {
			return x;                         // x is the root of the tree; return it
		} else {
			// Find out who the root is; compress path by making the root x's parent.
			array[x] = find(array[x]);
			return array[x];                                       // Return the root
		}
	}

}