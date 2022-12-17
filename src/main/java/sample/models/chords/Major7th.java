package sample.models.chords;

import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

/**
 * Class implementing a Major 7th Chord.
 */
public final class Major7th implements Chord {
    /**
     * max number of notes in any Major 7th Chord.
     */
    private static final int NUM_NOTES = 4;

    /**
     * largest interval in the Chord.
     */
    private static final int MAX_INTERVAL = 11;

    /**
     * interval between the root and the third.
     */
    private static final int ROOT_TO_THIRD = 4;

    /**
     * interval between the root Note and the fifth.
     */
    private static final int ROOT_TO_FIFTH = 7;

    /**
     * Array of Notes representing the Chord
     * starting from the root.
     */
    private Note[] chordNotes;

    @Override
    public Major7th transposeUp(final int n) throws InvalidNoteException {
        return this.sharp(n);
    }

    @Override
    public Major7th transposeDown(final int n) throws InvalidNoteException {
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
     * Constructs a Major 7th Chord rooted at the passed Note.
     * @param scaleRoot root of the Chord
     * @throws InvalidNoteException
     */
    public Major7th(final Note scaleRoot) throws InvalidNoteException {
        this(scaleRoot.getName());
    }

    /**
     * Constructs a Major 7th Chord rooted at C3.
     * @throws InvalidNoteException
     */
    public Major7th() throws InvalidNoteException {
        this("C3");
    }

    /**
     * Constructs a Major 7th Chord rooted at the same Note.
     * as the passed Chord
     * @param chord Note to copy root from
     * @throws InvalidNoteException
     */
    public Major7th(final Major7th chord) throws InvalidNoteException {
        this(chord.chordNotes[0].getName());
    }

    /**
     * Constructs a Major 7th Chord rooted at the Note described.
     * by the passed String
     * @param root name of the root Note
     * @throws InvalidNoteException
     */
    public Major7th(final String root) throws InvalidNoteException {
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    /**
     * @return the root Note of the Chord
     */
    public Note getRoot() {
        return chordNotes[0];
    }
    private Major7th sharp(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.chordNotes[0].getName())
                + n + MAX_INTERVAL > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.chordNotes[0].sharp(n).getName();
        return new Major7th(newRoot);
    }
    private Major7th flat(final int n) throws InvalidNoteException {
        if (Utilities.NOTE_NAMES.indexOf(this.chordNotes[0].getName())
                - n > Utilities.NUMBER_OF_KEYS_88 - 1) {
            return this;
        }
        String newRoot = this.chordNotes[0].sharp(n).getName();
        return new Major7th(newRoot);
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
        rep.append(getRoot().noteQuality() + " Major 7 :");
        for (Note note: chordNotes) {
            rep.append(" " + note.noteQuality());
        }
        return rep.toString();
    }
}
