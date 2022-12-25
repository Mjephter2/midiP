package sample.models;

import sample.models.exceptions.InvalidNoteException;

import java.util.Arrays;

/**
 * Class representing any musical construct
 * that can be transposed.
 */
public abstract class Transposable {
    /**
     * @return the Array of Notes making the Transposable.
     */
    public abstract Note[] notes();

    /**
     * Transposes all Notes of the Transposable object up
     * by the specified amount.
     * @param n number of times to transpose
     * @return the transposed Notes
     * @throws InvalidNoteException when an invalid Note is encountered
     */
    public Note[] transposeUp(final int n) throws InvalidNoteException {
        Note[] newNotes = Arrays.copyOf(notes(), notes().length);
        for (Note note: newNotes) {
            note = note.sharp(n);
        }
        return newNotes;
    }

    /**
     * Transposes all Notes of the Transposable object down
     * by the specified amount.
     * @param n number of times to transpose
     * @return the transposed Notes
     * @throws InvalidNoteException when an invalid Note is encountered
     */
    public Note[] transposeDown(final int n) throws InvalidNoteException {
        Note[] newNotes = Arrays.copyOf(notes(), notes().length);
        for (Note note: newNotes) {
            note = note.flat(n);
        }
        return newNotes;
    }

    /**
     * Generates the Notes of the Transposable.
     * @param root root Note
     * @return the generated Notes in an array
     * @throws InvalidNoteException when an invalid Note is encountered
     */
    public abstract Note[] generateNotes(Note root) throws InvalidNoteException;
}
