package linkedlists;

/**
 * A generic singly linked list implementation that stores elements of type T.
 * This class implements the List interface and provides standard list operations
 * including add, remove, contains, and size operations. Elements are stored in
 * SLLNode objects and linked together to form the list structure.
 * 
 * @param <T> the type of elements stored in this list
 * @author Lisa Miller
 * @date 9/24/25    
 * @version 1.0
 */
public class SingleLinkedList<T> implements List<T> {
    /** Reference to the first node in the linked list */
    private SLLNode<T> head;  // points to first node in list
    /** The current number of elements in the list */
    private int size;         // number of elements in list

    /**
     * Constructs an empty singly linked list.
     * Initializes the head reference to null and size to 0.
     */
    public SingleLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Adds an element to the front of the list.
     * The new element becomes the first element in the list.
     * Time complexity: O(1)
     * 
     * @param element the element to be added to the front of the list
     */
    @Override
    public void add(T element) {
        SLLNode<T> newNode = new SLLNode<T>(element);
        newNode.next = head;  // new node points to current head
        head = newNode;       // head now points to new node
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * @param element the element to be added to the end of the list
     * Time complexity: O(n) where n is the number of elements in the list
     */
    public void addLast(T element) {
        SLLNode<T> newNode = new SLLNode<T>(element);
        if (head == null) {
            head = newNode; // List was empty, new node is now head
        } else {
            SLLNode<T> current = head;
            while (current.next != null) {
                current = current.next; // Traverse to the end of the list
            }
            current.next = newNode; // Link the last node to the new node
        }
        size++;
    }
    /**
     * Removes the first occurrence of the specified element from the list.
     * If the element is not found, the list remains unchanged.
     * Time complexity: O(n) where n is the number of elements in the list
     * 
     * @param element the element to be removed from the list
     * @return true if the element was found and removed, false otherwise
     */
    @Override
    public boolean remove(T element) {
        if (head == null) {
            return false; // List is empty
        }
        if (head.data.equals(element)) {
            head = head.next; // Remove head
            size--;
            return true;
        }
        SLLNode<T> current = head;
        while (current.next != null && !current.next.data.equals(element)) {
            current = current.next;
        }
        if (current.next == null) {
            return false; // Element not found
        }
        current.next = current.next.next; // Bypass the node to be removed
        size--;
        return true;
    }

    /**
     * Removes and returns the first element from the list.
     * Time complexity: O(1)
     * 
     * @return the first element that was removed from the list
     * @throws ListException if the list is empty
     */
    @Override
    public T removeFirst() throws ListException {
        if (head == null) {
            throw new ListException("List is empty");
        }
        T removedData = head.data;
        head = head.next;
        size--;
        return removedData;
    }   

    /**
     * Removes and returns the element at the specified position in the list.
     * Positions are zero-indexed, where 0 is the first element.
     * Time complexity: O(n) where n is the position of the element
     * 
     * @param position the zero-based index of the element to remove
     * @return the element that was removed from the list
     * @throws ListException if the position is invalid (less than 0 or greater than or equal to size)
     */
    @Override
    public T remove(int position) throws ListException {
        if (position < 0 || position >= size) {
            throw new ListException("Invalid position");
        }
        if (position == 0) {
            return removeFirst();
        }
        SLLNode<T> current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
        }
        T removedData = current.next.data;
        current.next = current.next.next;
        size--;
        return removedData;
    }

    /**
     * Checks if the list contains the specified element.
     * Uses the equals method to compare elements for equality.
     * Time complexity: O(n) where n is the number of elements in the list
     * 
     * @param element the element to search for in the list
     * @return true if the element is found in the list, false otherwise
     */
    @Override
    public boolean contains(T element) {
        SLLNode<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns the number of unique elements in the list.
     * This method counts distinct values, treating multiple occurrences of the same
     * value as a single unique element. The implementation uses only the linked list
     * structure without additional data structures.
     * Time complexity: O(n²) where n is the number of elements in the list
     * Space complexity: O(1) - uses constant extra space
     * 
     * @return the count of unique elements in the list
     */
    @Override
    public int countUniques() {
        if (head == null) {
            return 0; // Empty list has no unique elements
        }
        
        int uniqueCount = 0;
        SLLNode<T> current = head;
        
        // For each node, check if its data appears earlier in the list
        while (current != null) {
            boolean isUnique = true;
            SLLNode<T> checker = head;
            
            // Check all nodes before the current node
            while (checker != current) {
                if (checker.data.equals(current.data)) {
                    isUnique = false;
                    break; // Found a duplicate earlier in the list
                }
                checker = checker.next;
            }
            
            // If no duplicate was found earlier, this is the first occurrence
            if (isUnique) {
                uniqueCount++;
            }
            
            current = current.next;
        }
        
        return uniqueCount;
    }

    /**
     * Checks if the list is empty.
     * Time complexity: O(1)
     * 
     * @return true if the list contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     * Time complexity: O(1)
     * 
     * @return the number of elements currently in the list
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    /** Retrieves an element from a specific position in the list.
     * 
     * @param position the position of the element to retrieve
     * @return the element at the specified position
     * @throws ListException if the position is invalid
     */
    public T get(int position) throws ListException {
        if (position < 0 || position >= size) {
            throw new ListException("Invalid position");
        }
        SLLNode<T> current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }
    /** toString returns a string representation of the elements of the list */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        SLLNode<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }
}