package sample.views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.AudioPlayer;
import sample.models.Utilities;
import sample.models.FillerButton;
import sample.models.Note;
import sample.models.chords.Chord;
import sample.models.chords.ChordType;
import sample.models.exceptions.InvalidNoteException;
import sample.models.scales.Scale;
import sample.models.scales.ScaleType;
import static sample.models.NotesNamingMode.FLAT_MODE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static sample.views.Styles.whiteKeysReleasedCss;
import static sample.views.Styles.whiteKeysPressedCss;
import static sample.views.Styles.blackKeysReleasedCss;
import static sample.views.Styles.blackKeysPressedCSs;

public class LearnWindow extends Application {
    private static final Logger logger = Logger.getLogger(LearnWindow.class.getName());
    private static final int NUMBER_OF_KEYS = 27;
    private final LinkedList<Button> keyBoard = new LinkedList<>();   //LinkedList containing the piano keys
    private final LinkedList<Button> whiteKeys = new LinkedList<>();
    private final LinkedList<Button> blackKeys = new LinkedList<>();
    private final ToggleButton majorTriadButton = new ToggleButton("Major Triad");
    private final ToggleButton minorTriadButton = new ToggleButton("Minor Triad");
    private final ToggleButton major7thButton = new ToggleButton("Major 7th");
    private final ToggleButton minor7thButton = new ToggleButton(("Minor 7th"));
    private final ToggleButton major9thButton = new ToggleButton("Major 9th");
    private final ToggleButton minor9thButton = new ToggleButton("Minor 9th");
    private final ToggleButton dominant7thButton = new ToggleButton("Dominant 7th");
    private final ToggleButton dominant9thButton = new ToggleButton("Dominant 9th");
    private final ToggleButton majorScaleButton = new ToggleButton("Major Scale");
    private final ToggleButton minorScaleButton = new ToggleButton("Minor Scale");
    private final RadioButton selectChord = new RadioButton("CHORD");
    private final RadioButton selectScale = new RadioButton("SCALE");
    private static final ChoiceBox<String> keyBox = new ChoiceBox<>(FXCollections.observableArrayList(
            Note.notesNamingMode == FLAT_MODE ? Utilities.NOTE_QUALITIES_FLAT : Utilities.NOTE_QUALITIES_SHARP));
    private final GridPane bottom = new GridPane();
    private final ToggleGroup selectChordOrScale = new ToggleGroup();
    private final ToggleGroup scaleType = new ToggleGroup();
    private final ToggleGroup chordType = new ToggleGroup();
    private final HBox white_keyPane = new HBox();
    private final HBox black_keyPane = new HBox();
    private final GridPane keyPane = new GridPane();
    private final Button resetButton = new Button("RESET");

    private final Button homeButton = new Button("Home");
    private AudioPlayer[] audioPlayers;

    private void drawSelectionButtons() {
        //bottom.setGridLinesVisible(true);
        bottom.setStyle("-fx-background-color: darkgray;");
        bottom.setPadding(new Insets(10,0,10,0));
        bottom.setHgap(10);
        bottom.setVgap(10);

        selectChord.setToggleGroup(selectChordOrScale);
        selectScale.setToggleGroup(selectChordOrScale);
        selectChordOrScale.selectToggle(selectChord);

        bottom.add(selectChord, 1,0);
        bottom.add(selectScale,1,1);

        bottom.add(keyBox,0,0,1,2);
        keyBox.setValue(keyBox.getItems().get(0));

        majorScaleButton.setToggleGroup(scaleType);
        minorScaleButton.setToggleGroup(scaleType);
        bottom.add(majorScaleButton,2,0);
        bottom.add(minorScaleButton,2,1);

        majorTriadButton.setToggleGroup(chordType);
        minorTriadButton.setToggleGroup(chordType);
        major7thButton.setToggleGroup(chordType);
        minor7thButton.setToggleGroup(chordType);
        major9thButton.setToggleGroup(chordType);
        minor9thButton.setToggleGroup(chordType);
        dominant7thButton.setToggleGroup(chordType);
        dominant9thButton.setToggleGroup(chordType);
        bottom.add(majorTriadButton,3,0);
        bottom.add(minorTriadButton,3,1);
        bottom.add(major7thButton,4,0);
        bottom.add(minor7thButton,4,1);
        bottom.add(major9thButton,5,0);
        bottom.add(minor9thButton,5,1);
        bottom.add(dominant7thButton,6,0);
        bottom.add(dominant9thButton,6,1);

        scaleType.getToggles().forEach(toggle -> {
            Node node = (Node) toggle;
            node.setDisable(true);
        });
        selectChord.setOnAction(actionEvent -> {
            chordType.getToggles().forEach(toggle -> {
                Node node = (Node) toggle;
                node.setDisable(false);
            });
            scaleType.getToggles().forEach(toggle -> {
                Node node = (Node) toggle;
                node.setDisable(true);
            });
        });
        selectScale.setOnAction(actionEvent -> {
            scaleType.getToggles().forEach(toggle -> {
                Node node = (Node) toggle;
                node.setDisable(false);
            });
            chordType.getToggles().forEach(toggle -> {
                Node node = (Node) toggle;
                node.setDisable(true);
            });
        });

        bottom.add(new FillerButton(60,10), 7 , 0);
        bottom.add(new FillerButton(60,10), 8 , 0);
        bottom.add(new FillerButton(60,10), 9 , 0);
        bottom.add(resetButton,10,0);
        bottom.add(homeButton, 10, 1);
    }

