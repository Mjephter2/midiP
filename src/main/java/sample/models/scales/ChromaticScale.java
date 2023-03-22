package sample.models.scales;

import sample.models.Note;

/**
 * Utility Class for generating Chromatic Scales.
 */
public class ChromaticScale {
    private ChromaticScale() { };

    /**
     * Generates the Chromatic Scale rooted at the given Note.
     * @param root root Note of the Scale
     * @return an array of the Notes making the Scale
     * @throws Exception if the Chord type is unrecognized
     * or not yet implemented.
     */
    public static Note[] generateScale(final Note root) throws Exception {
        Note[] notes = new Note[12];
        int position = 0;
        for (int i = 0; i < 12; i++) {
            notes[position++] = root.sharp(i);
        }
        return notes;
    }
}
