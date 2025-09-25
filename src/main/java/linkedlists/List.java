package linkedlists;

/**
 * A generic interface for a list.
 * 
 * @param <T> the type of elements in the list
 */
public interface List<T> {
    /**
     * Adds an element to the list.
     * 
     * @param element the element to add
     */
    void add(T element);

    /** Gets an element from a position in the list.
     * 
     * @param position the position of the element to get
     * @return the element at the specified position
     * @throws ListException if the position is invalid
     */
    T get(int position) throws ListException;

    /**
     * Removes an element from the list.
     * 
     * @param element the element to remove
     * @return true if the element was removed, false otherwise
     */
    boolean remove(T element);

    /** removeFirst.
     * Removes the first element from the list.
     * 
     * @return the removed element, or throws ListException if the list is empty
     * @throws ListException if the list is empty
     */
    T removeFirst() throws ListException;   

    /** remove from a position.
     * Removes an element from a specific position in the list.
     * 
     * @param position the position of the element to remove
     * @return the removed element, or throws ListException if the position is invalid
     * @throws ListException if the position is invalid
     */
    T remove(int position) throws ListException;

    /**
     * Checks if the list contains a specific element.
     * 
     * @param element the element to check for
     * @return true if the element is in the list, false otherwise
     */
    boolean contains(T element);

    /**
     * Retrieves the current size of the list.
     * 
     * @return the number of elements in the list
     */
    int size();

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    boolean isEmpty();

    /** countUniques 
     * Counts the number of unique elements in the list.
     * 
     * @return the number of unique elements
     */
    int countUniques();

    /** toString 
     * Returns a string representation of the list.
     * 
     * @return a string representing the list
     */
    @Override
    String toString();  

}
