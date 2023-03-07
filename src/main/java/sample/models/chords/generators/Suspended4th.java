package sample.models.chords.generators;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

/**
 * Utility Class for generating Suspended 4th Chords.
 */
public class Suspended4th {
    /**
     * largest interval in a Minor Triad Chord.
     */
    private static final int MAX_INTERVAL = 7;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 5;

    private Suspended4th() { }

    /**
     * Generate the Notes of the Suspended 4th Chord rooted
     * at the given Note.
     * @param root root Note of the Chord
     * @return an array of the Notes
     * @throws InvalidNoteException when an invalid Note is encountered
     */
    public static Note[] generateScale(final Note root)
            throws InvalidNoteException {
        return new Note[]{
                root,
                root.sharp(ROOT_TO_THIRD),
                root.sharp(MAX_INTERVAL)
        };
    }

}
