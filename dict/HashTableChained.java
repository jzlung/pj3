/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 **/

public class HashTableChained implements Dictionary {

	DList[] htable;
	protected int size;

	/** 
	 *  Constructs a new empty hash table intended to hold roughly sizeEstimate
	 *  entries. 
	 **/

	public HashTableChained(int sizeEstimate) {
		htable = new DList[sizeEstimate];
		size = 0;
		for (int i = 0; i < sizeEstimate; i++){
			htable[i] = new DList();
		}

	}

	/** 
	 *  Construct a new empty hash table with a default size of 13 (prime).
	 **/

	public HashTableChained() {
		htable = new DList[13];
		for (int i = 0; i < 13; i++){
			htable[i] = new DList();
		}
		size = 0;
	}

	/**
	 *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
	 *  to a value in the range 0...(size of hash table) - 1.
	 *
	 *  This function should have package protection and
	 *  should be used by insert, find, and remove.
	 **/

	int compFunction(int code) {
		return (Math.abs(9413*code + 439)%12981433) % htable.length;
	}



	/** 
	 *  Returns the number of entries stored in the dictionary.  Entries with
	 *  the same key (or even the same key and value) each still count as
	 *  a separate entry.
	 *  @return number of entries in the dictionary.
	 **/

	public int size() {
		return size;
	}

	public int hlength() {
		return htable.length;
	}

	/** 
	 *  Tests if the dictionary is empty.
	 *  @return true if the dictionary has no entries; false otherwise.
	 **/

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 *  Create a new HashEntry object referencing the input key and associated value,
	 *  and insert the entry into the dictionary.  Return a reference to the new
	 *  entry.  Multiple entries with the same key (or even the same key and
	 *  value) can coexist in the dictionary.
	 *
	 *  This method should run in O(1) time if the number of collisions is small.
	 *
	 *  @param key the key by which the entry can be retrieved.
	 *  @param value an arbitrary object.
	 *  @return an entry containing the key and value.
	 **/

	public HashEntry insert(Object key, int value) {
		if (find(key) == null || !find(key).key.equals(key)){
			if ((double)(size/htable.length) >= 0.75) {
				updateTable(htable.length*2);
			}
			HashEntry newEntry = new HashEntry(key, value);

			htable[compFunction(key.hashCode())].insertBack(newEntry);
			size++;
			return newEntry;
		}
		else {
			return null;
		}
	}

	/** 
	 *  Search for a HashEntry with the specified key.  If such an entry is found,
	 *  return it; otherwise return null.  If several entries have the specified
	 *  key, choose one arbitrarily and return it.
	 *
	 *  This method should run in O(1) time if the number of collisions is small.
	 *
	 *  @param key the search key.
	 *  @return an entry containing the key and an associated value, or null if
	 *          no entry contains the specified key.
	 **/

	public HashEntry find(Object key) {
		int h = key.hashCode();
		int hz = compFunction(h);

		DListNode pointer = (DListNode) htable[hz].front();

		for(int j = 0; j < htable[hz].length(); j++) {
			if(((HashEntry) pointer.item()).key.equals(key)){
				return ((HashEntry) pointer.item());    			
			}
			pointer = (DListNode) pointer.next();
		}
		return null;
	}

	/** 
	 *  Remove an entry with the specified key.  If such an entry is found,
	 *  remove it from the table and return it; otherwise return null.
	 *  If several entries have the specified key, choose one arbitrarily, then
	 *  remove and return it.
	 *
	 *  This method should run in O(1) time if the number of collisions is small.
	 *
	 *  @param key the search key.
	 *  @return an entry containing the key and an associated value, or null if
	 *          no entry contains the specified key.
	 */

	public HashEntry remove(Object key) {
		HashEntry h = find(key);

		if (h != null){
			if ((double)(size/htable.length) <= 0.25) {
				updateTable(htable.length/2);
			}
			int numTable = compFunction(key.hashCode());
			DListNode pointer = (DListNode) htable[numTable].front();
			for (int i = 0; i < htable[numTable].length(); i++){
				if(((HashEntry) pointer.item()).key.equals(key)){
					HashEntry removed = (HashEntry) pointer.item();
					pointer.remove();
					size--;
					return removed;	 			
				}
				pointer = (DListNode) pointer.next();
			}
		}
		return null;
	}


	/**
	 *  Remove all entries from the dictionary.
	 */
	public void makeEmpty() {
		htable = new DList[htable.length];
		size = 0;
	}

/*
 * updateTable() resizes the hash table to maintain a consistent load factor (number of entries divided by number of buckets). 
 * insert() will resize up to twice the current size if the load factor goes above 0.75 
 * remove() will resize down to half the current size if load factor goes below 0.25
 * However, these conditionals are implemented OUTSIDE of this method - this method simply creates a new table and rehashes the old entries in.
 * 
 * @param newSize is the size to which the hash table must be resized
 */
	private void updateTable(int newSize) {
		HashTableChained newTable = new HashTableChained(newSize);
		int counter = 0;
		for (DList bucket : htable) {
			DListNode curr = (DListNode) bucket.front();
			while (counter < bucket.length()) {
				HashEntry add = newTable.insert(((HashEntry)curr.item()).key, ((HashEntry)curr.item()).value);
				add.node = ((HashEntry)curr.item()).node;

				curr = (DListNode) curr.next();
				counter++;
			}
			counter = 0;
		}
		htable = newTable.htable;
		size = newTable.size;
	}

}
