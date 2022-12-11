package sample.DataClasses.scales;

import sample.DataClasses.Note;

public interface Scale {

    Scale transposeUp(int n);
    Scale transposeDown(int n);
    Note[] notes();
    String root();
    String toString();

}
