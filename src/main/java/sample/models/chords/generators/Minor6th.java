package sample.models.chords.generators;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

/**
 * Utility Class for generating Minor 6th Chords.
 */
public class Minor6th {
    /**
     * largest interval in a Major Triad Chord.
     */
    private static final int MAX_INTERVAL = 9;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 3;

    private static final int ROOT_TO_FIFTH = 7;

    private Minor6th() { }

    /**
     * Generate the Notes of the Minor 6th Chord rooted
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
                root.sharp(ROOT_TO_FIFTH),
                root.sharp(MAX_INTERVAL)
        };
    }
}
