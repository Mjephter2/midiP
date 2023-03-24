package tst.models;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import sample.models.Note;
import sample.models.NotesNamingMode;
import sample.models.exceptions.InvalidNoteException;

import static org.junit.jupiter.api.Assertions.*;

class NoteTest {

    @Mock
    private Note note;

    @Test
    public void invalidNoteTest() {
        assertThrows(InvalidNoteException.class, () -> new Note("Bad Note Name"));
        assertThrows(InvalidNoteException.class, () -> new Note("C10"));
    }
    @Test
    void getNameTest() throws InvalidNoteException {
        Note c3Note = new Note("C3");
        assertEquals("C3", c3Note.getName());
    }

    @Test
    void sharpTest() throws InvalidNoteException {
        Note root = new Note("Ab7");
        assertEquals(new Note("A7").getName(), root.sharp(1).getName());
        assertEquals(new Note("C8").getName(), root.sharp(4).getName());
    }

    @Test
    void flatTest() throws InvalidNoteException {
        Note root = new Note("C1");
        assertEquals(new Note("B0").getName(), root.flat(1).getName());
        assertThrows(InvalidNoteException.class,() -> root.flat(4));
    }

    @Test
    void noteQualityTest() throws InvalidNoteException {
        Note note = new Note("Gb1");
        assertEquals("Gb", note.noteQuality());
    }

    @Test
    public void noteNamingModeTest() throws InvalidNoteException {
        Note note = new Note("Ab1");
        assertEquals("Ab1", note.getName());
        assertEquals(NotesNamingMode.FLAT_MODE, note.notesNamingMode);

        note.notesNamingMode = NotesNamingMode.SHARP_MODE;
        Note note2 = new Note("Ab1");
        assertEquals("G#1", note2.getName());
    }
}