package sample;

import sample.models.Note;
import sample.models.exceptions.InvalidNoteException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Class to play back an audio file
 * using the SourceDataLine in Java Sound API.
 * @author www.codejava.net
 */
public class AudioPlayer {

    /**
     * size of the byte buffer used to read/write the audio stream.
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Play the given Note
     * @param note to be played.
     */
    public static void play(final Note note) {
        File audioFile = new File(new File("").getAbsolutePath()
                + "/src/downloads/sounds/piano/ff."
                + note.toString()
                + ".aiff");

        AudioInputStream audioStream;
        AudioFormat format;
        DataLine.Info info;
        SourceDataLine audioLine;

        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            format = audioStream.getFormat();
            info = new DataLine.Info(SourceDataLine.class, format);
            audioLine = (SourceDataLine) AudioSystem.getLine(info);
            audioLine.open(format);

            audioLine.start();

            System.out.println(String.format("Playing %s.", note));

            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }

            audioLine.drain();
            audioLine.close();
            audioStream.close();

            System.out.println("Playback completed.");

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (InterruptedIOException ex) {
            System.out.println("Playback Interrupted.");
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
