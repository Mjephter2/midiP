package sample.models.chords.voicings;

import sample.models.Note;
import sample.models.chords.ChordType;
import sample.models.chords.voicings.generators.triads.MajorTriadVoicing;
import sample.models.exceptions.InvalidVoicingException;

public class ChordVoicing {

    public final ChordVoicingVersionEnum version;
    private final Note root;
    private final Note[] leftHand;
    private final Note[] rightHand;

    public ChordVoicing(final ChordType chordType, final Note root, final ChordVoicingVersionEnum version) throws Exception {
        this.version = version;
        this.root = root;

        if (chordType == ChordType.MAJOR_TRIAD) {
            MajorTriadVoicing voicing = new MajorTriadVoicing(root, version.name());
            leftHand = voicing.generateLeftHand();
            rightHand = voicing.generateRightHand();
        } else {
            throw new InvalidVoicingException("Voicing is invalid or has not been implemented!");
        }
    }

    public Note getRoot() {
        return root;
    }

    public Note[] getLeftHand() {
        return leftHand;
    }

    public Note[] getRightHand() {
        return rightHand;
    }
}
