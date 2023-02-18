package sample.views;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import sample.AudioPlayer;
import sample.models.FillerButton;
import sample.models.Utilities;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Transmitter;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.MidiMessage;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static sample.views.Styles.*;

/**
 * GUI for the full size view of a midi piano
 * allowing for free play without audio feedback.
 */
public final class FreePlayWindow extends Application {
    /**
     * Window configuration.
     */
    public FreePlayWindowConfig freePlayWindowConfig = FreePlayWindowConfig.defaultConfig();
    /**
     * Array of Button representing the keys on the piano.
     */
    private final Button[] keyBoard = new Button[Utilities.NUMBER_OF_KEYS_88];

    /**
     * List of white keys on the piano in order.
     */
    private final LinkedList<Button> whiteKeys = new LinkedList<>();

    /**
     * List of black keys on the piano in order.
     */
    private final LinkedList<Button> blackKeys = new LinkedList<>();

    /**
     * Default Button style to help
     * reset buttons to original look.
     */
    private static final String BUTTON_ORIGINAL_STYLE = new Button().getStyle();

    /**
     * Midi Receiver to capture keyboard / midi events.
     */
    private final MidiInputReceiver midiInputReceiver =
            new MidiInputReceiver("Receiver");

    /**
     * Index of the last key pressed
     * as captured from a physical midi device.
     */
    private int lastKeyPressedIndex = 0;

    private final Button return_home = new Button("Home");
    // This button will allow user to view all notes on the piano-button for help!
    private final ToggleButton show_notes = new ToggleButton("Show Notes");

    /**
     * Initializes the components of the UI.
     */
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


    /**
     * This function assigns the text-note name on the button that is pressed.
     * On white keys it's simple because they are big. On the black keys the text displays
     * vertically so that the user can view the whole note name. It is called via show_notes action
     * listener
     */
    private void Display_Notes_help()
    {
        for (Button button : whiteKeys)
        {
            String text = button.getTooltip().getText();
            text = text.substring(0, text.length() -1);
            button.setText(text);
            button.setAlignment(Pos.BOTTOM_CENTER);
        }

        for(Button button : blackKeys)
        {
            String text = button.getTooltip().getText();
            text = text.substring(0, text.length() -1);
            button.setFont(new Font("aerials", 8));
            button.setText(text);
            button.setTextFill(Color.WHITE);
            button.setAlignment(Pos.BOTTOM_LEFT);
        }
    }


