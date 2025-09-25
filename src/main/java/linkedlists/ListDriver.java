package linkedlists;

public class ListDriver {
    /** A simple driver to test the SingleLinkedList implementation.
     * All methods should be tested more thoroughly in unit tests.
     * including countUniques method.
     * @param args command line arguments (not used)
     */ 
    
    public static void main(String[] args) {
      
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("A");
        list.add("B");
        list.addLast("C");
        list.addLast("A"); // Adding duplicate to test contains and remove
        System.out.println("List contents: " + list.toString()); // A -> B -> C -> A
        System.out.println("List contains A: " + list.contains("A")); // true
        System.out.println("List contains D: " + list.contains("D")); // false
        System.out.println("Size of list: " + list.size()); // 4
        System.out.println("Unique elements count: " + list.countUniques()); // 3
         // Testing get, remove, removeFirst, and remove at position with exception handling
        try {
            System.out.println("Element at position 1: " + list.get(1)); // B
            System.out.println("Removing A: " + list.remove("A")); // true
            System.out.println("Size after removal: " + list.size()); // 3
            System.out.println("List contents: " + list.toString()); // B -> C -> A         
            System.out.println("Removing first element: " + list.removeFirst()); // B
            System.out.println("Size after removing first: " + list.size()); // 1
            System.out.println("List contents: " + list.toString()); // C
            System.out.println("Removing element at position 0: " + list.remove(0)); // C
            System.out.println("Size after removing at position 0: " + list.size()); // 0
            System.out.println("Removing first element: " + list.removeFirst());    
            System.out.println("Size after removing first: " + list.size()); // 0
            System.out.println("List contents: " + list.toString()); // empty list
            list.removeFirst(); 
        } catch (ListException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }           
    }
}
