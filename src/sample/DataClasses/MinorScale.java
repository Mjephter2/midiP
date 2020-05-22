package sample.DataClasses;

public class MinorScale  {

    public static void main(String[] args) {
        MinorScale minor_scale = new MinorScale("C");
        System.out.println(minor_scale);
        minor_scale = minor_scale.flat(7);
        System.out.println(minor_scale);

    }

    private Note root ;
    private Note degree_2 ;
    private Note degree_4 ;
    private Note degree_5 ;
    private Note degree_3 ;
    private Note degree_6 ;
    private Note degree_7 ;

    public Note getRoot() {
        return root;
    }
    public void setRoot(Note root) {
        this.root = root;
    }
    public Note getDegree_2() {
        return degree_2;
    }
    public void setDegree_2(Note degree_2) {
        this.degree_2 = degree_2;
    }
    public Note getDegree_4() {
        return degree_4;
    }
    public void setDegree_4(Note degree_4) {
        this.degree_4 = degree_4;
    }
    public Note getDegree_5() {
        return degree_5;
    }
    public void setDegree_5(Note degree_5) {
        this.degree_5 = degree_5;
    }
    public Note getDegree_3() {
        return degree_3;
    }
    public void setDegree_3(Note degree_3) {
        this.degree_3 = degree_3;
    }
    public Note getDegree_6() {
        return degree_6;
    }
    public void setDegree_6(Note degree_6) {
        this.degree_6 = degree_6;
    }
    public Note getDegree_7() {
        return degree_7;
    }
    public void setDegree_7(Note degree_7) {
        this.degree_7 = degree_7;
    }

    public MinorScale(Note scaleRoot){
        root = new Note(scaleRoot);
        degree_2 = root.sharp(2);
        degree_3 = degree_2.sharp(1);
        degree_4 = degree_3.sharp(2);
        degree_5 = degree_4.sharp(2);
        degree_6 = degree_5.sharp(1);
        degree_7 = degree_6.sharp(2);
    }
    public MinorScale(){
        root = new Note("C");
        degree_2 = root.sharp(2);
        degree_3 = degree_2.sharp(1);
        degree_4 = degree_3.sharp(2);
        degree_5 = degree_4.sharp(2);
        degree_6 = degree_5.sharp(1);
        degree_7 = degree_6.sharp(2);
    }
    public MinorScale(MinorScale scale){
        root = new Note(scale.root);
        degree_2 = root.sharp(2);
        degree_3 = degree_2.sharp(1);
        degree_4 = degree_3.sharp(2);
        degree_5 = degree_4.sharp(2);
        degree_6 = degree_5.sharp(1);
        degree_7 = degree_6.sharp(2);
    }
    public MinorScale(String root){
        Note test = new Note();
        if(!Utilities.NOTE_NAMES.contains(root)) {
            System.out.println("Invalid Note name!!!");
            System.exit(0);
        }
        this.root = new Note(root);
        degree_2 = this.root.sharp(2);
        degree_3 = degree_2.sharp(1);
        degree_4 = degree_3.sharp(2);
        degree_5 = degree_4.sharp(2);
        degree_6 = degree_5.sharp(1);
        degree_7 = degree_6.sharp(2);

    }
    private MinorScale sharp(){
        MinorScale result = new MinorScale(this.root.sharp(1));
        return result;
    }
    public MinorScale sharp(int n){
        if(n >= 12) n = n % 12;
        MinorScale result = new MinorScale(this);
        for(int i = 1; i <=n; i++) result = result.sharp();
        return result;
    }
    private MinorScale flat(){
        MinorScale result = new MinorScale(this.root.flat(1));
        return result;
    }
    public MinorScale flat(int n){
        if(n >= 12) n = n % 12;
        MinorScale result = new MinorScale(this);
        for(int i = 1; i <= n; i++) result = result.flat();
        return result;
    }

    @Override
    public String toString() {
        return  "root: " + root +
                "\ndegree_2: " + degree_2 +
                "\ndegree_3: " + degree_3 +
                "\ndegree_4: " + degree_4 +
                "\ndegree_5: " + degree_5 +
                "\ndegree_6: " + degree_6 +
                "\ndegree_7: " + degree_7 +
                "\nroot: " + root + "\n";
    }
}