    private void drawKeyboard() {
        int start = Utilities.NOTE_NAMES_FLAT.indexOf("C3");
        ArrayList<Integer> blackIndex = new ArrayList<>();
        blackIndex.add(1);
        blackIndex.add(4);
        blackIndex.add(6);
        blackIndex.add(9);
        blackIndex.add(11);
        for(int i = start; i < start + NUMBER_OF_KEYS; i++){
            String noteName = Note.notesNamingMode == FLAT_MODE ? Utilities.NOTE_NAMES_FLAT.get(i) : Utilities.NOTE_NAMES_SHARP.get(i);
            if(blackIndex.contains(i % 12)){
                blackKeys.add(new Button(noteName));
            }else{
                whiteKeys.add(new Button(noteName));
            }
        }
        white_keyPane.setPickOnBounds(false);
        black_keyPane.setPickOnBounds(false);
        white_keyPane.setSpacing(1);
        black_keyPane.setSpacing(11);
        black_keyPane.setPadding(new Insets(0,0,0,25));
        for(Button button: whiteKeys){
            button.setPrefSize(40,140);
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setStyle(whiteKeysReleasedCss);
            white_keyPane.getChildren().add(button);
            keyBoard.add(button);
        }

        for(Button button: blackKeys){
            button.setPrefSize(30,80);
            button.setStyle(blackKeysReleasedCss);
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            black_keyPane.getChildren().add(button);
            keyBoard.add(button);
        }

        audioPlayers = Utilities.createAudioPlayers(keyBoard.stream().map(button -> {
            try {
                return new Note(button.getTooltip().getText());
            } catch (InvalidNoteException e) {
                throw new RuntimeException(e);
            }
        }).toArray(Note[]::new));

        for(int i = 0; i < keyBoard.size(); i++) {
            Button currentButton = keyBoard.get(i);
            int finalI = i;

            if(whiteKeys.contains(currentButton)) {
                currentButton.setOnMousePressed(event -> {
                    audioPlayers[finalI].start();
                    currentButton.setStyle(whiteKeysPressedCss);
                    currentButton.setPrefSize(40, 150);
                });

                currentButton.setOnMouseReleased(event -> {
                    audioPlayers[finalI].stopPlaying();
                    currentButton.setStyle(whiteKeysReleasedCss);
                    currentButton.setPrefSize(40, 140);
                    try {
                        audioPlayers[finalI] = new AudioPlayer(new Note(currentButton.getTooltip().getText()));
                    } catch (InvalidNoteException e) {
                        throw new RuntimeException(e);
                    }
                });
            } else {
                currentButton.setOnMousePressed(event -> {
                    currentButton.setStyle(blackKeysPressedCSs);
                    currentButton.setPrefSize(30,82);
                    audioPlayers[finalI].start();
                });

                currentButton.setOnMouseReleased(event -> {
                    currentButton.setStyle(blackKeysReleasedCss);
                    currentButton.setPrefSize(30,80);
                    audioPlayers[finalI].stopPlaying();
                    try {
                        audioPlayers[finalI] = new AudioPlayer(new Note(currentButton.getTooltip().getText()));
                    } catch (InvalidNoteException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }

        keyBoard.sort(Utilities.KEY_NOTE_COMPARATOR);
        black_keyPane.getChildren().add(2, new FillerButton(30,80));
        black_keyPane.getChildren().add(6, new FillerButton(30,80));
        black_keyPane.getChildren().add(9, new FillerButton(30,80));
        black_keyPane.getChildren().add(13, new FillerButton(30,80));

        keyPane.setAlignment(Pos.BASELINE_CENTER);
        BorderPane.setAlignment(keyPane,Pos.CENTER);
        keyPane.setPadding(new Insets(0,0,10,0));
        keyPane.add(white_keyPane,0,0,2,1);
        keyPane.add(black_keyPane,0,0,2,1);
        resetButton.setOnMouseClicked(mouseEvent -> resetButtons());
    }

    void showButtonNoteName(Button button) {
        String text = button.getTooltip().getText().substring(0, button.getTooltip().getText().length() -1);
        button.setText(text);
    }

    private void addActionsToSelectionButtons() {
        Map<ToggleButton, ChordType> buttonChordTypeMap = new HashMap<>();
        buttonChordTypeMap.put(minorTriadButton, ChordType.MINOR_TRIAD);
        buttonChordTypeMap.put(majorTriadButton, ChordType.MAJOR_TRIAD);
        buttonChordTypeMap.put(minor7thButton, ChordType.MINOR_7TH);
        buttonChordTypeMap.put(major7thButton, ChordType.MAJOR_7TH);
        buttonChordTypeMap.put(major9thButton, ChordType.MAJOR_9TH);
        buttonChordTypeMap.put(minor9thButton, ChordType.MINOR_9TH);
        buttonChordTypeMap.put(dominant7thButton, ChordType.DOMINANT_7TH);
        buttonChordTypeMap.put(dominant9thButton, ChordType.DOMINANT_9TH);

        for (ToggleButton button : buttonChordTypeMap.keySet()) {
            button.setOnMouseClicked(mouseEvent -> {
                resetButtons();
                Note root = null;
                try {
                    root = new Note("C3");
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                while(!Objects.requireNonNull(root).noteQuality().equals(keyBox.getValue())){
                    try {
                        root = root.sharp(1);
                    } catch (InvalidNoteException e) {
                        e.printStackTrace();
                    }
                }
                Chord chord = null;
                try {
                    chord = new Chord(buttonChordTypeMap.get(button), root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Note[] chordNotes = Objects.requireNonNull(chord).notes();
                ArrayList<String> chordNotesNames = new ArrayList<>();
                for (Note chordNote : chordNotes) {
                    chordNotesNames.add(chordNote.getName());
                }
                logger.info(chordNotesNames.toString());
                for(Button button1: keyBoard){
                    button1.setText("");
                    button1.setAlignment(Pos.BOTTOM_CENTER);
                    if(blackKeys.contains(button1))
                        button1.setFont(new Font("aerials", 8));
                    if(chordNotesNames.contains(button1.getTooltip().getText())){
                        colorButton(button1);
                        showButtonNoteName(button1);
                    }
                }
            });
        }

        Map<ToggleButton, ScaleType> buttonScaleTypeMap = new HashMap<>();
        buttonScaleTypeMap.put(majorScaleButton, ScaleType.MAJOR_SCALE);
        buttonScaleTypeMap.put(minorScaleButton, ScaleType.MINOR_SCALE);

        for (ToggleButton button : buttonScaleTypeMap.keySet()) {
            button.setOnMouseClicked(mouseEvent -> {
                resetButtons();
                Note root = null;
                try {
                    root = new Note("C3");
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                while(!Objects.requireNonNull(root).noteQuality().equals(keyBox.getValue())){
                    try {
                        root = root.sharp(1);
                    } catch (InvalidNoteException e) {
                        e.printStackTrace();
                    }
                }
                Scale scale = null;
                try {
                    scale = new Scale(buttonScaleTypeMap.get(button), root);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Note[] scaleNotes = Objects.requireNonNull(scale).notes();
                ArrayList<String> scaleNotesNames = new ArrayList<>();
                for (Note scaleNote : scaleNotes) {
                    scaleNotesNames.add(scaleNote.getName());
                }
                logger.info(scaleNotesNames.toString());
                for(Button button1: keyBoard){
                    button1.setText("");
                    button1.setAlignment(Pos.BOTTOM_CENTER);
                    if(blackKeys.contains(button1))
                        button1.setFont(new Font("aerials", 8));
                    if(scaleNotesNames.contains(button1.getTooltip().getText())){
                        colorButton(button1);
                        showButtonNoteName(button1);
                    }
                }
            });
        }
    }

    private void colorButton(Button button){
        button.setStyle(button.getStyle() + "-fx-background-color: #83AAE6");
    }

    /**
     * draws selection buttons
     * draws on-display keyboard
     * add actions to selection buttons
     */
    private void draw() {
        drawSelectionButtons();
        drawKeyboard();
        addActionsToSelectionButtons();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage learn) {
        draw();
        learn.setOnCloseRequest(windowEvent -> {
            Main mainWindow = new Main();
            Stage mainStage = new Stage();
            try {
                mainWindow.start(mainStage);
                learn.close();
                mainStage.show();
            } catch (Exception ex) {
                logger.info("Error opening Main Window!!!");
                ex.printStackTrace();
            }
        });

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #E6BF83");
        root.setPadding(new Insets(10,10,10,10));
        root.setBottom(bottom);
        GridPane.setHalignment(bottom, HPos.CENTER);
        root.setCenter(keyPane);

        Scene scene = new Scene(root,1000,255);
        learn.setFullScreen(false);
        learn.setResizable(false);
        learn.setTitle("Learn Chords and Scales");
        learn.setScene(scene);
        learn.show();

        // Home button action listener: Closes LearnWindow Stage and opens Main window Stage
        homeButton.setOnAction( e ->{
            Main mainWindow = new Main();
            try {
                mainWindow.start(new Stage());
                learn.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void resetButtons(){
        resetBlackKeys();
        resetWhiteKeys();
    }

    private void resetWhiteKeys() {
        for(Button button: whiteKeys){
            button.setText("");
            button.setStyle(whiteKeysReleasedCss);
        }
    }

    private void resetBlackKeys() {
        for(Button button: blackKeys) {
            button.setText("");
            button.setStyle(blackKeysReleasedCss);
        }
    }

}
