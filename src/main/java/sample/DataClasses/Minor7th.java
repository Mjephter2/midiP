package sample.DataClasses;

public class Minor7th implements Chord {

    private Note[] chord_Notes = new Note[4];

    @Override
    public Minor7th transposeUp(int n) {
        return this.sharp(n);
    }
    @Override
    public Minor7th transposeDown(int n) {
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

    public Minor7th(Note scaleRoot){
        this(scaleRoot.getName());
    }
    public Minor7th(){
        this("C3");
    }
    public Minor7th(Minor7th chord){
        this(chord.chord_Notes[0].getName());
    }
    public Minor7th(String root){
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return chord_Notes[0];
    }


    private Minor7th sharp(int n){
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) + n + 10 > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Minor7th(newRoot);
    }
    private Minor7th flat(int n){
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) - n > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Minor7th(newRoot);
    }
    private void generateScale(Note root){
        this.chord_Notes[0] = root;
        this.chord_Notes[1]= root.sharp(3);
        this.chord_Notes[2]= root.sharp(7);
        this.chord_Notes[3] = root.sharp(10);
    }

    @Override
    public String toString() {
        return  getRoot().noteQuality() + " Minor 7 :"
                + " " + getRoot().noteQuality()
                + " " + chord_Notes[1].noteQuality()
                + " " + chord_Notes[2].noteQuality()
                + " " + chord_Notes[3].noteQuality();
    }

    public static void main(String[] args) {
        Chord c = new Minor7th("C3");
        System.out.println(c);
    }
}
