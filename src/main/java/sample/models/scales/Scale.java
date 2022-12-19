package sample.models.scales;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

/**
 * Base Interface representing a musical Scale.
 */
public interface Scale {
    /**
     * Transposes a Scale up.
     *
     * @param n number of times to transpose
     * @return the transposed Scale
     * @throws InvalidNoteException
     */
    Scale transposeUp(int n) throws InvalidNoteException;

    /**
     * Transposes a Scale down.
     * @param n number of times to transpose
     * @return the transposed Scale
     * @throws InvalidNoteException
     */
    Scale transposeDown(int n) throws InvalidNoteException;

    /**
     * @return the array of Notes making up a Scale.
     * starting from the root
     */
    Note[] notes();

    /**
     * @return a String representation of the root Note.
     */
    String root();

    /**
     * @return a String representation of the Scale.
     */
    String toString();
}
