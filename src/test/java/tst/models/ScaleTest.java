package tst.models;

import org.junit.jupiter.api.Test;
import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;
import sample.models.scales.Scale;
import sample.models.scales.ScaleType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class ScaleTest {

    @Test
    public void scaleTypeTest() throws Exception {
        Scale cMajorScale = new Scale(ScaleType.MAJOR_SCALE, new Note("C3"));
        Scale cMinorScale = new Scale(ScaleType.MINOR_SCALE, new Note("C3"));
        assertEquals(ScaleType.MAJOR_SCALE, cMajorScale.getScaleType());
        assertEquals(ScaleType.MINOR_SCALE, cMinorScale.getScaleType());
    }

    @Test
    public void majorScaleNotesTest() throws Exception {
        Scale cMajorScale = new Scale(ScaleType.MAJOR_SCALE, new Note("C3"));
        assertEquals(new Note("C3").getName(), cMajorScale.notes()[0].getName());
        assertEquals(new Note("D3").getName(), cMajorScale.notes()[1].getName());
        assertEquals(new Note("E3").getName(), cMajorScale.notes()[2].getName());
        assertEquals(new Note("F3").getName(), cMajorScale.notes()[3].getName());
        assertEquals(new Note("G3").getName(), cMajorScale.notes()[4].getName());
        assertEquals(new Note("A3").getName(), cMajorScale.notes()[5].getName());
        assertEquals(new Note("B3").getName(), cMajorScale.notes()[6].getName());
    }

    @Test
    public void minorScaleNotesTest() throws Exception {
        Scale cMajorScale = new Scale(ScaleType.MINOR_SCALE, new Note("C3"));
        assertEquals(new Note("C3").getName(), cMajorScale.notes()[0].getName());
        assertEquals(new Note("D3").getName(), cMajorScale.notes()[1].getName());
        assertEquals(new Note("Eb3").getName(), cMajorScale.notes()[2].getName());
        assertEquals(new Note("F3").getName(), cMajorScale.notes()[3].getName());
        assertEquals(new Note("G3").getName(), cMajorScale.notes()[4].getName());
        assertEquals(new Note("Ab3").getName(), cMajorScale.notes()[5].getName());
        assertEquals(new Note("Bb3").getName(), cMajorScale.notes()[6].getName());
    }

    @Test
    public void majorScaleOutbound() {
        assertThrows(InvalidNoteException.class, () -> new Scale(ScaleType.MAJOR_SCALE, new Note("F7")));
    }

    @Test void minorScaleOutbound() {
        assertThrows(InvalidNoteException.class, () -> new Scale(ScaleType.MINOR_SCALE, new Note("F7")));
    }

    @Test
    public void majorPentatonicNotesTest() throws Exception {
        Scale cMajorPentatonicScale = new Scale(ScaleType.MAJOR_PENTATONIC, new Note("C3"));
        assertEquals(new Note("C3").getName(), cMajorPentatonicScale.notes()[0].getName());
        assertEquals(new Note("D3").getName(), cMajorPentatonicScale.notes()[1].getName());
        assertEquals(new Note("E3").getName(), cMajorPentatonicScale.notes()[2].getName());
        assertEquals(new Note("G3").getName(), cMajorPentatonicScale.notes()[3].getName());
        assertEquals(new Note("A3").getName(), cMajorPentatonicScale.notes()[4].getName());
        assertEquals(new Note("C4").getName(), cMajorPentatonicScale.notes()[5].getName());
    }

    @Test
    public void majorPentatonicScaleOutbound() {
        assertThrows(InvalidNoteException.class, () -> new Scale(ScaleType.MAJOR_PENTATONIC, new Note("F7")));
    }

    @Test
    public void minorPentatonicNotesTest() throws Exception {
        Scale cMinorPentatonicScale = new Scale(ScaleType.MINOR_PENTATONIC, new Note("C3"));
        assertEquals(new Note("C3").getName(), cMinorPentatonicScale.notes()[0].getName());
        assertEquals(new Note("Eb3").getName(), cMinorPentatonicScale.notes()[1].getName());
        assertEquals(new Note("F3").getName(), cMinorPentatonicScale.notes()[2].getName());
        assertEquals(new Note("G3").getName(), cMinorPentatonicScale.notes()[3].getName());
        assertEquals(new Note("Bb3").getName(), cMinorPentatonicScale.notes()[4].getName());
        assertEquals(new Note("C4").getName(), cMinorPentatonicScale.notes()[5].getName());
    }

    @Test
    public void minorPentatonicScaleOutbound() {
        assertThrows(InvalidNoteException.class, () -> new Scale(ScaleType.MINOR_PENTATONIC, new Note("F7")));
    }

    @Test
    public void scaleModesTest() throws Exception {
        Scale cMajorScale = new Scale(ScaleType.MAJOR_SCALE, new Note("C3"));

        Note[] ionianMode = cMajorScale.generateIonianMode();
        assertEquals("C3", ionianMode[0].getName());
        assertEquals("D3", ionianMode[1].getName());
        assertEquals("E3", ionianMode[2].getName());
        assertEquals("F3", ionianMode[3].getName());
        assertEquals("G3", ionianMode[4].getName());
        assertEquals("A3", ionianMode[5].getName());
        assertEquals("B3", ionianMode[6].getName());
        assertEquals("C4", ionianMode[7].getName());

        Note[] dorianMode = cMajorScale.generateDorianMode();
        assertEquals("D3", dorianMode[0].getName());
        assertEquals("E3", dorianMode[1].getName());
        assertEquals("F3", dorianMode[2].getName());
        assertEquals("G3", dorianMode[3].getName());
        assertEquals("A3", dorianMode[4].getName());
        assertEquals("B3", dorianMode[5].getName());
        assertEquals("C4", dorianMode[6].getName());

        Note[] phrygianMode = cMajorScale.generatePhrygianMode();
        assertEquals("E3", phrygianMode[0].getName());
        assertEquals("F3", phrygianMode[1].getName());
        assertEquals("G3", phrygianMode[2].getName());
        assertEquals("A3", phrygianMode[3].getName());
        assertEquals("B3", phrygianMode[4].getName());
        assertEquals("C4", phrygianMode[5].getName());
        assertEquals("D4", phrygianMode[6].getName());

        Note[] lydianMode = cMajorScale.generateLydianMode();
        assertEquals("F3", lydianMode[0].getName());
        assertEquals("G3", lydianMode[1].getName());
        assertEquals("A3", lydianMode[2].getName());
        assertEquals("B3", lydianMode[3].getName());
        assertEquals("C4", lydianMode[4].getName());
        assertEquals("D4", lydianMode[5].getName());
        assertEquals("E4", lydianMode[6].getName());

        Note[] mixolydianMode = cMajorScale.generateMixolydianMode();
        assertEquals("G3", mixolydianMode[0].getName());
        assertEquals("A3", mixolydianMode[1].getName());
        assertEquals("B3", mixolydianMode[2].getName());
        assertEquals("C4", mixolydianMode[3].getName());
        assertEquals("D4", mixolydianMode[4].getName());
        assertEquals("E4", mixolydianMode[5].getName());
        assertEquals("F4", mixolydianMode[6].getName());

        Note[] aeolianMode = cMajorScale.generateAeolianMode();
        assertEquals("A3", aeolianMode[0].getName());
        assertEquals("B3", aeolianMode[1].getName());
        assertEquals("C4", aeolianMode[2].getName());
        assertEquals("D4", aeolianMode[3].getName());
        assertEquals("E4", aeolianMode[4].getName());
        assertEquals("F4", aeolianMode[5].getName());
        assertEquals("G4", aeolianMode[6].getName());

        Note[] locrianMode = cMajorScale.generateLocrianMode();
        assertEquals("B3", locrianMode[0].getName());
        assertEquals("C4", locrianMode[1].getName());
        assertEquals("D4", locrianMode[2].getName());
        assertEquals("E4", locrianMode[3].getName());
        assertEquals("F4", locrianMode[4].getName());
        assertEquals("G4", locrianMode[5].getName());
        assertEquals("A4", locrianMode[6].getName());
    }

    @Test
    public void wholeToneScaleTest() throws Exception {
        Scale cWholeToneScale = new Scale(ScaleType.WHOLE_TONE, new Note("C3"));
        assertEquals("C3", cWholeToneScale.notes()[0].getName());
        assertEquals("D3", cWholeToneScale.notes()[1].getName());
        assertEquals("E3", cWholeToneScale.notes()[2].getName());
        assertEquals("Gb3", cWholeToneScale.notes()[3].getName());
        assertEquals("Ab3", cWholeToneScale.notes()[4].getName());
        assertEquals("Bb3", cWholeToneScale.notes()[5].getName());
        assertEquals("C4", cWholeToneScale.notes()[6].getName());
    }
}
