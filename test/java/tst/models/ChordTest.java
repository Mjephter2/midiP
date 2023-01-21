package tst.models;

import org.junit.jupiter.api.Test;
import sample.models.Note;
import sample.models.chords.Chord;
import sample.models.chords.ChordType;

import static org.junit.Assert.assertEquals;

public class ChordTest {

    @Test
    public void chordTypeTest() throws Exception {
        Chord cMajorTriadChord = new Chord(ChordType.MAJOR_TRIAD, new Note());
        Chord cMinorTriadChord = new Chord(ChordType.MINOR_TRIAD, new Note());
        Chord cMajor7thChord = new Chord(ChordType.MAJOR_7TH, new Note());
        Chord cMinor7thChord = new Chord(ChordType.MINOR_7TH, new Note());
        Chord cMajor9thChord = new Chord(ChordType.MAJOR_9TH, new Note());
        Chord cMinor9thChord = new Chord(ChordType.MINOR_9TH, new Note());
        Chord cDominant7thChord = new Chord(ChordType.DOMINANT_7TH, new Note());
        Chord cDominant9thChord = new Chord(ChordType.DOMINANT_9TH, new Note());

        assertEquals(ChordType.MAJOR_TRIAD, cMajorTriadChord.type);
        assertEquals(ChordType.MINOR_TRIAD, cMinorTriadChord.type);
        assertEquals(ChordType.MAJOR_7TH, cMajor7thChord.type);
        assertEquals(ChordType.MINOR_7TH, cMinor7thChord.type);
        assertEquals(ChordType.MAJOR_9TH, cMajor9thChord.type);
        assertEquals(ChordType.MINOR_9TH, cMinor9thChord.type);
        assertEquals(ChordType.DOMINANT_7TH, cDominant7thChord.type);
        assertEquals(ChordType.DOMINANT_9TH, cDominant9thChord.type);
    }

    @Test
    public void triadTest() throws Exception {
        Chord cMajorTriadChord = new Chord(ChordType.MAJOR_TRIAD, new Note());
        Chord cMinorTriadChord = new Chord(ChordType.MINOR_TRIAD, new Note());

        assertEquals(new Note().getName(), cMajorTriadChord.notes()[0].getName());
        assertEquals(new Note("E1").getName(), cMajorTriadChord.notes()[1].getName());
        assertEquals(new Note("G1").getName(), cMajorTriadChord.notes()[2].getName());

        assertEquals(new Note().getName(), cMinorTriadChord.notes()[0].getName());
        assertEquals(new Note("Eb1").getName(), cMinorTriadChord.notes()[1].getName());
        assertEquals(new Note("G1").getName(), cMinorTriadChord.notes()[2].getName());
    }

    @Test
    public void chord7thTest() throws Exception {
        Chord cMajor7thChord = new Chord(ChordType.MAJOR_7TH, new Note());
        Chord cMinor7thChord = new Chord(ChordType.MINOR_7TH, new Note());

        assertEquals(new Note().getName(), cMajor7thChord.notes()[0].getName());
        assertEquals(new Note("E1").getName(), cMajor7thChord.notes()[1].getName());
        assertEquals(new Note("G1").getName(), cMajor7thChord.notes()[2].getName());
        assertEquals(new Note("B1").getName(), cMajor7thChord.notes()[3].getName());

        assertEquals(new Note().getName(), cMinor7thChord.notes()[0].getName());
        assertEquals(new Note("Eb1").getName(), cMinor7thChord.notes()[1].getName());
        assertEquals(new Note("G1").getName(), cMinor7thChord.notes()[2].getName());
        assertEquals(new Note("Bb1").getName(), cMinor7thChord.notes()[3].getName());
    }

    @Test
    public void chord9thTest() throws Exception {
        Chord cMajor9thChord = new Chord(ChordType.MAJOR_9TH, new Note());
        Chord cMinor9thChord = new Chord(ChordType.MINOR_9TH, new Note());

        assertEquals(new Note().getName(), cMajor9thChord.notes()[0].getName());
        assertEquals(new Note("E1").getName(), cMajor9thChord.notes()[1].getName());
        assertEquals(new Note("G1").getName(), cMajor9thChord.notes()[2].getName());
        assertEquals(new Note("B1").getName(), cMajor9thChord.notes()[3].getName());
        assertEquals(new Note("D2").getName(), cMajor9thChord.notes()[4].getName());

        assertEquals(new Note().getName(), cMinor9thChord.notes()[0].getName());
        assertEquals(new Note("Eb1").getName(), cMinor9thChord.notes()[1].getName());
        assertEquals(new Note("G1").getName(), cMinor9thChord.notes()[2].getName());
        assertEquals(new Note("Bb1").getName(), cMinor9thChord.notes()[3].getName());
        assertEquals(new Note("D2").getName(), cMajor9thChord.notes()[4].getName());
    }

    @Test
    public void dominantChordTest() throws Exception {
        Chord cDominant7thChord = new Chord(ChordType.DOMINANT_7TH, new Note());
        Chord cDominant9thChord = new Chord(ChordType.DOMINANT_9TH, new Note());

        assertEquals(new Note().getName(), cDominant7thChord.notes()[0].getName());
        assertEquals(new Note("E1").getName(), cDominant7thChord.notes()[1].getName());
        assertEquals(new Note("G1").getName(), cDominant7thChord.notes()[2].getName());
        assertEquals(new Note("Bb1").getName(), cDominant7thChord.notes()[3].getName());

        assertEquals(new Note().getName(), cDominant9thChord.notes()[0].getName());
        assertEquals(new Note("E1").getName(), cDominant9thChord.notes()[1].getName());
        assertEquals(new Note("G1").getName(), cDominant9thChord.notes()[2].getName());
        assertEquals(new Note("Bb1").getName(), cDominant9thChord.notes()[3].getName());
        assertEquals(new Note("D2").getName(), cDominant9thChord.notes()[4].getName());
    }
}
