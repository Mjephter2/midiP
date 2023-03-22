package sample.models.scales;

import sample.models.Invertable;
import sample.models.Note;
import sample.models.Transposable;
import sample.models.exceptions.InvalidNoteException;

import java.util.Arrays;
import java.util.List;

/**
 * Class representation of a Scale.
 */
public class Scale extends Transposable implements Invertable {
    /**
     * Type of the Scale.
     */
    private final ScaleType type;

    /**
     * Array of Notes making the Scale.
     */
    private final Note[] scaleNotes;

    /**
     * Constructs a Scale of the provided type,
     * rooted at the given Note.
     * @param scaleType type of the Scale
     * @param root root Note of the Scale
     * @throws Exception when invalid ScaleType in encountered.
     */
    public Scale(final ScaleType scaleType, final Note root) throws Exception {
        this.type = scaleType;
        this.scaleNotes = this.generateNotes(root);
    }

    /**
     * @return the Notes making the Scale.
     */
    @Override
    public Note[] notes() {
        return scaleNotes;
    }

    public ScaleType getScaleType() {
        return type;
    }

    /**
     * Generates the Notes of the Scale rooted at the given Note.
     * @param root root Note
     * @return the Scale Notes in array form
     * @throws Exception when ScaleType provided in not valid
     */
    @Override
    public Note[] generateNotes(final Note root) throws Exception {
        switch (type) {
            case MAJOR_SCALE -> {
                return MajorScale.generateScale(root);
            }
            case MINOR_SCALE -> {
                return MinorScale.generateScale(root);
            }
            case MAJOR_PENTATONIC -> {
                return MajorPentatonicScale.generateScale(root);
            }
            case MINOR_PENTATONIC -> {
                return MinorPentatonicScale.generateScale(root);
            }
            case WHOLE_TONE -> {
                return WholeToneScale.generateScale(root);
            }
            case CHINESE_SCALE -> {
                return ChineseScale.generateScale(root);
            }
            case CHROMATIC_SCALE -> {
                return ChromaticScale.generateScale(root);
            }
            default -> throw new Exception("Chord type is unrecognized or not yet implemented!");
        }
    }

    /**
     * Ionian Mode is equivalent to the root Major Scale.
     * @return the original Scale
     */
    public Note[] generateIonianMode() {
        return scaleNotes;
    }

    /**
     * @return the Notes of the Scale Dorian Mode
     */
    public Note[] generateDorianMode() {
        return generateMode(ScaleMode.DORIAN);
    }

    /**
     * @return the Notes of the Scale Phrygian Mode
     */
    public Note[] generatePhrygianMode() {
        return generateMode(ScaleMode.PHRYGIAN);
    }

    /**
     * @return the Notes of the Scale Lydian Mode
     */
    public Note[] generateLydianMode() {
        return generateMode(ScaleMode.LYDYAN);
    }

    /**
     * @return the Notes of the Scale Mixolydian Mode
     */
    public Note[] generateMixolydianMode() {
        return generateMode(ScaleMode.MIXOLYDIAN);
    }

    /**
     * @return the Notes of the Scale Aeolian Mode
     */
    public Note[] generateAeolianMode() {
        return generateMode(ScaleMode.AEOLIAN);
    }

    /**
     * @return the Notes of the Scale Locrian Mode
     */
    public Note[] generateLocrianMode() {
        return generateMode(ScaleMode.LOCRIAN);
    }

    /**
     * @param mode mode of the scale
     * @return the Scale Notes with the given Mode
     */
    private Note[] generateMode(final ScaleMode mode) {
        return invert(mode.getValue());
    }

    /**
     *
     * @param index the index of the scale note to start mode from.
     * @return the Scale Notes starting from the index specified
     */
    @Override
    public Note[] invert(int index) {
        if (index >= scaleNotes.length) {
            return scaleNotes;
        }
        Note[] uniqueNotes = Arrays.copyOfRange(scaleNotes, 1, scaleNotes.length);
        Note[] firstNotes = Arrays.copyOfRange(uniqueNotes, index - 1, uniqueNotes.length);
        List<Note> lastNotes = Arrays.stream(Arrays.copyOfRange(uniqueNotes, 0, index - 1)).toList();
        List<Note> sharpLastNotes = lastNotes.stream().map(note -> {
            try {
                return note.sharp(12);
            } catch (InvalidNoteException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        Note[] invertedNotes = new Note[uniqueNotes.length];
        System.arraycopy(firstNotes, 0, invertedNotes, 0, firstNotes.length);
        System.arraycopy(sharpLastNotes.toArray(), 0, invertedNotes, firstNotes.length, lastNotes.size());
        return invertedNotes;
    }
}
