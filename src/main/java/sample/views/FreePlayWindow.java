package sample.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.models.FillerButton;
import sample.models.Utilities;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Transmitter;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.MidiMessage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class FreePlayWindow extends Application {
    /**
     * Array of Button representing the keys on the piano.
     */
    private final Button[] keyBoard = new Button[Utilities.NUMBER_OF_KEYS_88];

    /**\
     * Array of white keys on the piano.
     */
    private final LinkedList<Button> whiteKeys = new LinkedList<>();

    /**
     * Array of black keys on the piano.
     */
    private final LinkedList<Button> blackKeys = new LinkedList<>();

    /**
     * Preferred window width.
     */
    private static final int SCREEN_WIDTH = 1248;

    /**
     * White key width.
     */
    private static final int WHITE_KEY_SIZE = 24;

    /**
     * Black keys width.
     */
    private static final int BLACK_KEY_SIZE = 20;

    /**
     * Default Button style.
     */
    private static final String BUTTON_ORIGINAL_STYLE = new Button().getStyle();

    /**
     * Midi Receiver to capture keyboard / midi events.
     */
    private final MidiInputReceiver midiInputReceiver =
            new MidiInputReceiver("Receiver");

    /**
     * Index the last key pressed
     * as captured from a physical midi device.
     */
    private int lastKeyPressedIndex = 0;

    private void initialize() {
        int i = 0;
        while (i < Utilities.NUMBER_OF_KEYS_88) {
            ArrayList<Integer> blackIndex = new ArrayList<>();
            blackIndex.add(1);
            blackIndex.add(4);
            blackIndex.add(6);
            blackIndex.add(9);
            blackIndex.add(11);
            Button button = new Button(Utilities.NOTE_NAMES.get(i));
            if (blackIndex.contains(i % 12)) {
                blackKeys.add(button);
            } else {
                whiteKeys.add(button);
            }
            keyBoard[i] = button;
            i++;
        }

        for (Button button : keyBoard) {
            button.setOnMousePressed(mouseEvent
                    ->
                    button.setStyle("-fx-background-color: gray"));
            button.setOnMouseReleased(mouseEvent -> {
                if (blackKeys.contains(button)) {
                    button.setStyle("-fx-background-color: black");
                } else if (whiteKeys.contains(button)) {
                    button.setStyle(BUTTON_ORIGINAL_STYLE);
                }
            });
        }
    }

    private void openAllTransmitters() {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : infos) {
            try {
                device = MidiSystem.getMidiDevice(info);
                int numTrans = device.getTransmitters().size();
                System.out.println(info + " has " + numTrans + " transmitters");
                List<Transmitter> transmitters = device.getTransmitters();
                for (Transmitter transmitter : transmitters) {
                    transmitter.setReceiver(midiInputReceiver);
                }
                Transmitter trans = device.getTransmitter();
                trans.setReceiver(midiInputReceiver);
                device.open();
                System.out.println(device.getDeviceInfo() + " Was Opened");
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeAllTransmitters() {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : infos) {
            try {
                device = MidiSystem.getMidiDevice(info);
                if (device.isOpen()) {
                    device.close();
                    System.out.println(device.getDeviceInfo() + " Was closed");
                }
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage freePlay) {
        initialize();
        openAllTransmitters();
        BorderPane root = new BorderPane();
        freePlay.setOnCloseRequest(windowEvent -> {
            Main mainWindow = new Main();
            Stage mainStage = new Stage();
            try {
                mainWindow.start(mainStage);
                freePlay.close();
                mainStage.show();
            } catch (Exception ex) {
                System.out.println("Error opening Main Window!!!");
                ex.printStackTrace();
            }
            closeAllTransmitters();
            midiInputReceiver.close();
            System.exit(0);
        });

        HBox whiteKeyPane = new HBox();
        whiteKeyPane.setPickOnBounds(false);
        HBox blackKeyPane = new HBox();
        blackKeyPane.setPickOnBounds(false);
        blackKeyPane.setPadding(new Insets(0, 0, 0, 13));
        blackKeyPane.setSpacing(4);
        whiteKeyPane.setSpacing(0.4);

        for (Button button : whiteKeys) {
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(WHITE_KEY_SIZE, 112);
            whiteKeyPane.getChildren().add(button);
        }
        for (Button button : blackKeys) {
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(BLACK_KEY_SIZE, 80);
            blackKeyPane.getChildren().add(button);
            button.setStyle("-fx-background-color: black");
        }
        blackKeyPane.getChildren().add(1, new FillerButton(BLACK_KEY_SIZE, 80));
        int i = 1;
        while (i < blackKeyPane.getChildren().size() - 1) {
            FillerButton fillerButton1 = new FillerButton(BLACK_KEY_SIZE, 80);
            FillerButton fillerButton2 = new FillerButton(BLACK_KEY_SIZE, 80);
            blackKeyPane.getChildren().add(i + 3, fillerButton1);
            blackKeyPane.getChildren().add(i + 7, fillerButton2);
            i = i + 7;
        }

        GridPane keyPane = new GridPane();
        keyPane.add(whiteKeyPane, 0, 0, 2, 1);
        keyPane.add(blackKeyPane, 0, 0, 2, 1);
        root.setCenter(keyPane);

        Scene scene = new Scene(root, SCREEN_WIDTH, 112);
        freePlay.setFullScreen(false);
        freePlay.setResizable(false);
        freePlay.setTitle("Free Play");
        freePlay.setScene(scene);
        freePlay.show();
    }

    private void keyPressedReleased(final int key) {
        Button button = keyBoard[key];
        if (button.getStyle().contains("blue")
                || button.getStyle().contains("red")) {
            if (whiteKeys.contains(button)) {
                button.setStyle(BUTTON_ORIGINAL_STYLE);
            } else if (blackKeys.contains(button)) {
                button.setStyle("-fx-background-color: black");
            }
        } else {
            if (whiteKeys.contains(button)) {
                button.setStyle("-fx-background-color: blue");
            } else if (blackKeys.contains(button)) {
                button.setStyle("-fx-background-color: red");
            }
        }
    }

    public final class MidiInputReceiver implements Receiver {

        /**
         * name of the receiver.
         */
        private final String name;

        /**
         * Construct a receiver with the String argument as name.
         * @param receiverName name of receiver
         */
        public MidiInputReceiver(final String receiverName) {
            this.name = receiverName;
        }

        /**
         * @param msg the MIDI message to send
         * @param timeStamp the time-stamp for the message, in microseconds
         */
        public void send(final MidiMessage msg, final long timeStamp) {
            byte[] aMsg = msg.getMessage();
            if ((lastKeyPressedIndex == 127 && aMsg[2] == 0)
                    || aMsg[2] == 127) {
                lastKeyPressedIndex = aMsg[2];
                return;
            }
            System.out.println(
                    "Message: " + aMsg[0] + ", " + aMsg[1] + ", " + aMsg[2]
            );
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
    }
}
