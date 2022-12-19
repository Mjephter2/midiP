package sample.models.exceptions;

/**
 * class to be thrown when a Note is not valid.
 */
public class InvalidNoteException extends Exception {
    /**
     * Constructs an Exception with the passed message.
     * @param message Exception message
     */
    public InvalidNoteException(final String message) {
        super(message);
    }
}
