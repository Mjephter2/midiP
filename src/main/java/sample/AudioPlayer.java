package sample;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class to play back an audio file
 * using the SourceDataLine in Java Sound API.
 * @author www.codejava.net
 */
public class AudioPlayer {

    private static final Logger logger = LogManager.getLogger(AudioPlayer.class);

    /**
     * size of the byte buffer used to read/write the audio stream.
     */
    private static final int BUFFER_SIZE = 4096;

    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    public void play(final String audioFilePath) {
        File audioFile = new File(audioFilePath);
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

            //logger.info("Playback started.");

            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }

            audioLine.drain();
            audioLine.close();
            audioStream.close();

            //logger.info("Playback completed.");

        } catch (UnsupportedAudioFileException ex) {
            logger.error("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            logger.info("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            logger.info("Error playing the audio file.");
            ex.printStackTrace();
        }
    }
}
