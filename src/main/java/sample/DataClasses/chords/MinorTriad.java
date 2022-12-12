package sample.DataClasses.chords;

import sample.DataClasses.Note;
import sample.DataClasses.Utilities;
import sample.DataClasses.exceptions.InvalidNoteException;

public class MinorTriad implements Chord {

    private Note[] chord_Notes = new Note[3];

    @Override
    public MinorTriad transposeUp(int n) throws InvalidNoteException {
        return this.sharp(n);
    }

    @Override
    public MinorTriad transposeDown(int n) throws InvalidNoteException {
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

    public MinorTriad(Note scaleRoot) throws InvalidNoteException{
        this(scaleRoot.getName());
    }

    public MinorTriad() throws InvalidNoteException{
        this("C3");
    }

    public MinorTriad(MinorTriad triad) throws InvalidNoteException{
        this(triad.chord_Notes[0].getName());
    }

    public MinorTriad(String root) throws InvalidNoteException{
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return chord_Notes[0];
    }

    private MinorTriad sharp(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) + n + 7 > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new MinorTriad(newRoot);
    }

    private MinorTriad flat(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) - n > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new MinorTriad(newRoot);
    }
    
    private void generateScale(Note root) throws InvalidNoteException{
        this.chord_Notes[0] = root;
        this.chord_Notes[1]= root.sharp(3);
        this.chord_Notes[2]= root.sharp(7);
    }

    @Override
    public String toString() {
        return  getRoot().noteQuality() + " Minor Triad:"
                + " " + getRoot().noteQuality()
                + " " + chord_Notes[1].noteQuality()
                + " " + chord_Notes[2].noteQuality();
    }
}
