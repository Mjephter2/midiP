package sample.models.scales;

import sample.models.Note;
import sample.models.Utilities;

/**
 * Utility class for generating Major Pentatonic Scales
 */
public class MajorPentatonicScale {
    private MajorPentatonicScale() { }

    public static Note[] generateScale(final Note root) throws Exception {
        Note[] notes = new Note[Utilities.MAJOR_PENTATONIC_SCALE_DEGREE_INTERVALS.size()];
        int position = 0;
        for (Integer interval: Utilities.MAJOR_PENTATONIC_SCALE_DEGREE_INTERVALS) {
            notes[position++] = root.sharp(interval);
        }
        return notes;
    }
}
