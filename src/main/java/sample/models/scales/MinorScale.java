package sample.models.scales;

import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

/**
 * Class implementing a Minor Scale.
 */
public final class MinorScale implements Scale {
    /**
     * number of notes in a Minor Scale.
     */
    private static final int NUM_NOTES = 8;

    /**
     * Array of Notes making the Scale.
     */
    private Note[] scaleNotes;

    /**
     * Transposes a Minor Scale up n times.
     * @param n number of times to transpose
     * @return the transposed Scale
     * @throws InvalidNoteException
     */
    @Override
    public MinorScale transposeUp(final int n) throws InvalidNoteException {
        return this.sharp(n);
    }

    /**
     * Transposes a Minor Scale down n times.
     * @param n number of times to transpose
     * @return the transposed Scale
     * @throws InvalidNoteException
     */
    @Override
    public MinorScale transposeDown(final int n) throws InvalidNoteException {
        return this.flat(n);
    }

    /**
     * @return the Notes in the Scale
     */
    @Override
    public Note[] notes() {
        return scaleNotes;
    }

    /**
     * @return a String representation of the root Note.
     */
    @Override
    public String root() {
        return this.scaleNotes[0].getName();
    }

    /**
     * Constructs a Minor Scale rooted at the given Note.
     * @param scaleRoot root of the Scale
     * @throws InvalidNoteException
     */
    public MinorScale(final Note scaleRoot) throws InvalidNoteException {
        this(scaleRoot.getName());
    }

    /**
     * Default Constructor.
     * Constructs a Minor Scale rooted at C3
     * @throws InvalidNoteException
     */
    public MinorScale() throws InvalidNoteException {
        this("C3");
    }

    /**
     * Constructs a Major Scale rooted at the same root Note
     * as the passed Scale.
     * @param scale Scale to copy root Note from
     * @throws InvalidNoteException
     */
    public MinorScale(final MinorScale scale) throws InvalidNoteException {
        this(scale.scaleNotes[0].getName());
    }

    /**
     * Constructs a Major Scale rooted at the Note represented by
     *  the passed String.
     * @param root String representation of root Note
     * @throws InvalidNoteException
     */
    public MinorScale(final String root) throws InvalidNoteException {
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    /**
     * @return the root Note of the Scale
     */
    public Note getRoot() {
        return scaleNotes[0];
    }

    private MinorScale sharp(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.scaleNotes[0].getName())
                + n
                + Utilities.NUM_NOTE_QUALITIES
                > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.scaleNotes[0].sharp(n).getName();
        return new MinorScale(newRoot);
    }
    private MinorScale flat(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.scaleNotes[0].getName())
                - n < 0) {
            return this;
        }
        String newRoot = this.scaleNotes[0].flat(n).getName();
        return new MinorScale(newRoot);
    }
    private void generateScale(final Note root) throws InvalidNoteException {
        this.scaleNotes = new Note[NUM_NOTES];
        int position = 0;
        for (Integer interval: Utilities.MINOR_SCALE_DEGREE_INTERVALS) {
            this.scaleNotes[position++] = root.sharp(interval);
        }
    }

    @Override
    public String toString() {
        StringBuilder rep = new StringBuilder(
                getRoot().noteQuality() + "\tMinor Scale:"
        );
        for (Note note: scaleNotes) {
            rep.append("\t").append(note.noteQuality());
        }
        return rep.toString();
    }
}
