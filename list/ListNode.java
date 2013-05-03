/* ListNode.java */

package list;

/**
 *  A ListNode is a mutable node in a list.
 **/

public abstract class ListNode {

  /**
   *  item references the item stored in the current node.
   *  myList references the List that contains this node.
   */

  protected Object item;
  protected List myList;

  /**
   *  isValidNode returns true if this node is valid; false otherwise.
   *  By default, an invalid node is one that doesn't belong to a list (myList
   *  is null), but subclasses can override this definition.
   *
   *  @return true if this node is valid; false otherwise.
   *
   *  Performance:  runs in O(1) time.
   */
  public boolean isValidNode() {
    return myList != null;
  }

  /**
   *  item() returns this node's item.
   *
   *  @return the item stored in this node.
   *
   *  Performance:  runs in O(1) time.
   */
  public Object item() {
    return item;
  }

  /**
   *  setItem() sets this node's item to "item".
   *
   *  Performance:  runs in O(1) time.
   */
  public void setItem(Object item) {
    this.item = item;
  }

  /**
   *  next() returns the node following this node.
   *
   *  @return the node following this node.
   */
  public abstract ListNode next();

  /**
   *  prev() returns the node preceding this node.
   *
   *  @param node the node whose predecessor is sought.
   *  @return the node preceding this node.
   */
  public abstract ListNode prev();

  /**
   *  insertAfter() inserts an item immediately following this node.
   *
   *  @param item the item to be inserted.
   */
  public abstract void insertAfter(Object item);

  /**
   *  insertBefore() inserts an item immediately preceding this node.
   *
   *  @param item the item to be inserted.
   */
  public abstract void insertBefore(Object item);

  /**
   *  remove() removes this node from its List.
   */
  public abstract Object remove();

}
