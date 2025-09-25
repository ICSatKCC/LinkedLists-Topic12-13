package linkedlists;

/**
 * Exception thrown when an operation on a list is invalid.
 */
public class ListException extends RuntimeException {
    public ListException(String message) {
        super(message);
    }
}
