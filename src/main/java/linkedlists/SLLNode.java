package linkedlists;

/**
 * A generic singly linked list node class that can store data of any type.
 * Each node contains data and a reference to the next node in the list.
 * 
 * @param <T> the type of data stored in this node
 * @author ICS211
 * @version 1.0
 */
public class SLLNode<T> {
    /** The data stored in this node */
    T data;               // data field
    /** Reference to the next node in the linked list */
    SLLNode<T> next;     // link field

    /**
     * Constructs a new SLLNode with the specified data and null next reference.
     * 
     * @param newData the data to be stored in this node
     */
    public SLLNode(T newData) {
        this.data = newData;
        this.next = null;
    }
    
    /**
     * Constructs a new SLLNode with the specified data and next node reference.
     * 
     * @param newData the data to be stored in this node
     * @param nextNode the next node to link to
     */
    public SLLNode(T newData, SLLNode<T> nextNode) {
        this.data = newData;
        this.next = (SLLNode<T>)nextNode;   
    }

    /**
     * Returns a string representation of the data stored in this node.
     * 
     * @return the string representation of the data
     */
    @Override
    public String toString() {
        return data.toString();
    }

    /**
     * Retrieves the data stored in this node.
     * 
     * @return the data stored in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Retrieves the reference to the next node in the linked list.
     * 
     * @return the next node, or null if this is the last node
     */
    public SLLNode<T> getNext() {
        return next;
    }
    
    /**
     * Sets the data for this node.
     * 
     * @param newData the new data to store in this node
     */
    public void setData(T newData) {
        this.data = newData;
    }
    
    /**
     * Sets the reference to the next node in the linked list.
     * 
     * @param nextNode the next node to link to, or null to make this the last node
     */
    public void setNext(SLLNode<T> nextNode) {
        this.next = nextNode;
    }

    /**
     * Driver method to test the SLLNode class functionality.
     * Tests the class using both String and Integer data types.
     * Demonstrates node creation, linking, and traversal.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // test with String data
        // create variables that can point to objects of class SLLNode
      System.out.println("Class SLLNode<String>:");
      SLLNode<String> head, tail;
   
   	// give each variable the address to an object
      String fruit1 = new String("apple");
      String fruit2 = new String("banana");
      head = new SLLNode<String>(fruit1);
      tail = new SLLNode<String>(fruit2);
   	// link the nodes together
      head.setNext(tail);
   
   	// print linked nodes
      SLLNode<String> pointer = head;
      String fruitX = pointer.getData();
      System.out.println("head = " + fruitX);
      pointer = pointer.getNext();
      fruitX = pointer.getData();
      System.out.println("tail = " + fruitX);
      System.out.println();
   
   	//How are these nodes linked together?
      SLLNode<String> node1 = new SLLNode<String>("apple");
      SLLNode<String> node2 = new SLLNode<String>("banana");
      SLLNode<String> node3 = new SLLNode<String>("carrot");
      SLLNode<String> node4 = new SLLNode<String>("doughnut");
      SLLNode<String> node5 = new SLLNode<String>("eggplant");
      node1.setNext(node5);
      node2.setNext(node4);
      node3.setNext(node2);
      node4.setNext(null);
      node5.setNext(node3);
      
      
   
   	// Print out the linked nodes with a loop
      System.out.println("Linked nodes 1-5: ");
      for (SLLNode<String> i = node1; i != null; i = i.getNext()) {
         System.out.println(i.toString());
      }


    }

}   // end of class