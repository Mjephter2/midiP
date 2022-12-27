package sample.models.chords;

import sample.models.Note;
import sample.models.Transposable;
import sample.models.chords.generators.dominants.Dominant7th;
import sample.models.chords.generators.dominants.Dominant9th;
import sample.models.chords.generators.ninths.Major9th;
import sample.models.chords.generators.ninths.Minor9th;
import sample.models.chords.generators.sevenths.Major7th;
import sample.models.chords.generators.sevenths.Minor7th;
import sample.models.chords.generators.triads.MajorTriad;
import sample.models.chords.generators.triads.MinorTriad;

/**
 * Class representation of a Chord.
 * A chord is essentially a combination of notes
 */
public class Chord extends Transposable {
    /**
     * the type of the Chord.
     */
    private final ChordType type;

    /**
     * Array of Notes making the Chord.
     */
    private final Note[] chordNotes;

    /**
     * Constructs a Chord of the provided type,
     * rooted at the given Note.
     * @param chordType type of the Chord
     * @param root root Note of the Chord
     */
    public Chord(final ChordType chordType, final Note root)
            throws Exception {
        this.type = chordType;
        this.chordNotes = generateNotes(root);
    }

    /**
     * @return the Notes in the Chord
     */
    @Override
    public Note[] notes() {
        return chordNotes;
    }

    /**
     * Generates the Notes of the Chord.
     * @param root root Note
     * @return the Chord Notes in array form
     * @throws Exception when ChordType provided in not valid
     */
    @Override
    public Note[] generateNotes(final Note root) throws Exception {
        if (this.type == ChordType.MAJOR_TRIAD) {
            return MajorTriad.generateScale(root);
        } else if (this.type == ChordType.MINOR_TRIAD) {
            return MinorTriad.generateScale(root);
        } else if (this.type == ChordType.MAJOR_7TH) {
            return Major7th.generateScale(root);
        } else if (this.type == ChordType.MINOR_7TH) {
            return Minor7th.generateScale(root);
        } else if (this.type == ChordType.MAJOR_9TH) {
            return Major9th.generateScale(root);
        } else if (this.type == ChordType.MINOR_9TH) {
            return Minor9th.generateScale(root);
        } else if (this.type == ChordType.DOMINANT_7TH) {
            return Dominant7th.generateScale(root);
        } else if (this.type == ChordType.DOMINANT_9TH) {
            return Dominant9th.generateScale(root);
        }
        throw new Exception("Could not find ChordType specified!!!");
    }

    /**
     * @return a String representation of the Chord
     */
    public String toString() {
        StringBuilder rep = new StringBuilder();
        rep.append(chordNotes[0].noteQuality())
                .append(" ")
                .append(this.type.toString());
        for (Note note: chordNotes) {
            rep.append(" ").append(note.noteQuality());
        }
        return rep.toString();
    }
}
