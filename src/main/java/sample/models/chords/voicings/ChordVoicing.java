package sample.models.chords.voicings;

import sample.models.Note;
import sample.models.chords.ChordType;
import sample.models.chords.voicings.generators.triads.TriadVoicing;
import sample.models.exceptions.InvalidNoteException;
import sample.models.exceptions.InvalidVoicingException;

public interface ChordVoicing {
    Note[] generateLeftHand() throws InvalidNoteException;

    Note[] generateRightHand() throws InvalidNoteException, InvalidVoicingException;
}
