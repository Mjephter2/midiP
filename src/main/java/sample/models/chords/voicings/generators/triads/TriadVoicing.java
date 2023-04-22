package sample.models.chords.voicings.generators.triads;

import sample.models.Note;
import sample.models.chords.Chord;
import sample.models.chords.ChordType;
import sample.models.chords.voicings.ChordVoicing;
import sample.models.chords.voicings.ChordVoicingVersionEnum;
import sample.models.exceptions.InvalidNoteException;
import sample.models.exceptions.InvalidVoicingException;

import java.util.Arrays;

public class TriadVoicing implements ChordVoicing {

    private final Note root;
    private final ChordType chordType;

    public TriadVoicing(final Note root, ChordType chordType, final String version) throws Exception {
        if (Arrays.stream(ChordVoicingVersionEnum.values()).noneMatch(v -> v.name().equals(version))) {
            throw new InvalidVoicingException("Invalid voicing version provided");
        }
        this.root = root;
        this.chordType = chordType;
    }

    public Note[] generateLeftHand() throws InvalidNoteException {
        return new Note[] {root, root.sharp(12)};
    }

    public Note[] generateRightHand() throws InvalidNoteException, InvalidVoicingException {
        if (chordType == ChordType.MAJOR_TRIAD) {
            try {
                return new Chord(ChordType.MAJOR_TRIAD, root.sharp(24)).notes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  else if (chordType == ChordType.MINOR_TRIAD) {
            try {
                return new Chord(ChordType.MINOR_TRIAD, root.sharp(24)).notes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new InvalidVoicingException("Invalid voicing version provided");
    }
}
