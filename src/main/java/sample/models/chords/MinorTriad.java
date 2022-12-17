package sample.models.chords;

import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

/**
 * Class implementing a Minor Triad Chord.
 */
public final class MinorTriad implements Chord {
    /**
     * max number of notes in any Major Triad Chord.
     */
    private static final int NUM_NOTES = 3;

    /**
     * largest interval in the Chord.
     */
    private static final int MAX_INTERVAL = 7;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 3;

    /**
     * Array of Notes representing the Chord
     * starting from the root.
     */
    private Note[] chordNotes;

    @Override
    public MinorTriad transposeUp(final int n) throws InvalidNoteException {
        return this.sharp(n);
    }

    @Override
    public MinorTriad transposeDown(final int n) throws InvalidNoteException {
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
     * Constructs a Minor Triad Chord rooted at the passed Note.
     * @param scaleRoot root of the Chord
     * @throws InvalidNoteException
     */
    public MinorTriad(final Note scaleRoot) throws InvalidNoteException {
        this(scaleRoot.getName());
    }

    /**
     * Default Constructor
     * Constructs a Minor Triad Chord rooted at C3.
     * @throws InvalidNoteException
     */
    public MinorTriad() throws InvalidNoteException {
        this("C3");
    }

    /**
     * Constructs a Minor Triad Chord rooted at the same Note.
     * as the passed Chord
     * @param triad to copy root from
     * @throws InvalidNoteException
     */
    public MinorTriad(final MinorTriad triad) throws InvalidNoteException {
        this(triad.chordNotes[0].getName());
    }

    /**
     * Constructs a Minor Triad Chord rooted at the Note described.
     * by the passed String
     * @param root name of the root Note
     * @throws InvalidNoteException
     */
    public MinorTriad(final String root) throws InvalidNoteException {
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    /**
     * @return the root Note of the Chord
     */
    public Note getRoot() {
        return chordNotes[0];
    }

    private MinorTriad sharp(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.chordNotes[0].getName())
                + n + MAX_INTERVAL > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.chordNotes[0].sharp(n).getName();
        return new MinorTriad(newRoot);
    }

    private MinorTriad flat(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.chordNotes[0].getName())
                - n > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.chordNotes[0].sharp(n).getName();
        return new MinorTriad(newRoot);
    }
    private void generateScale(final Note root) throws InvalidNoteException {
        this.chordNotes = new Note[]{
                root,
                root.sharp(ROOT_TO_THIRD),
                root.sharp(MAX_INTERVAL)
        };
    }

    @Override
    public String toString() {
        StringBuilder rep = new StringBuilder();
        rep.append(getRoot().noteQuality() + " Minor Triad :");
        for (Note note: chordNotes) {
            rep.append(" " + note.noteQuality());
        }
        return rep.toString();
    }
}
