package sample.models.chords;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

public interface Chord {
    Chord transposeUp(int n) throws InvalidNoteException;
    Chord transposeDown(int n) throws InvalidNoteException;
    Note[] notes();
    String root();
    String toString();
}
