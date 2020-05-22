package sample;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This is an example program that demonstrates how to play back an audio file
 * using the SourceDataLine in Java Sound API.
 * @author www.codejava.net
 *
 */
public class AudioPlayer {

    // size of the byte buffer used to read/write the audio stream
    private static final int BUFFER_SIZE = 4096;

    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    public void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
            audioLine.open(format);

            audioLine.start();

            //System.out.println("Playback started.");

            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }

            audioLine.drain();
            audioLine.close();
            audioStream.close();

            //System.out.println("Playback completed.");

        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String audioFilePath = "src/Sample sounds/PinkPanther60.wav";
        AudioPlayer player = new AudioPlayer();
        player.play(audioFilePath);
    }

}