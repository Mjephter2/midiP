package sample.models.scales;

import sample.models.Note;

import static sample.models.Utilities.CHINESE_SCALE_DEGREE_INTERVALS;

/**
 * Utility Class for generating Chinese Scales.
 */
public class ChineseScale {
    private ChineseScale() { };

    /**
     * Generates the Chinese Scale rooted at the given Note.
     * @param root root Note of the Scale
     * @return an array of the Notes making the Scale
     * @throws Exception if the Chord type is unrecognized
     * or not yet implemented.
     */
    public static Note[] generateScale(final Note root) throws Exception {
        Note[] notes = new Note[CHINESE_SCALE_DEGREE_INTERVALS.size()];
        int position = 0;
        for (Integer interval: CHINESE_SCALE_DEGREE_INTERVALS) {
            notes[position++] = root.sharp(interval);
        }
        return notes;
    }
}
