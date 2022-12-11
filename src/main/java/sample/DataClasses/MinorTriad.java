package sample.DataClasses;

public class MinorTriad implements Chord {

    private Note[] chord_Notes = new Note[3];

    @Override
    public MinorTriad transposeUp(int n) {
        return this.sharp(n);
    }
    @Override
    public MinorTriad transposeDown(int n) {
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

    public MinorTriad(Note scaleRoot){
        this(scaleRoot.getName());
    }
    public MinorTriad(){
        this("C3");
    }
    public MinorTriad(MinorTriad triad){
        this(triad.chord_Notes[0].getName());
    }
    public MinorTriad(String root){
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return chord_Notes[0];
    }


    private MinorTriad sharp(int n){
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) + n + 7 > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new MinorTriad(newRoot);
    }
    private MinorTriad flat(int n){
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) - n > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new MinorTriad(newRoot);
    }
    private void generateScale(Note root){
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

    public static void main(String[] args) {
        Chord c = new MinorTriad("C3");
        System.out.println(c.transposeUp(1));
    }
}
