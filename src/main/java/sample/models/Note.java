package sample.models;

import sample.models.exceptions.InvalidNoteException;

import static sample.models.NoteNames.FLAT_MODE_NAMES;
import static sample.models.NoteNames.SHARP_MODE_NAMES;

import static sample.models.NotesNamingMode.FLAT_MODE;
import static sample.models.NotesNamingMode.SHARP_MODE;
import static sample.models.Utilities.NOTE_NAMES_FLAT;
import static sample.models.Utilities.NOTE_NAMES_SHARP;

/**
 * Class implementation of a piano key / note.
 */
public final class Note {
    public static NotesNamingMode notesNamingMode = FLAT_MODE;
    /**
     * variable to store the full name of a Note, for example 'Db1'.
     */
    private final String name;

    /**
     * default constructor.
     * Assigns Note name to "C1"
     */
    public Note() {
        this.name = "C1";
    }

    /**
     * Constructs a note using the given string.
     * @param noteName the full name of the Note
     * @throws InvalidNoteException if the note name is not valid
     */
    public Note(final String noteName) throws InvalidNoteException {
        if(!isValidNote(noteName)) {
            throw new InvalidNoteException("Invalid Note created: " + noteName);
        }

        int noteIndex;
        if (NOTE_NAMES_FLAT.contains(noteName)) {
            noteIndex = NOTE_NAMES_FLAT.indexOf(noteName);
        } else {
            noteIndex = NOTE_NAMES_SHARP.indexOf(noteName);
        }

        if(notesNamingMode == FLAT_MODE) {
            name = NOTE_NAMES_FLAT.get(noteIndex);
        } else {
            name = NOTE_NAMES_SHARP.get(noteIndex);
        }
    }

    /**
     * Checks if the Note is valid.
     * @param noteName the full name of the Note
     * @return true if the Note is valid, false otherwise
     */
    private boolean isValidNote(final String noteName) {
        return NOTE_NAMES_FLAT.contains(noteName) || NOTE_NAMES_SHARP.contains(noteName);
    }

    /**
     * @return the full name of the Note.
     */
    public String getName() {
        return this.name;
    }

    /**
     * sharpens a Note @param n times.
     * @param n number of times to sharpen the Note
     * @return a new Note n half notes above the current one
     */
    public Note sharp(final int n) throws InvalidNoteException {
        int index = NOTE_NAMES_FLAT.indexOf(this.name);
        if (index + n > Utilities.NUMBER_OF_KEYS_88 - 1) {
            throw new InvalidNoteException("Invalid Note created: " + name);
        }
        return new Note(NOTE_NAMES_FLAT.get(index + n));
    }

    /**
     * flattens a Note @param n times.
     * @param n number of times to flatten the Note
     * @return a new Note @param n notes below the current one
     */
    public Note flat(final int n) throws InvalidNoteException {
        int index = NOTE_NAMES_FLAT.indexOf(name);
        if (index - n < 0) {
            throw new InvalidNoteException("Invalid Note created: " + name);
        }
        return new Note(NOTE_NAMES_FLAT.get(index - n));
    }

    /**
     * @return the quality of a note
     */
    public String noteQuality() {
        return this.getName().substring(0, this.getName().length() - 1);
    }
}
