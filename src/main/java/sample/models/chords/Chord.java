package sample.models.chords;

import lombok.NonNull;
import sample.models.Invertable;
import sample.models.Note;
import sample.models.Transposable;
import sample.models.chords.generators.Major6th;
import sample.models.chords.generators.Minor6th;
import sample.models.chords.generators.Suspended4th;
import sample.models.chords.generators.Suspended2nd;
import sample.models.chords.generators.dominants.Dominant7th;
import sample.models.chords.generators.dominants.Dominant9th;
import sample.models.chords.generators.elevenths.Major11th;
import sample.models.chords.generators.elevenths.Minor11th;
import sample.models.chords.generators.ninths.Major9th;
import sample.models.chords.generators.ninths.Minor9th;
import sample.models.chords.generators.sevenths.Major7th;
import sample.models.chords.generators.sevenths.Minor7th;
import sample.models.chords.generators.thirteenths.Major13th;
import sample.models.chords.generators.triads.MajorTriad;
import sample.models.chords.generators.triads.MinorTriad;
import sample.models.exceptions.InvalidNoteException;

import java.util.Arrays;
import java.util.List;

/**
 * Class representation of a Chord.
 * A chord is essentially a combination of notes
 */
public class Chord extends Transposable implements Invertable {
    /**
     * the type of the Chord.
     */
    @NonNull
    public final ChordType type;

    /**
     * Array of Notes making the Chord.
     */
    @NonNull
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
        } else if (this.type == ChordType.MAJOR_6TH) {
            return Major6th.generateScale(root);
        } else if (this.type == ChordType.MINOR_6TH) {
            return Minor6th.generateScale(root);
        } else if (this.type == ChordType.SUSPENDED_2ND) {
            return Suspended2nd.generateScale(root);
        } else if (this.type == ChordType.SUSPENDED_4TH) {
            return Suspended4th.generateScale(root);
        } else if (this.type == ChordType.MAJOR_11TH) {
            return Major11th.generateScale(root);
        } else if (this.type == ChordType.MINOR_11TH) {
            return Minor11th.generateScale(root);
        } else if (this.type == ChordType.MAJOR_13TH) {
            return Major13th.generateScale(root);
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
                .append(this.type);
        for (Note note: chordNotes) {
            rep.append(" ").append(note.noteQuality());
        }
        return rep.toString();
    }

    /**
     *
     * @param index the index of the chord note to start chord from.
     * @return the chord Notes starting from the index specified
     */
    @Override
    public Note[] invert(int index) {
        if (index >= chordNotes.length) {
            return chordNotes;
        }
        Note[] firstNotes = Arrays.copyOfRange(chordNotes, index, chordNotes.length);
        List<Note> lastNotes = Arrays.stream(Arrays.copyOfRange(chordNotes, 0, index)).toList();
        List<Note> sharpLastNotes = lastNotes.stream().map(note -> {
            try {
                return note.sharp(12);
            } catch (InvalidNoteException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        Note[] invertedNotes = new Note[chordNotes.length];
        System.arraycopy(firstNotes, 0, invertedNotes, 0, firstNotes.length);
        System.arraycopy(sharpLastNotes.toArray(), 0, invertedNotes, firstNotes.length, lastNotes.size());
        return invertedNotes;
    }
}
