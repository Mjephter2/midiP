package sample.models.chords;

import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

/**
 * Class implementing a Dominant 9th Chord.
 */
public final class Dominant9th implements Chord {

    /**
     * max number of notes in any Dominant 7th Chord.
     */
    private static final int NUM_NOTES = 5;

    /**
     * largest interval in the Chord.
     */
    private static final int MAX_INTERVAL = 14;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 4;

    /**
     * interval between the root Note and the fifth.
     */
    private static final int ROOT_TO_FIFTH = 7;

    /**
     * interval between the root Note and the seventh.
     */
    private static final int ROOT_TO_SEVEN = 10;

    /**
     * Array of Notes representing the Chord
     * starting from the root.
     */
    private Note[] chordNotes;

    @Override
    public Dominant9th transposeUp(final int n) throws InvalidNoteException {
        return this.sharp(n);
    }
    @Override
    public Dominant9th transposeDown(final int n) throws InvalidNoteException {
        return this.flat(n);
    }
    @Override
    public Note[] notes() {
        return chordNotes;
    }
    @Override
    public String root() {
        return this.chordNotes[0].getName();
    }

    /**
     * Constructs a Dominant 9th Chord rooted at the passed Note.
     * @param scaleRoot root Note of the Chord
     * @throws InvalidNoteException
     */
    public Dominant9th(final Note scaleRoot) throws InvalidNoteException {
        this(scaleRoot.getName());
    }

    /**
     * Default Constructor.
     * Constructs a Dominant 9th Chord rooted at C3
     * @throws InvalidNoteException
     */
    public Dominant9th() throws InvalidNoteException {
        this("C3");
    }

    /**
     * Constructs a Dominant 9th Chord rooted at the same root Note
     * as the passed Chord.
     * @param chord Dominant 9th Chord to copy root from
     * @throws InvalidNoteException
     */
    public Dominant9th(final Dominant9th chord) throws InvalidNoteException {
        this(chord.chordNotes[0].getName());
    }

    /**
     * Constructs a Dominant 9th Chord rooted at the Noted described by
     * the passed String.
     * @param root name of the root Note
     * @throws InvalidNoteException
     */
    public Dominant9th(final String root) throws InvalidNoteException {
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    /**
     * @return the root of the Chord
     */
    public Note getRoot() {
        return chordNotes[0];
    }

    /**
     * Transposes the Chord by n half Notes.
     * @param n number of times to transpose
     * @return the transposed Chord
     * @throws InvalidNoteException
     */
    private Dominant9th sharp(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.chordNotes[0].getName())
                + n + MAX_INTERVAL > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.chordNotes[0].sharp(n).getName();
        return new Dominant9th(newRoot);
    }
    private Dominant9th flat(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.chordNotes[0].getName())
                - n > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.chordNotes[0].sharp(n).getName();
        return new Dominant9th(newRoot);
    }
    private void generateScale(final Note root) throws InvalidNoteException {
        this.chordNotes = new Note[]{
                root,
                root.sharp(ROOT_TO_THIRD),
                root.sharp(ROOT_TO_FIFTH),
                root.sharp(ROOT_TO_SEVEN),
                root.sharp(MAX_INTERVAL)
        };
    }

    @Override
    public String toString() {
        StringBuilder rep = new StringBuilder();
        rep.append(getRoot().noteQuality() + " Dominant 9 :");
        for (Note note: chordNotes) {
            rep.append(" " + note.noteQuality());
        }
        return rep.toString();
    }
}
