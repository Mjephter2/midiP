package sample.DataClasses.chords;

import sample.DataClasses.Note;
import sample.DataClasses.Utilities;
import sample.DataClasses.exceptions.InvalidNoteException;

public class Minor9th implements Chord {

    private Note[] chord_Notes = new Note[5];

    @Override
    public Minor9th transposeUp(int n) throws InvalidNoteException {
        return this.sharp(n);
    }

    @Override
    public Minor9th transposeDown(int n) throws InvalidNoteException {
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

    public Minor9th(Note scaleRoot) throws InvalidNoteException{
        this(scaleRoot.getName());
    }

    public Minor9th() throws InvalidNoteException{
        this("C3");
    }

    public Minor9th(Minor9th chord) throws InvalidNoteException{
        this(chord.chord_Notes[0].getName());
    }

    public Minor9th(String root) throws InvalidNoteException{
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return chord_Notes[0];
    }

    private Minor9th sharp(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) + n + 14 > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Minor9th(newRoot);
    }

    private Minor9th flat(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) - n > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Minor9th(newRoot);
    }
    
    private void generateScale(Note root) throws InvalidNoteException{
        this.chord_Notes[0] = root;
        this.chord_Notes[1]= root.sharp(3);
        this.chord_Notes[2]= root.sharp(7);
        this.chord_Notes[3] = root.sharp(10);
        this.chord_Notes[4] = root.sharp(14);
    }

    @Override
    public String toString() {
        return  getRoot().noteQuality() + " Minor 9 :"
                + " " + chord_Notes[0].noteQuality()
                + " " + chord_Notes[1].noteQuality()
                + " " + chord_Notes[2].noteQuality()
                + " " + chord_Notes[3].noteQuality()
                + " " + chord_Notes[4].noteQuality();
    }
}
