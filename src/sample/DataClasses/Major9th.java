package sample.DataClasses;

public class Major9th implements Chord {
    private Note[] chord_Notes = new Note[5];

    @Override
    public Major9th transposeUp(int n) {
        return this.sharp(n);
    }
    @Override
    public Major9th transposeDown(int n) {
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

    public Major9th(Note scaleRoot){
        this(scaleRoot.getName());
    }
    public Major9th(){
        this("C3");
    }
    public Major9th(Major9th chord){
        this(chord.chord_Notes[0].getName());
    }
    public Major9th(String root){
        Note newRoot = new Note(root);
        generateScale(newRoot);
    }

    public Note getRoot() {
        return chord_Notes[0];
    }


    private Major9th sharp(int n){
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) + n + 14 > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Major9th(newRoot);
    }
    private Major9th flat(int n){
        if(Utilities.NOTE_NAMES.indexOf(this.chord_Notes[0].getName()) - n > 87) return this;
        String newRoot = this.chord_Notes[0].sharp(n).getName();
        return new Major9th(newRoot);
    }
    private void generateScale(Note root){
        this.chord_Notes[0] = root;
        this.chord_Notes[1]= root.sharp(4);
        this.chord_Notes[2]= root.sharp(7);
        this.chord_Notes[3] = root.sharp(11);
        this.chord_Notes[4] = root.sharp(14);
    }

    @Override
    public String toString() {
        return  getRoot().noteQuality() + " Major 7 :"
                + " " + chord_Notes[0].noteQuality()
                + " " + chord_Notes[1].noteQuality()
                + " " + chord_Notes[2].noteQuality()
                + " " + chord_Notes[3].noteQuality()
                + " " + chord_Notes[4].noteQuality();
    }

    public static void main(String[] args) {
        Chord c = new Major9th("A3");
        System.out.println(c);
    }
}
