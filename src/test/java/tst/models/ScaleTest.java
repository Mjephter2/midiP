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
    public void majorScaleOutbound() throws Exception {
        assertThrows(InvalidNoteException.class, () -> new Scale(ScaleType.MAJOR_SCALE, new Note("F7")));
    }

    @Test void minorScaleOutbound() throws Exception {
        assertThrows(InvalidNoteException.class, () -> new Scale(ScaleType.MINOR_SCALE, new Note("F7")));
    }
}
