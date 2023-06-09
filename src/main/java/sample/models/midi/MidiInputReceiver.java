package sample.models.midi;

import javafx.scene.control.Button;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Class to handle midi events.
 */
public final class MidiInputReceiver implements Receiver {
    public static final Logger LOGGER = Logger.getLogger(MidiInputReceiver.class.getName());
    private static final String BUTTON_ORIGINAL_STYLE = new Button().getStyle();

    /**
     * name of the receiver.
     */
    private final String name;

    private final LinkedList<Button> whiteKeys;
    private final LinkedList<Button> keyBoard;

    /**
     * Construct a receiver with the String argument as name.
     * @param receiverName name of receiver
     */
    public MidiInputReceiver(final String receiverName, LinkedList<Button> keyBoard, final LinkedList<Button> whiteKeys, LinkedList<Button> blackKeys) {
        this.name = receiverName;
        this.whiteKeys = whiteKeys;
        this.keyBoard = keyBoard;
    }

    /**
     * Index of the last key pressed
     * as captured from a physical midi device.
     */
    private int lastKeyPressedIndex = 0;

    private void keyPressedReleased(final int key) {
        Button button = keyBoard.get(key);
        final boolean isWhiteKey = whiteKeys.contains(button);
        if (button.getStyle().contains("blue")
                || button.getStyle().contains("red")) {
            if (isWhiteKey) {
                button.setStyle(BUTTON_ORIGINAL_STYLE);
            } else {
                button.setStyle("-fx-background-color: black");
            }
        } else {
            if (isWhiteKey) {
                button.setStyle("-fx-background-color: blue");
            } else {
                button.setStyle("-fx-background-color: red");
            }
        }
    }

    /**
     * @param msg the MIDI message to send
     * @param timeStamp the time-stamp for the message, in microseconds
     */
    @Override
    public void send(final MidiMessage msg, final long timeStamp) {
        byte[] aMsg = msg.getMessage();
        if ((lastKeyPressedIndex == 127 && aMsg[2] == 0)
                || aMsg[2] == 127) {
            lastKeyPressedIndex = aMsg[2];
            return;
        }
        LOGGER.info("Message: " + aMsg[0] + ", " + aMsg[1] + ", " + aMsg[2]);
        if (aMsg[1] - 21 < 0) {
            return;
        }
        keyPressedReleased(aMsg[1] - 21);
        lastKeyPressedIndex = aMsg[2];
    }

    @Override
    public void close() {
        closeAllTransmitters();
    }

    /**
     * Closes all the open midi transmitters available in the system.
     */
    private void closeAllTransmitters() {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : infos) {
            try {
                device = MidiSystem.getMidiDevice(info);
                if (device.isOpen()) {
                    device.close();
                    LOGGER.info(device.getDeviceInfo() + " Was closed");
                }
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
        }
    }
}
