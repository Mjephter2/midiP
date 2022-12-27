package sample.models.scales;

import sample.models.Note;
import sample.models.Utilities;

/**
 * Utility Class for generating Major Scales.
 */
public final class MajorScale {
    private MajorScale() { }

    /**
     * Generates the Major Scale rooted at the given Note.
     * @param root root Note of the Scale
     * @return an array of the Notes making the Scale
     * @throws Exception if the Chord type is unrecognized
     * or not yet implemented.
     */
    public static Note[] generateScale(final Note root) throws Exception {
        Note[] notes = new Note[Utilities.MAJOR_SCALE_DEGREE_INTERVALS.size()];
        int position = 0;
        for (Integer interval: Utilities.MAJOR_SCALE_DEGREE_INTERVALS) {
            notes[position++] = root.sharp(interval);
        }
        return notes;
    }
}
