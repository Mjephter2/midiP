package sample.DataClasses.exceptions;

/**
 * class to be thrown when a Note is not valid.
 */
public class InvalidNoteException extends Exception {
    
    public InvalidNoteException(String message) {
        super(message);
    }
}
