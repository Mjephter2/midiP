package sample.models.chords.generators.triads;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

/**
 * Utility Class for generating Minor Triad Chords.
 */
public final class MinorTriad {
    /**
     * largest interval in a Minor Triad Chord.
     */
    private static final int MAX_INTERVAL = 7;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 3;

    private MinorTriad() { };

    /**
     * Generate the Notes of the Minor Triad Chord rooted
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
