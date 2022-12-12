package sample.DataClasses.chords;

import sample.DataClasses.Note;
import sample.DataClasses.exceptions.InvalidNoteException;

public interface Chord {
    Chord transposeUp(int n) throws InvalidNoteException;
    Chord transposeDown(int n) throws InvalidNoteException;
    Note[] notes();
    String root();
    String toString();
}
