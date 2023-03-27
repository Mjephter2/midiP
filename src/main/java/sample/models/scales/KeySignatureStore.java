package sample.models.scales;

import javafx.util.Pair;
import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class representing the Key Signature of a scale.
 * Find info on key signatures here: https://en.wikipedia.org/wiki/Key_signature
 */
public final class KeySignatureStore {
    /**
     * The list of sharps or flats in the key signature.
     */
    public static final List<Pair<String, String[]>> accidentals;

    static {
        try {
            accidentals = generateAccidentals();
        } catch (InvalidNoteException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Pair<String, String[]>> generateAccidentals() throws InvalidNoteException {
        List<Pair<String, String[]>> accidentals = new ArrayList<>();

        List<Note> scaleRoots = Utilities.NOTE_QUALITIES_FLAT
                .subList(0, Utilities.NOTE_QUALITIES_FLAT.size())
                .stream().map(noteName -> {
            try {
                return new Note(noteName + "1");
            } catch (InvalidNoteException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        List<Scale> scales = scaleRoots.stream().map(note -> {
            try {
                return new Scale(ScaleType.MAJOR_SCALE, note);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        for(Scale scale : scales) {
            Note[] uniqueNotes = Arrays.copyOfRange(scale.notes(), 0, scale.notes().length - 1);
            String[] scaleAccidentals = Arrays.stream(uniqueNotes)
                    .filter(note -> note.getName().length() > 2)
                    .map(note -> note.noteQuality()).toArray(String[]::new);
            accidentals.add(new Pair<>(scale.notes()[0].noteQuality(), scaleAccidentals));
        }

        return accidentals;
    }

    public static String printKeySignatures() {
        StringBuilder rep = new StringBuilder();
        for (Pair<String, String[]> keySignature : accidentals) {
            rep.append(keySignature.getKey()).append(": [ ");
            for (String accidental : keySignature.getValue()) {
                rep.append(accidental).append(" ");
            }
            rep.append("]\n");
        }
        return rep.toString();
    }
}
