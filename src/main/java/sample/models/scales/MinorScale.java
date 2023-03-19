package sample.models.scales;

import sample.models.Note;

import static sample.models.Utilities.MINOR_SCALE_DEGREE_INTERVALS;

/**
 * Utility class for generating Minor Scales.
 */
public final class MinorScale {
    private MinorScale() { }

    /**
     * Generates the Minor Scale rooted at the given Note.
     * @param root root Note of the Scale
     * @return an array of the Notes making the Scale
     * @throws Exception if the Chord type is unrecognized
     * or not yet implemented.
     */
    public static Note[] generateScale(final Note root) throws Exception {
        Note[] notes = new Note[MINOR_SCALE_DEGREE_INTERVALS.size()];
        int position = 0;
        for (Integer interval: MINOR_SCALE_DEGREE_INTERVALS) {
            notes[position++] = root.sharp(interval);
        }
        return notes;
    }
}
