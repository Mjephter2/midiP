package sample.DataClasses;

public interface Scale {

    Scale transposeUp(int n);
    Scale transposeDown(int n);
    Note[] notes();
    String root();
    String toString();

}
