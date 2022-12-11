package sample.DataClasses.chords;

import sample.DataClasses.Note;

public interface Chord {
    Chord transposeUp(int n);
    Chord transposeDown(int n);
    Note[] notes();
    String root();
    String toString();
}
