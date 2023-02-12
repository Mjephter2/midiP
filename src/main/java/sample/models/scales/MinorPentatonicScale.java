package sample.models.scales;

import sample.models.Note;
import sample.models.Utilities;

/**
 * Utility class for generating Minor Pentatonic Scales
 */
public class MinorPentatonicScale {
    private MinorPentatonicScale(){ }

    public static Note[] generateScale(final Note root) throws Exception {
        Note[] notes = new Note[Utilities.MINOR_PENTATONIC_SCALE_DEGREE_INTERVALS.size()];
        int position = 0;
        for (Integer interval: Utilities.MINOR_PENTATONIC_SCALE_DEGREE_INTERVALS) {
            notes[position++] = root.sharp(interval);
        }
        return notes;
    }
}
