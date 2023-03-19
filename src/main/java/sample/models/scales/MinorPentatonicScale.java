package sample.models.scales;

import sample.models.Note;

import static sample.models.Utilities.MINOR_PENTATONIC_SCALE_DEGREE_INTERVALS;

/**
 * Utility class for generating Minor Pentatonic Scales
 */
public class MinorPentatonicScale {
    private MinorPentatonicScale(){ }

    public static Note[] generateScale(final Note root) throws Exception {
        Note[] notes = new Note[MINOR_PENTATONIC_SCALE_DEGREE_INTERVALS.size()];
        int position = 0;
        for (Integer interval: MINOR_PENTATONIC_SCALE_DEGREE_INTERVALS) {
            notes[position++] = root.sharp(interval);
        }
        return notes;
    }
}
