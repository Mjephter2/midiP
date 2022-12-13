package sample.models.chords;

import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

public class Major7th implements Chord {
    private Note[] chord_Notes = new Note[4];

    @Override
    public Major7th transposeUp(int n) throws InvalidNoteException {
        return this.sharp(n);
    }

    @Override
    public Major7th transposeDown(int n) throws InvalidNoteException {
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

    public Major7th(Note scaleRoot) throws InvalidNoteException{
        this(scaleRoot.getName());
    }

    public Major7th() throws InvalidNoteException{
        this("C3");
    }

    public Major7th(Major7th chord) throws InvalidNoteException{
        this(chord.chord_Notes[0].getName());
    }
    
    public Major7th(String root) throws InvalidNoteException{
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return chord_Notes[0];
    }


    private Major7th sharp(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) + n + 11 > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Major7th(newRoot);
    }
    private Major7th flat(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) - n > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Major7th(newRoot);
    }
    private void generateScale(Note root) throws InvalidNoteException{
        this.chord_Notes[0] = root;
        this.chord_Notes[1]= root.sharp(4);
        this.chord_Notes[2]= root.sharp(7);
        this.chord_Notes[3] = root.sharp(11);
    }

    @Override
    public String toString() {
        return  getRoot().noteQuality() + " Major 7 :"
                + " " + chord_Notes[0].noteQuality()
                + " " + chord_Notes[1].noteQuality()
                + " " + chord_Notes[2].noteQuality()
                + " " + chord_Notes[3].noteQuality();
    }
}
