package sample.models.chords.generators.elevenths;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

public class Minor11th {
    /**
     * largest interval in a Major 9th Chord.
     */
    private static final int MAX_INTERVAL = 17;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 3;

    /**
     * interval between the root Note and the fifth.
     */
    private static final int ROOT_TO_FIFTH = 7;

    /**
     * interval between the root Note and the seventh.
     */
    private static final int ROOT_TO_SEVEN = 10;

    /**
     * interval between the root Note and the ninth.
     */
    private static final int ROOT_TO_NINE = 14;

    private Minor11th() { }

    /**
     * Generate the Notes of the Major 11th Chord rooted
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
                root.sharp(ROOT_TO_SEVEN),
                root.sharp(ROOT_TO_NINE),
                root.sharp(MAX_INTERVAL)
        };
    }
}
