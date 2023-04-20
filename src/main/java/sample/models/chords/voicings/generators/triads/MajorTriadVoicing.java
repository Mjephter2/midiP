package sample.models.chords.voicings.generators.triads;

import sample.models.Note;
import sample.models.chords.Chord;
import sample.models.chords.ChordType;
import sample.models.chords.voicings.ChordVoicingVersionEnum;
import sample.models.exceptions.InvalidNoteException;
import sample.models.exceptions.InvalidVoicingException;

import java.util.Arrays;

public class MajorTriadVoicing {

    private final Note root;

    public MajorTriadVoicing(final Note root, final String version) throws Exception {
        if (Arrays.stream(ChordVoicingVersionEnum.values()).noneMatch(v -> v.name().equals(version))) {
            throw new InvalidVoicingException("Invalid voicing version provided");
        }
        this.root = root;
    }

    public Note[] generateLeftHand() throws InvalidNoteException {
        return new Note[] {root, root.sharp(12)};
    }

    public Note[] generateRightHand() throws Exception {
        return new Chord(ChordType.MAJOR_TRIAD, root.sharp(24)).notes();
    }
}
