package sample.models.scales;

import sample.models.Note;
import sample.models.Transposable;

/**
 * Class representation of a Scale.
 */
public class Scale extends Transposable {
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
            default -> throw new Exception("Chord type is unrecognized or not yet implemented!");
        }
    }
}
