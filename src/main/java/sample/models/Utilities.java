package sample.models;

import sample.AudioPlayer;
import sample.models.exceptions.InvalidNoteException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Button;

public class Utilities {
    public static final Comparator<Note> NOTE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note note1, Note note2) {
            int note1Num = note1.getName().charAt(note1.getName().length() - 1);
            int note2Num = note2.getName().charAt(note2.getName().length() - 1);
            if(note1Num != note2Num) return note1Num - note2Num;
            return NOTE_QUALITIES.indexOf(note1.noteQuality()) - NOTE_QUALITIES.indexOf(note2.noteQuality());
        }
    };

    public static final Comparator<Button> KEY_NOTE_COMPARATOR = new Comparator<Button>() {
        @Override
        public int compare(Button o1, Button o2) {
            Note note1 = null;
            Note note2 = null;
            try {
                note1 = new Note(o1.getTooltip().getText());
                note2 = new Note(o2.getTooltip().getText());
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            return NOTE_COMPARATOR.compare(note1, note2);
        }
    };

    // list containing all possible Note qualities
    public static final ArrayList<String> NOTE_QUALITIES = new ArrayList<>(List.of("A", "Bb", "B", "C", "Db", "D",
            "Eb", "E","F", "Gb", "G", "Ab"));

    public static LinkedList<String> NOTE_NAMES = generateNames();

    private static LinkedList<String> generateNames(){
        LinkedList<String> list = new LinkedList<>();
        for(int i = 1; i < 8; i++){
            for(int j = 0; j < 12; j++){
                String str = NOTE_QUALITIES.get(j) + i;
                list.add(str);
            }
        }
        list.add("A8");
        list.add("Bb8");
        list.add("B8");
        list.add("C8");
        return list;
    }

    public static AudioPlayer AUDIOPLAYER = new AudioPlayer();
}
