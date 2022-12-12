package sample.DataClasses.scales;

import sample.DataClasses.Note;
import sample.DataClasses.exceptions.InvalidNoteException;

public interface Scale {

    Scale transposeUp(int n) throws InvalidNoteException;
    Scale transposeDown(int n) throws InvalidNoteException;
    Note[] notes();
    String root();
    String toString();

}
