package sample.DataClasses.chords;

import sample.DataClasses.Note;
import sample.DataClasses.Utilities;
import sample.DataClasses.exceptions.InvalidNoteException;

public class Dominant9th implements Chord {
    private Note[] chord_Notes = new Note[5];

    @Override
    public Dominant9th transposeUp(int n) throws InvalidNoteException {
        return this.sharp(n);
    }
    @Override
    public Dominant9th transposeDown(int n) throws InvalidNoteException {
        return this.flat(n);
    }
    @Override
    public Note[] notes() {
        return chord_Notes;
    }
    @Override
    public String root() {
        return this.chord_Notes[0].getName();
    }

    public Dominant9th(Note scaleRoot) throws InvalidNoteException{
        this(scaleRoot.getName());
    }
    public Dominant9th() throws InvalidNoteException{
        this("C3");
    }
    public Dominant9th(Dominant9th chord) throws InvalidNoteException{
        this(chord.chord_Notes[0].getName());
    }
    public Dominant9th(String root) throws InvalidNoteException{
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return chord_Notes[0];
    }


    private Dominant9th sharp(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) + n + 13 > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Dominant9th(newRoot);
    }
    private Dominant9th flat(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) - n > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Dominant9th(newRoot);
    }
    private void generateScale(Note root) throws InvalidNoteException{
        this.chord_Notes[0] = root;
        this.chord_Notes[1]= root.sharp(4);
        this.chord_Notes[2]= root.sharp(7);
        this.chord_Notes[3] = root.sharp(10);
        this.chord_Notes[4] = root.sharp(14);
    }

    @Override
    public String toString() {
        return  getRoot().noteQuality() + " Dominant 9 :"
                + " " + chord_Notes[0].noteQuality()
                + " " + chord_Notes[1].noteQuality()
                + " " + chord_Notes[2].noteQuality()
                + " " + chord_Notes[3].noteQuality()
                + " " + chord_Notes[4].noteQuality();
    }
}