    /**
     * Opens all the midi transmitters available in
     * the system.
     */
    /*private void openAllTransmitters() {
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
    }*/

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
                    System.out.println(device.getDeviceInfo() + " Was closed");
                }
            } catch (MidiUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main entry point.
     * @param args application parameters
     */
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage freePlay) {
        initialize();
        //openAllTransmitters();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #E6BF83");
        root.setPadding(new Insets(10,20,10,20));
        HBox whiteKeyPane = new HBox();
        whiteKeyPane.setPickOnBounds(false);
        HBox blackKeyPane = new HBox();
        blackKeyPane.setPickOnBounds(false);
        blackKeyPane.setPadding(new Insets(0, 0, 0, freePlayWindowConfig.getBlackKeyPaneLeftPadding()));
        blackKeyPane.setSpacing(freePlayWindowConfig.getBlackKeyPaneSpacing());
        whiteKeyPane.setSpacing(FreePlayWindowConfig.WHITE_KEY_PANE_SPACING);



        // Top pane that will contain show_notes ToggleButton, home button and text of note pressed
        BorderPane top_pane = new BorderPane();

        // Hbox inside which we put show_notes and home button
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().add(show_notes);
        buttons.getChildren().add(return_home);
        // Assign buttons Hbox on the right side of top_pane
        top_pane.setRight(buttons);
        top_pane.setStyle("-fx-background-color: #8499ad");
        top_pane.setPadding(new Insets(10,20,10,20));
        return_home.setVisible(true);
        show_notes.setVisible(true);


        for (Button button : whiteKeys) {
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(freePlayWindowConfig.getWhiteKeysPrefWidth(), freePlayWindowConfig.getWhiteKeysPrefHeight());
            button.setPadding(new Insets(0,0,0,0));
            button.setOnMousePressed(event -> {
                button.setStyle(whiteKeysPressedCss);
                button.setTranslateY(2);

            });

            button.setOnMouseReleased(event -> {
                button.setStyle(whiteKeysReleasedCss);
                button.setTranslateY(0);
            });

            whiteKeyPane.getChildren().add(button);
        }

        for (Button button : blackKeys) {
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight());
            button.setPadding(new Insets(0, freePlayWindowConfig.getBlackKeysPaddingRight(), 0, 0));
            button.setOnMousePressed(event -> {
                button.setStyle(blackKeysPressedCSs);
                button.setPrefSize(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight() + 2);
                // This line of code changes the text in top_pane to the value of the note pressed
            });

            button.setOnMouseReleased(event -> {
                button.setStyle(blackKeysReleasedCss);
                button.setPrefSize(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight());;
            });
            blackKeyPane.getChildren().add(button);
        }
        FillerButton filler = new FillerButton((int) freePlayWindowConfig.getBlackKeysPrefWidth(), (int) freePlayWindowConfig.getBlackKeysPrefHeight());
        filler.setPadding(new Insets(0, freePlayWindowConfig.getBlackKeysPaddingRight(), 0, 0));
        blackKeyPane.getChildren().add(1, filler);
        int i = 1;
        while (i < blackKeyPane.getChildren().size() - 1) {
            FillerButton fillerButton1 = new FillerButton(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight());
            FillerButton fillerButton2 = new FillerButton(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight());
            fillerButton1.setPadding(new Insets(0, freePlayWindowConfig.getBlackKeysPaddingRight(), 0, 0));
            fillerButton2.setPadding(new Insets(0, freePlayWindowConfig.getBlackKeysPaddingRight(), 0, 0));
            blackKeyPane.getChildren().add(i + 3, fillerButton1);
            blackKeyPane.getChildren().add(i + 7, fillerButton2);
            i = i + 7;
        }
        for (Button button: blackKeys) {
            button.setStyle(blackKeysReleasedCss);
        }
        for (Button button: whiteKeys) {
            button.setStyle(whiteKeysReleasedCss);
        }

        GridPane keyPane = new GridPane();
        keyPane.add(whiteKeyPane, 0, 0, 2, 1);
        keyPane.add(blackKeyPane, 0, 0, 2, 1);
        root.setCenter(keyPane);
        root.setTop(top_pane);

        Scene scene = new Scene(root, freePlayWindowConfig.getWindowWidth(), freePlayWindowConfig.getWindowHeight());
        freePlay.setFullScreen(false);
        freePlay.setResizable(false);
        freePlay.setTitle("Free Play");
        freePlay.setScene(scene);
        freePlay.show();

        // show_notes, home_button actionListener
        show_notes.setOnAction(e ->{
            Display_Notes_help();
        });
        return_home.setOnAction(e -> {
            Main mainWindow = new Main();
            try {
                mainWindow.start(new Stage());
                freePlay.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        freePlay.setOnCloseRequest(windowEvent -> {
            Main mainWindow = new Main();
            try {
                mainWindow.start(new Stage());
                freePlay.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            closeAllTransmitters();
            midiInputReceiver.close();
        });


        show_notes.setOnMouseClicked( e ->{
            if(show_notes.isSelected())
                Display_Notes_help();
            else {
                for (Button button : whiteKeys)
                    button.setText("");

                for (Button button : blackKeys)
                    button.setText("");
            }
        });
    }

    /**
     * Event handler for midi keyboard events.
     * Assigns the appropriate style to the keyboard keys involved
     * @param key
     */
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

    /**
     * Class to handle midi events.
     */
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
