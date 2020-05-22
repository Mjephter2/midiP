package sample.DataClasses;

import sample.AudioPlayer;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Utilities {
    public static final Comparator<Note> NOTE_COMPARATOR = new Comparator<Note>() {
        @Override
        public int compare(Note note1, Note note2) {
            int note1Num = note1.getName().charAt(note1.getName().length() - 1);
            int note2Num = note2.getName().charAt(note2.getName().length() - 1);
            if(note1Num != note2Num) return note1Num - note2Num;
            return note1.getName().substring(0,note1.getName().length() - 1).compareTo(note2.getName().substring(0,note2.getName().length() - 1));
        }
    };

    // list containing all possible Note qualities
    public static final LinkedList<String> NOTE_QUALITIES = new LinkedList(List.of("A","Bb", "B","C", "Db", "D",
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

    public static void main(String[] args) {
        System.out.print(NOTE_NAMES.size());
    }

    public static AudioPlayer AUDIOPLAYER = new AudioPlayer();
}
