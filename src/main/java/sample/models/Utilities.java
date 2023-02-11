package sample.models;

import sample.models.exceptions.InvalidNoteException;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Button;

public final class Utilities {
    private static final String soundZipFilePath = new File("").getAbsolutePath() + "/src/main/java/sample/resources/sounds/piano.zip";
    private static final String s3SoundFileUrl = "https://midip-sounds.s3.amazonaws.com/piano/piano.zip";

    // author https://www.digitalocean.com/community/tutorials/java-download-file-url
    public static boolean downloadUsingStream() {
        if (new File(soundZipFilePath).exists()) {
            System.out.println("Sound File Zip has already been downloaded!");
            return true;
        }
        try {
            System.out.println("Starting Sound File Zip download!");
            URL url = new URL(s3SoundFileUrl);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(soundZipFilePath);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
            System.out.println("Successfully downloaded Sound File Zip : " + s3SoundFileUrl);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Utilities() {
    }

    /**
     * number of keys in an 88 Key keyboard.
     */
    public static final int NUMBER_OF_KEYS_88 = 88;

    /**
     * number of unaltered Notes.
     */
    public static final int NUMBER_OF_UNALTERED_NOTES = 8;

    /**
     * number of Note qualities.
     */
    public static final int NUM_NOTE_QUALITIES = 12;

    /**
     * Comparator for Notes.
     */
    public static final Comparator<Note> NOTE_COMPARATOR = new Comparator<>() {
        @Override
        public int compare(final Note note1, final Note note2) {
            int note1Num = note1.getName().charAt(note1.getName().length() - 1);
            int note2Num = note2.getName().charAt(note2.getName().length() - 1);
            if (note1Num != note2Num) {
                return note1Num - note2Num;
            }
            return NOTE_QUALITIES.indexOf(note1.noteQuality())
                    - NOTE_QUALITIES.indexOf(note2.noteQuality());
        }
    };

    /**
     * Comparator for Button representations
     * of Notes.
     */
    public static final Comparator<Button> KEY_NOTE_COMPARATOR = (o1, o2) -> {
        Note note1 = null;
        Note note2 = null;
        try {
            note1 = new Note(o1.getTooltip().getText());
            note2 = new Note(o2.getTooltip().getText());
        } catch (InvalidNoteException e) {
            e.printStackTrace();
        }
        return NOTE_COMPARATOR.compare(note1, note2);
    };

    /**
     * list containing all possible Note qualities.
     */
    public static final ArrayList<String> NOTE_QUALITIES = new ArrayList<>(
            List.of(
                    "A",
                    "Bb",
                    "B",
                    "C",
                    "Db",
                    "D",
                    "Eb",
                    "E",
                    "F",
                    "Gb",
                    "G",
                    "Ab"));

    /**
     * List containing the notes interval relative to the root for Major scales.
     */
    public static final ArrayList<Integer> MAJOR_SCALE_DEGREE_INTERVALS =
            new ArrayList<>(List.of(0, 2, 4, 5, 7, 9, 11, 12));

    /**
     * List containing the notes interval relative to the root for Minor scales.
     */
    public static final ArrayList<Integer> MINOR_SCALE_DEGREE_INTERVALS =
            new ArrayList<>(List.of(0, 2, 3, 5, 7, 8, 10, 12));

    /**
     * List containing the notes interval relative to the root for Mojor Pentatonic scales.
     */
    public static final ArrayList<Integer> MAJOR_PENTATONIC_SCALE_DEGREE_INTERVALS =
            new ArrayList<>(List.of(0, 2, 4, 7, 9, 12));

    /**
     * List containing the notes interval relative to the root for Minor Pentatonic scales.
     */
    public static final ArrayList<Integer> MINOR_PENTATONIC_SCALE_DEGREE_INTERVALS =
            new ArrayList<>(List.of(0, 3, 5, 7, 10, 12));

    /**
     * list containing all possible Note names
     * in order.
     */
    public static final LinkedList<String> NOTE_NAMES = generateNames();

    private static LinkedList<String> generateNames() {
        int _A_to_C = 3;
        LinkedList<String> list = new LinkedList<>();
        list.add("A0");
        list.add("Bb0");
        list.add("B0");
        for (int i = 1; i < NUMBER_OF_UNALTERED_NOTES; i++) {
            for (int j = 0; j < NUM_NOTE_QUALITIES; j++) {
                String str = NOTE_QUALITIES.get((j + _A_to_C) % 12) + i;
                list.add(str);
            }
        }
        list.add("C8");
        return list;
    }
}
