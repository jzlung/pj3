/* SListNode.java */

package kruskalmaterials;

/**
 *  An SList object is a singly-linked list, and an SListNode is a node of a singly-linked
 *  list.  Each SListNode has two references:  one to an object, and one to
 *  the next node in the list. In our project, the SListNode is used only in Queues. 
 */

class SListNode {
  Object item;
  SListNode next;

  /**
   *  SListNode() (with one parameter) constructs a list node referencing the
   *  item "obj". Since we are using Queues, the next field when you create an SListNode object is always null.
   */

  SListNode(Object obj) {
    item = obj;
    next = null;
  }

}
