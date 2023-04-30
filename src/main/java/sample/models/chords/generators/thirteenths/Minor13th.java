package sample.models.chords.generators.thirteenths;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

public class Minor13th {

/**
     * largest interval in a Minor 13th Chord.
     */
    private static final int MAX_INTERVAL = 21;

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

    /**
     * interval between the root Note and the eleventh.
     */
    private static final int ROOT_TO_ELEVEN = 17;

    private Minor13th() { };

    /**
     * Generate the Notes of the Minor 13th Chord rooted
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
                root.sharp(ROOT_TO_ELEVEN),
                root.sharp(MAX_INTERVAL)
        };
    }
}
