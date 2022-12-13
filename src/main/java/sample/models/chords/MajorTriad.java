package sample.models.chords;

import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

public class MajorTriad implements Chord {
    private Note[] chord_Notes = new Note[3];

    @Override
    public MajorTriad transposeUp(int n) throws InvalidNoteException {
        return this.sharp(n);
    }

    @Override
    public MajorTriad transposeDown(int n) throws InvalidNoteException {
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

    public MajorTriad(Note scaleRoot) throws InvalidNoteException{
        this(scaleRoot.getName());
    }

    public MajorTriad() throws InvalidNoteException{
        this("C3");
    }

    public MajorTriad(MajorTriad triad) throws InvalidNoteException{
        this(triad.chord_Notes[0].getName());
    }

    public MajorTriad(String root) throws InvalidNoteException{
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return chord_Notes[0];
    }

    private MajorTriad sharp(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) + n + 11 > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new MajorTriad(newRoot);
    }

    private MajorTriad flat(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) - n > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new MajorTriad(newRoot);
    }
    
    private void generateScale(Note root) throws InvalidNoteException{
        this.chord_Notes[0] = root;
        this.chord_Notes[1]= root.sharp(4);
        this.chord_Notes[2]= root.sharp(7);
    }

    @Override
    public String toString() {
        return  getRoot().noteQuality() + " Major Triad:"
                + " " + getRoot().noteQuality()
                + " " + chord_Notes[1].noteQuality()
                + " " + chord_Notes[2].noteQuality();
    }
}
