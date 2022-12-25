package sample.models.chords;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

/**
 * Utility Class for generating Minor Triad Chords.
 */
public final class MajorTriad {
    /**
     * largest interval in a Major Triad Chord.
     */
    private static final int MAX_INTERVAL = 7;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 4;

    private MajorTriad() { };

    /**
     * Generate the Notes of the Major Triad Chord rooted
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
