package sample.models.scales;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

public interface Scale {

    Scale transposeUp(int n) throws InvalidNoteException;
    Scale transposeDown(int n) throws InvalidNoteException;
    Note[] notes();
    String root();
    String toString();

}
