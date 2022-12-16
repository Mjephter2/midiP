package sample.models.chords;

import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

/**
 * Class implementing a dominant 7th chord.
 */
public final class Dominant7th implements Chord {
    /**
     * max number of notes in any dominant 7th chord.
     */
    private static final int NUM_NOTES = 4;

    /**
     * largest interval in the chord.
     */
    private static final int MAX_INTERVAL = 10;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 4;

    /**
     * interval between the root note and the fifth.
     */
    private static final int ROOT_TO_FIFTH = 7;

    /**
     * Array of notes representing the chord
     * starting from the root.
     */
    private Note[] chordNotes;

    @Override
    public Dominant7th transposeUp(final int n) throws InvalidNoteException {
        return this.sharp(n);
    }
    @Override
    public Dominant7th transposeDown(final int n) throws InvalidNoteException {
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
     * Constructs a Dominant 7th Chord with the given note
     * as the root.
     * @param scaleRoot the root note of the chord
     * @throws InvalidNoteException
     */
    public Dominant7th(final Note scaleRoot) throws InvalidNoteException {
        this(scaleRoot.getName());
    }

    /**
     * Default Constructor.
     * Constructs a Dominant 7th chord rooted at C3
     * @throws InvalidNoteException
     */
    public Dominant7th() throws InvalidNoteException {
        this("C3");
    }

    /**
     * Constructs a chord rooted at the same note as the
     * passed Chord.
     * @param chord Chord to copy from
     * @throws InvalidNoteException
     */
    public Dominant7th(final Dominant7th chord) throws InvalidNoteException {
        this(chord.chordNotes[0].getName());
    }

    /**
     * Constructs a Dominant 7th Chord rooted at the note
     * represented by the string @root.
     * @param root string representing a Note
     * @throws InvalidNoteException
     */
    public Dominant7th(final String root) throws InvalidNoteException {
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    /**
     * @return the root note of the Chord
     */
    public Note getRoot() {
        return chordNotes[0];
    }
    private Dominant7th sharp(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.chordNotes[0].getName())
                + n
                + MAX_INTERVAL > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.chordNotes[0].sharp(n).getName();
        return new Dominant7th(newRoot);
    }
    private Dominant7th flat(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(
                this.chordNotes[0].getName())
                - n > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.chordNotes[0].sharp(n).getName();
        return new Dominant7th(newRoot);
    }
    private void generateScale(final Note root) throws InvalidNoteException {
        this.chordNotes = new Note[]{
                root,
                root.sharp(ROOT_TO_THIRD),
                root.sharp(ROOT_TO_FIFTH),
                root.sharp(MAX_INTERVAL)
        };
    }

    @Override
    public String toString() {
        StringBuilder rep = new StringBuilder();
        rep.append(getRoot().noteQuality() + " Dominant 7 :");
        for (Note note: chordNotes) {
            rep.append(" " + note.noteQuality());
        }
        return rep.toString();
    }
}
