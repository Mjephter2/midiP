package sample.models.scales;

import sample.models.Note;

import static sample.models.Utilities.MAJOR_PENTATONIC_SCALE_DEGREE_INTERVALS;

/**
 * Utility class for generating Major Pentatonic Scales
 */
public class MajorPentatonicScale {
    private MajorPentatonicScale() { }

    public static Note[] generateScale(final Note root) throws Exception {
        Note[] notes = new Note[MAJOR_PENTATONIC_SCALE_DEGREE_INTERVALS.size()];
        int position = 0;
        for (Integer interval: MAJOR_PENTATONIC_SCALE_DEGREE_INTERVALS) {
            notes[position++] = root.sharp(interval);
        }
        return notes;
    }
}
