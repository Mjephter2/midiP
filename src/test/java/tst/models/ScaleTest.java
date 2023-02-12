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
        assertEquals("C3", ionianMode[0].toString());
        assertEquals("D3", ionianMode[1].toString());
        assertEquals("E3", ionianMode[2].toString());
        assertEquals("F3", ionianMode[3].toString());
        assertEquals("G3", ionianMode[4].toString());
        assertEquals("A3", ionianMode[5].toString());
        assertEquals("B3", ionianMode[6].toString());
        assertEquals("C4", ionianMode[7].toString());

        Note[] dorianMode = cMajorScale.generateDorianMode();
        assertEquals("D3", dorianMode[0].toString());
        assertEquals("E3", dorianMode[1].toString());
        assertEquals("F3", dorianMode[2].toString());
        assertEquals("G3", dorianMode[3].toString());
        assertEquals("A3", dorianMode[4].toString());
        assertEquals("B3", dorianMode[5].toString());
        assertEquals("C4", dorianMode[6].toString());

        Note[] phrygianMode = cMajorScale.generatePhrygianMode();
        assertEquals("E3", phrygianMode[0].toString());
        assertEquals("F3", phrygianMode[1].toString());
        assertEquals("G3", phrygianMode[2].toString());
        assertEquals("A3", phrygianMode[3].toString());
        assertEquals("B3", phrygianMode[4].toString());
        assertEquals("C4", phrygianMode[5].toString());
        assertEquals("D4", phrygianMode[6].toString());

        Note[] lydianMode = cMajorScale.generateLydianMode();
        assertEquals("F3", lydianMode[0].toString());
        assertEquals("G3", lydianMode[1].toString());
        assertEquals("A3", lydianMode[2].toString());
        assertEquals("B3", lydianMode[3].toString());
        assertEquals("C4", lydianMode[4].toString());
        assertEquals("D4", lydianMode[5].toString());
        assertEquals("E4", lydianMode[6].toString());

        Note[] mixolydianMode = cMajorScale.generateMixolydianMode();
        assertEquals("G3", mixolydianMode[0].toString());
        assertEquals("A3", mixolydianMode[1].toString());
        assertEquals("B3", mixolydianMode[2].toString());
        assertEquals("C4", mixolydianMode[3].toString());
        assertEquals("D4", mixolydianMode[4].toString());
        assertEquals("E4", mixolydianMode[5].toString());
        assertEquals("F4", mixolydianMode[6].toString());

        Note[] aeolianMode = cMajorScale.generateAeolianMode();
        assertEquals("A3", aeolianMode[0].toString());
        assertEquals("B3", aeolianMode[1].toString());
        assertEquals("C4", aeolianMode[2].toString());
        assertEquals("D4", aeolianMode[3].toString());
        assertEquals("E4", aeolianMode[4].toString());
        assertEquals("F4", aeolianMode[5].toString());
        assertEquals("G4", aeolianMode[6].toString());

        Note[] locrianMode = cMajorScale.generateLocrianMode();
        assertEquals("B3", locrianMode[0].toString());
        assertEquals("C4", locrianMode[1].toString());
        assertEquals("D4", locrianMode[2].toString());
        assertEquals("E4", locrianMode[3].toString());
        assertEquals("F4", locrianMode[4].toString());
        assertEquals("G4", locrianMode[5].toString());
        assertEquals("A4", locrianMode[6].toString());
    }
}
