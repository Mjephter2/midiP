package sample.models;

import sample.AudioPlayer;
import sample.models.exceptions.InvalidNoteException;

/**
 * A class implementation of a piano key / notes
 */
public class Note {
    /**
     * variable to store the full name of a Note, for example 'Db1'
     */
    private String name;

    /**
     * default constructor
     * Assigns Note name to "C1"
     */
    public Note(){
        this.name = "C1";
    }

    /**
     * constructor
     * @param name: the full name of the Note
     */
    public Note(String name) throws InvalidNoteException {
        if(!isValidNote(name)){
            throw new InvalidNoteException("Invalid Note created: " + this.name);
        }
        this.name = name;
    }

    /**
     * Checks if the Note is valid
     * @param name: the full name of the Note
     * @return true if the Note is valid, false otherwise
     */
    private boolean isValidNote(String name){
        if(!Utilities.NOTE_NAMES.contains(name)) return false;
        return true;
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
    public String toString(){
        return name;
    }

    /**
     * sharpens a Note @param n times
     * @param n: number of times to sharpen the Note
     * @return a new Note @param n notes above the current one
     */
    public Note sharp(int n) throws InvalidNoteException {
        int index = Utilities.NOTE_NAMES.indexOf(this.name);
        if(index + n > 87) {
            throw new InvalidNoteException("Invalid Note created: " + this.name);
        };
        return new Note(Utilities.NOTE_NAMES.get(index + n));
    }

    /**
     * flatten a Note @param n times
     * @param n: number of times to flatten the Note
     * @return a new Note @param n notes below the current one
     */
    public Note flat(int n) throws InvalidNoteException {
        int index = Utilities.NOTE_NAMES.indexOf(this.name);
        if(index - n < 0) {
            throw new InvalidNoteException("Invalid Note created: " + this.name);
        };
        return new Note(Utilities.NOTE_NAMES.get(index - n));
    }

    public void play(){
        System.out.println("Playing " + this.name);
        String path = getSoundFilePath(this.name);
        new Thread(new Runnable() {
            @Override
            public void run() {
                new AudioPlayer().play(path);
            }
        }).start();
    }

    // retrieve the filePath of the Note's corresponding sound
    private String getSoundFilePath(String noteName){
        // TODO
        return "PATH TO SOUND FILE OF: " + noteName;
    }

    public String noteQuality(){
        return this.getName().substring(0,this.getName().length() - 1);
    }
}
