package sample.models;

import sample.models.exceptions.InvalidNoteException;

public interface Invertable {
    abstract Note[] invert(final int index) throws InvalidNoteException;
}
