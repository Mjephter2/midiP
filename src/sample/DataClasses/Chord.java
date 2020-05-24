package sample.DataClasses;

public interface Chord {
    Chord transposeUp(int n);
    Chord transposeDown(int n);
    Note[] notes();
    String root();
    String toString();
}
