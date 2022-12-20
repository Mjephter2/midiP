package sample.models;

import sample.AudioPlayer;
import sample.models.exceptions.InvalidNoteException;

/**
 * Class implementation of a piano key / notes.
 */
public final class Note {
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
        if (!isValidNote(noteName)) {
            throw new InvalidNoteException("Invalid Note created: " + noteName);
        }
        name = noteName;
    }

    /**
     * Checks if the Note is valid.
     * @param noteName the full name of the Note
     * @return true if the Note is valid, false otherwise
     */
    private boolean isValidNote(final String noteName) {
        return Utilities.NOTE_NAMES.contains(noteName);
    }

    /**
     * @return the full name of the Note.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return a string representation of the Note
     */
    public String toString() {
        return name;
    }

    /**
     * sharpens a Note @param n times.
     * @param n number of times to sharpen the Note
     * @return a new Note n half notes above the current one
     */
    public Note sharp(final int n) throws InvalidNoteException {
        int index = Utilities.NOTE_NAMES.indexOf(this.name);
        if (index + n > Utilities.NUMBER_OF_KEYS_88 - 1) {
            throw new InvalidNoteException("Invalid Note created: " + name);
        }
        return new Note(Utilities.NOTE_NAMES.get(index + n));
    }

    /**
     * flattens a Note @param n times.
     * @param n number of times to flatten the Note
     * @return a new Note @param n notes below the current one
     */
    public Note flat(final int n) throws InvalidNoteException {
        int index = Utilities.NOTE_NAMES.indexOf(name);
        if (index - n < 0) {
            throw new InvalidNoteException("Invalid Note created: " + name);
        }
        return new Note(Utilities.NOTE_NAMES.get(index - n));
    }

    /**
     * TODO.
     */
    public void play() {
        System.out.println("Playing " + this.name);
        String path = getSoundFilePath(this.name);
        new Thread(() -> new AudioPlayer().play(path)).start();
    }

    /**
      * retrieve the filePath of the Note's corresponding sound.
     * @param noteName the full name of the note
     * @return the sound file name corresponding to the note name
      */
    private String getSoundFilePath(final String noteName) {
        // TODO
        return "PATH TO SOUND FILE OF: " + noteName;
    }

    /**
     * @return the quality of a note
     */
    public String noteQuality() {
        return this.getName().substring(0, this.getName().length() - 1);
    }
}
