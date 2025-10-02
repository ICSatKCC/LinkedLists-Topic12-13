package linkedlists;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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
public class SingleLinkedList<T> implements List<T>, Iterable<T> {
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

    @Override
    /** Inserts an element at a specific position in the list.
     * 
     * @param position the position to insert the element at
     * @param element the element to insert
     * @throws ListException if the position is invalid
     */
    public void insert(int position, T element) throws ListException {
        if (position < 0 || position > size) {
            throw new ListException("Invalid position");
        }
        SLLNode<T> newNode = new SLLNode<>(element);
        if (position == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            SLLNode<T> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * 
     * @return an iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator();
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     * 
     * @return a list iterator over the elements in this list
     */
    public ListIterator<T> listIterator() {
        return new SingleLinkedListIterator();
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence),
     * starting at the specified position in the list.
     * 
     * @param index index of the first element to be returned from the list iterator
     * @return a list iterator over the elements in this list, starting at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return new SingleLinkedListIterator(index);
    }

    /**
     * Inner class that implements both Iterator and ListIterator for SingleLinkedList.
     * Note: Due to the singly-linked nature of this list, some ListIterator operations
     * like previous() and hasPrevious() are not efficiently supported and will throw
     * UnsupportedOperationException.
     */
    private class SingleLinkedListIterator implements ListIterator<T> {
        private SLLNode<T> current;
        private SLLNode<T> previous;
        private int currentIndex;
        private boolean canRemove;
        private boolean canSet;

        /**
         * Constructs an iterator starting at the beginning of the list.
         */
        public SingleLinkedListIterator() {
            this(0);
        }

        /**
         * Constructs an iterator starting at the specified index.
         * 
         * @param index the starting index for the iterator
         */
        public SingleLinkedListIterator(int index) {
            current = head;
            previous = null;
            currentIndex = 0;
            canRemove = false;
            canSet = false;

            // Advance to the specified index
            for (int i = 0; i < index && current != null; i++) {
                previous = current;
                current = current.next;
                currentIndex++;
            }
        }

        /**
         * Returns true if this list iterator has more elements when traversing
         * the list in the forward direction.
         * 
         * @return true if the list iterator has more elements when traversing forward
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         * 
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element");
            }
            T data = current.data;
            previous = current;
            current = current.next;
            currentIndex++;
            canRemove = true;
            canSet = true;
            return data;
        }

        /**
         * Returns true if this list iterator has more elements when traversing
         * the list in the reverse direction.
         * Note: This operation is not efficiently supported for singly-linked lists.
         * 
         * @return false (not supported for singly-linked lists)
         */
        @Override
        public boolean hasPrevious() {
            // For a singly-linked list, going backwards is not efficient
            // We could implement this by traversing from head each time, but it would be O(n)
            return false;
        }

        /**
         * Returns the previous element in the list and moves the cursor position backwards.
         * Note: This operation is not efficiently supported for singly-linked lists.
         * 
         * @throws UnsupportedOperationException always, as this operation is not supported
         */
        @Override
        public T previous() {
            throw new UnsupportedOperationException("previous() not supported for singly-linked list");
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to next().
         * 
         * @return the index of the element that would be returned by next()
         */
        @Override
        public int nextIndex() {
            return currentIndex;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to previous().
         * Note: This operation is not efficiently supported for singly-linked lists.
         * 
         * @return the index that would be returned by previous()
         */
        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        /**
         * Removes from the list the last element that was returned by next() or previous().
         * This call can only be made once per call to next() or previous().
         * 
         * @throws IllegalStateException if neither next() nor previous() have been called,
         *         or remove() or add() have been called after the last call to next() or previous()
         */
        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("remove() can only be called after next()");
            }
            
            // Remove the element that was just returned by next()
            if (previous == null) {
                // We're removing the head
                head = current;
            } else {
                // Find the node before previous and link it to current
                SLLNode<T> beforePrevious = head;
                while (beforePrevious != null && beforePrevious.next != previous) {
                    beforePrevious = beforePrevious.next;
                }
                if (beforePrevious != null) {
                    beforePrevious.next = current;
                } else {
                    // Previous was the head
                    head = current;
                }
            }
            
            size--;
            currentIndex--;
            canRemove = false;
            canSet = false;
        }

        /**
         * Replaces the last element returned by next() or previous() with the specified element.
         * 
         * @param e the element with which to replace the last element returned
         * @throws IllegalStateException if neither next() nor previous() have been called,
         *         or remove() or add() have been called after the last call to next() or previous()
         */
        @Override
        public void set(T e) {
            if (!canSet) {
                throw new IllegalStateException("set() can only be called after next()");
            }
            previous.data = e;
        }

        /**
         * Inserts the specified element into the list at the current position.
         * The element is inserted immediately before the element that would be
         * returned by next(), if any.
         * 
         * @param e the element to insert
         */
        @Override
        public void add(T e) {
            SLLNode<T> newNode = new SLLNode<>(e);
            
            if (previous == null) {
                // Adding at the beginning
                newNode.next = head;
                head = newNode;
            } else {
                // Adding in the middle or end
                newNode.next = current;
                previous.next = newNode;
            }
            
            previous = newNode;
            size++;
            currentIndex++;
            canRemove = false;
            canSet = false;
        }
    }
}