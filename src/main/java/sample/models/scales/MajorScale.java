package sample.models.scales;

import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

public class MajorScale implements Scale{
    private Note[] scale_Notes = new Note[8];

    @Override
    public Scale transposeUp(int n) throws InvalidNoteException {
        return this.sharp(n);
    }

    @Override
    public Scale transposeDown(int n) throws InvalidNoteException {
        return this.flat(n);
    }

    @Override
    public Note[] notes() {
        return scale_Notes;
    }

    @Override
    public String root() {
        return this.scale_Notes[0].getName();
    }

    public MajorScale(Note scaleRoot) throws InvalidNoteException{
        this(scaleRoot.getName());
    }

    public MajorScale() throws InvalidNoteException{
        this("C3");
    }

    public MajorScale(MajorScale scale) throws InvalidNoteException{
        this(scale.scale_Notes[0].getName());
    }

    public MajorScale(String root) throws InvalidNoteException{
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return scale_Notes[0];
    }

    public Note getDegree_2() {
        return scale_Notes[1];
    }

    public Note getDegree_3() {
        return scale_Notes[2];
    }

    public Note getDegree_4() {
        return scale_Notes[3];
    }

    public Note getDegree_5() {
        return scale_Notes[4];
    }

    public Note getDegree_6() {
        return scale_Notes[5];
    }

    public Note getDegree_7() {
        return scale_Notes[6];
    }

    private MajorScale sharp(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.scale_Notes[0].getName()) + n + 12 > 87) return this;
        String newRoot = this.scale_Notes[0].sharp(n).getName();
        return new MajorScale(newRoot);
    }

    private MajorScale flat(int n) throws InvalidNoteException{
        if(Utilities.NOTE_NAMES.indexOf(this.scale_Notes[0].getName()) - n < 0) return this;
        String newRoot = this.scale_Notes[0].flat(n).getName();
        return new MajorScale(newRoot);
    }
    
    private void generateScale(Note root) throws InvalidNoteException{
        this.scale_Notes[0] = root;
        this.scale_Notes[1]= root.sharp(2);
        this.scale_Notes[2]= root.sharp(4);
        this.scale_Notes[3]= root.sharp(5);
        this.scale_Notes[4]= root.sharp(7);
        this.scale_Notes[5]= root.sharp(9);
        this.scale_Notes[6]= root.sharp(11);
    }

    @Override
    public String toString() {
        return  getRoot().noteQuality() + "\tMajor Scale:"
                + " " + getRoot().noteQuality()
                + "\t" + getDegree_2().noteQuality()
                + "\t" + getDegree_3().noteQuality()
                + "\t" + getDegree_4().noteQuality()
                + "\t" + getDegree_5().noteQuality()
                + "\t" + getDegree_6().noteQuality()
                + "\t" + getDegree_7().noteQuality()
                + "\t" + getRoot().noteQuality();
    }
}
