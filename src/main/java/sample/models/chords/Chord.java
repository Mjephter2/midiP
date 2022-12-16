package sample.models.chords;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

/**
 * interface representation of a chord.
 * A chord is essentially a combination of notes
 */
public interface Chord {
    /**
     * @param n number half-notes
     * @return a chord transposed up by n half-notes
     * @throws InvalidNoteException
     */
    Chord transposeUp(int n) throws InvalidNoteException;

    /**
     * @param n number of half-notes
     * @return a chord transposed down by n half-notes
     * @throws InvalidNoteException
     */
    Chord transposeDown(int n) throws InvalidNoteException;

    /**
     * @return an array of the notes making up a chord
     * starting from the root note
     */
    Note[] notes();

    /**
     * @return a string representation of the root note
     * TODO make into a default method
     */
    String root();

    /**
     * @return a string representation of the chord
     */
    String toString();
}
