/* DListNode.java */

package list;

/**
 *  A DListNode is a mutable node in a DList (doubly-linked list).
 **/

public class DListNode extends ListNode {

  /**
   *  (inherited)  item references the item stored in the current node.
   *  (inherited)  myList references the List that contains this node.
   *  prev references the previous node in the DList.
   *  next references the next node in the DList.
   **/

  protected DListNode prev;
  protected DListNode next;

  public DListNode(Object i, DList l){
	  item = i;
	  myList = l;
  }
  /**
   *  DListNode() constructor.
   *  @param i the item to store in the node.
   *  @param l the list this node is in.
   *  @param p the node previous to this node.
   *  @param n the node following this node.
   */
  DListNode(Object i, DList l, DListNode p, DListNode n) {
    item = i;
    myList = l;
    prev = p;
    next = n;
  }

  /**
   *  isValidNode returns true if this node is valid; false otherwise.
   *  An invalid node is represented by a `myList' field with the value null.
   *  Sentinel nodes are invalid, and nodes that don't belong to a list are
   *  also invalid.
   *
   *  @return true if this node is valid; false otherwise.
   *
   *  Performance:  runs in O(1) time.
   */
  public boolean isValidNode() {
    return myList != null;
  }

  /**
   *  next() returns the node following this node.
   *
   *  @return the node following this node.
   *
   *  Performance:  runs in O(1) time.
   */
  public ListNode next(){ 
    return next;
  }

  /**
   *  prev() returns the node preceding this node.
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node preceding this node.
   *
   *  Performance:  runs in O(1) time.
   */
  public ListNode prev() { 
    return prev;
  }

  /**
   *  insertAfter() inserts an item immediately following this node. 
   *
   *  @param item the item to be inserted.
   *
   *  Performance:  runs in O(1) time.
   */

  public void insertAfter(Object item){
    DListNode inserted = ((DList)myList).newNode(item, (DList) this.myList, this, this.next);
	  this.next.prev = inserted;
	  this.next = inserted;
	  myList.size++;
  }

  /**
   *  insertBefore() inserts an item immediately preceding this node.
   *
   *  @param item the item to be inserted.
   *
   *  Performance:  runs in O(1) time.
   */
  public void insertBefore(Object item){ 

    DListNode inserted = ((DList) myList).newNode(item,(DList) myList, this.prev, this);
	  this.prev.next = inserted;
	  this.prev = inserted;
	  myList.size++;
  }

  /**
   *  remove() removes this node from its DList. 
   *
   *  Performance:  runs in O(1) time.
   */
  public Object remove() { 
	  this.prev.next = this.next;
	  this.next.prev = this.prev;
	  myList.size--;

    myList = null;
    next = null;
    prev = null;
    return this.item();
  }
  
  public DList myList() {
	  return (DList) myList;
  }
}
