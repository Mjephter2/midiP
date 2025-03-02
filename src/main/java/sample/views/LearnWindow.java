package sample.views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.text.Font;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static sample.models.NotesNamingMode.SHARP_MODE;
import static sample.models.Utilities.NOTE_QUALITIES_FLAT;
import static sample.models.Utilities.NOTE_QUALITIES_SHARP;
import static sample.views.Styles.whiteKeysReleasedCss;
import static sample.views.Styles.whiteKeysPressedCss;
import static sample.views.Styles.blackKeysReleasedCss;
import static sample.views.Styles.blackKeysPressedCSs;

public class LearnWindow extends Application {
    private static final Logger logger = Logger.getLogger(LearnWindow.class.getName());
    private static final int NUMBER_OF_KEYS = 36;
    private static final List<String> keyColors = List.of("green", "blue", "red", "orange", "purple", "pink", "brown", "gray", "black");

    //Chord Buttons
    private final ToggleButton majorTriadButton = new ToggleButton("Major Triad");
    private final ToggleButton minorTriadButton = new ToggleButton("Minor Triad");
    private final ToggleButton major7thButton = new ToggleButton("Major 7th");
    private final ToggleButton minor7thButton = new ToggleButton(("Minor 7th"));
    private final ToggleButton major9thButton = new ToggleButton("Major 9th");
    private final ToggleButton minor9thButton = new ToggleButton("Minor 9th");
    private final ToggleButton major11thButton = new ToggleButton("Major 11th");
    private final ToggleButton minor11thButton = new ToggleButton("Minor 11th");
    private final ToggleButton major13thButton = new ToggleButton("Major 13th");
    private final ToggleButton minor13thButton = new ToggleButton("Minor 13th");
    private final ToggleButton dominant7thButton = new ToggleButton("Dominant 7th");
    private final ToggleButton dominant9thButton = new ToggleButton("Dominant 9th");
    private final ToggleButton dominant11thButton = new ToggleButton("Dominant 11th");
    private final ToggleButton dominant13thButton = new ToggleButton("Dominant 13th");
    private final ToggleButton major6thButton = new ToggleButton("Major 6th");
    private final ToggleButton minor6thButton = new ToggleButton("Minor 6th");

    //Scale Buttons
    private final ToggleButton majorScaleButton = new ToggleButton("Major Scale");
    private final ToggleButton minorScaleButton = new ToggleButton("Minor Scale");
    private final ToggleButton chineseScaleButton = new ToggleButton("Chinese Scale");
    private final ToggleButton majorPentatonicScaleButton = new ToggleButton("Major Pentatonic Scale");
    private final ToggleButton minorPentatonicScaleButton = new ToggleButton("Minor Pentatonic Scale");
    private final ToggleButton wholeToneScaleButton = new ToggleButton("Whole Tone Scale");
    private final ToggleButton suspended2ndButton = new ToggleButton("Suspended 2nd");
    private final ToggleButton suspended4thButton = new ToggleButton("Suspended 4th");

    // Selection Buttons / Choice Boxes
    private final RadioButton selectChord = new RadioButton("CHORD");
    private final RadioButton selectScale = new RadioButton("SCALE");
    private static final ChoiceBox<String> keyBox = new ChoiceBox<>(FXCollections.observableArrayList(
            Note.notesNamingMode == FLAT_MODE ? NOTE_QUALITIES_FLAT : NOTE_QUALITIES_SHARP));

    private static final Map<String, Integer> inversionNameToNumber = Stream.of(new Object[][] {
            {"Root", 0},
            {"First", 1},
            {"Second", 2},
            {"Third", 3},
            {"Fourth", 4}
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
    private static final ChoiceBox<String> inversionBox = new ChoiceBox<>(
            FXCollections.observableArrayList(
                    inversionNameToNumber.keySet().stream()
                            .sorted(Comparator.comparingInt(inversionNameToNumber::get)).collect(Collectors.toList()))
    );

    private final ToggleGroup selectChordOrScale = new ToggleGroup();
    private final ToggleGroup scaleType = new ToggleGroup();
    private final ToggleGroup chordType = new ToggleGroup();

    //Musical Keys Representation
    private final LinkedList<Button> keyBoard = new LinkedList<>();   //LinkedList containing the piano keys
    private final LinkedList<Button> whiteKeys = new LinkedList<>();
    private final LinkedList<Button> blackKeys = new LinkedList<>();

    // Layouts
    private final GridPane bottom = new GridPane();
    private final HBox white_keyPane = new HBox();
    private final HBox black_keyPane = new HBox();
    private final GridPane keyPane = new GridPane();

    private final CommonMenu menu = new CommonMenu(false);

    private void drawSelectionButtons() {
        bottom.setStyle("-fx-background-color: darkgray;");
        bottom.setPadding(new Insets(10,0,10,0));
        bottom.setHgap(15);
        bottom.setVgap(15);

        selectChord.setToggleGroup(selectChordOrScale);
        selectScale.setToggleGroup(selectChordOrScale);
        selectChordOrScale.selectToggle(selectChord);

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
            inversionBox.setDisable(false);
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
            inversionBox.setDisable(true);
        });

        bottom.add(selectChord, 1,0);
        bottom.add(selectScale,1,1);
        bottom.add(new Label("Key / Root"),0,0, 1, 2);
        bottom.add(keyBox,0,1);
        bottom.add(new Label("Inversion"),0,2, 1, 2);
        bottom.add(inversionBox, 0, 3);

        keyBox.setValue(keyBox.getItems().get(0));
        inversionBox.setValue(inversionBox.getItems().get(0));

        majorScaleButton.setToggleGroup(scaleType);
        minorScaleButton.setToggleGroup(scaleType);
        chineseScaleButton.setToggleGroup(scaleType);
        majorPentatonicScaleButton.setToggleGroup(scaleType);
        minorPentatonicScaleButton.setToggleGroup(scaleType);
        wholeToneScaleButton.setToggleGroup(scaleType);
        bottom.add(majorScaleButton,2,0);
        bottom.add(minorScaleButton,2,1);
        bottom.add(chineseScaleButton,2,2);
        bottom.add(majorPentatonicScaleButton,2,3);
        bottom.add(minorPentatonicScaleButton,2,4);
        bottom.add(wholeToneScaleButton,2,5);

        majorTriadButton.setToggleGroup(chordType);
        minorTriadButton.setToggleGroup(chordType);
        major7thButton.setToggleGroup(chordType);
        minor7thButton.setToggleGroup(chordType);
        major9thButton.setToggleGroup(chordType);
        minor9thButton.setToggleGroup(chordType);
        major13thButton.setToggleGroup(chordType);
        minor13thButton.setToggleGroup(chordType);
        dominant7thButton.setToggleGroup(chordType);
        dominant9thButton.setToggleGroup(chordType);
        dominant11thButton.setToggleGroup(chordType);
        dominant13thButton.setToggleGroup(chordType);
        major6thButton.setToggleGroup(chordType);
        minor6thButton.setToggleGroup(chordType);
        major11thButton.setToggleGroup(chordType);
        minor11thButton.setToggleGroup(chordType);
        suspended2ndButton.setToggleGroup(chordType);
        suspended4thButton.setToggleGroup(chordType);
        bottom.add(majorTriadButton,3,0);
        bottom.add(minorTriadButton,3,1);
        bottom.add(suspended2ndButton,3,2);
        bottom.add(suspended4thButton,3,3);
        bottom.add(major6thButton,3,4);
        bottom.add(minor6thButton,3,5);
        bottom.add(major7thButton,4,0);
        bottom.add(minor7thButton,4,1);
        bottom.add(dominant7thButton,4,2);
        bottom.add(major9thButton,5,0);
        bottom.add(minor9thButton,5,1);
        bottom.add(dominant9thButton,5,2);
        bottom.add(dominant11thButton,5,3);
        bottom.add(dominant13thButton,5,4);
        bottom.add(major11thButton,6,0);
        bottom.add(minor11thButton,6,1);
        bottom.add(major13thButton,6,2);
        bottom.add(minor13thButton,6,3);

        bottom.add(new FillerButton(60,10), 7 , 0);
        bottom.add(new FillerButton(60,10), 8 , 0);
        bottom.add(new FillerButton(60,10), 9 , 0);
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

        for (Button currentButton : keyBoard) {
            if (whiteKeys.contains(currentButton)) {
                currentButton.setOnMousePressed(event -> {
                    currentButton.setStyle(whiteKeysPressedCss);
                    currentButton.setPrefSize(40, 150);
                });

                currentButton.setOnMouseReleased(event -> {
                    currentButton.setStyle(whiteKeysReleasedCss);
                    currentButton.setPrefSize(40, 140);
                });
            } else {
                currentButton.setOnMousePressed(event -> {
                    currentButton.setStyle(blackKeysPressedCSs);
                    currentButton.setPrefSize(30, 82);
                });

                currentButton.setOnMouseReleased(event -> {
                    currentButton.setStyle(blackKeysReleasedCss);
                    currentButton.setPrefSize(30, 80);
                });
            }
        }

        keyBoard.sort(Utilities.KEY_NOTE_COMPARATOR);
        black_keyPane.getChildren().add(2, new FillerButton(30,80));
        black_keyPane.getChildren().add(6, new FillerButton(30,80));
        black_keyPane.getChildren().add(9, new FillerButton(30,80));
        black_keyPane.getChildren().add(13, new FillerButton(30,80));
        black_keyPane.getChildren().add(16, new FillerButton(30,80));

        keyPane.setAlignment(Pos.BASELINE_CENTER);
        BorderPane.setAlignment(keyPane,Pos.CENTER);
        keyPane.setPadding(new Insets(0,0,10,0));
        keyPane.add(white_keyPane,0,0,2,1);
        keyPane.add(black_keyPane,0,0,2,1);
    }

    void showButtonNoteName(Button button) {
        String text = button.getTooltip().getText().substring(0, button.getTooltip().getText().length() -1);
        button.setTextFill(Color.WHITE);
        button.setText(text);
    }

    private void addActionsToSelectionButtons() {
        Map<ToggleButton, ChordType> buttonChordTypeMap = new HashMap<>(){{
            put(minorTriadButton, ChordType.MINOR_TRIAD);
            put(majorTriadButton, ChordType.MAJOR_TRIAD);
            put(suspended2ndButton, ChordType.SUSPENDED_2ND);
            put(suspended4thButton, ChordType.SUSPENDED_4TH);
            put(major6thButton, ChordType.MAJOR_6TH);
            put(minor6thButton, ChordType.MINOR_6TH);
            put(minor7thButton, ChordType.MINOR_7TH);
            put(major7thButton, ChordType.MAJOR_7TH);
            put(major9thButton, ChordType.MAJOR_9TH);
            put(minor9thButton, ChordType.MINOR_9TH);
            put(major11thButton, ChordType.MAJOR_11TH);
            put(minor11thButton, ChordType.MINOR_11TH);
            put(major13thButton, ChordType.MAJOR_13TH);
            put(minor13thButton, ChordType.MINOR_13TH);
            put(dominant7thButton, ChordType.DOMINANT_7TH);
            put(dominant9thButton, ChordType.DOMINANT_9TH);
            put(dominant11thButton, ChordType.DOMINANT_11TH);
            put(dominant13thButton, ChordType.DOMINANT_13TH);
        }};

        for (ToggleButton button : buttonChordTypeMap.keySet()) {
            button.setOnMouseClicked(mouseEvent -> {
                resetButtons();
                try {
                    // find the first key / button that matches the root note
                    Note root = new Note("C3");
                    while(!Objects.requireNonNull(root).noteQuality().equals(keyBox.getValue())){
                        root = root.sharp(1);
                    }
                    Chord chord = new Chord(buttonChordTypeMap.get(button), root);
                    Note[] chordNotes = inversionNameToNumber.get(inversionBox.getValue()) == 0 ?
                            chord.notes()
                            :
                            chord.invert(inversionNameToNumber.get(inversionBox.getValue()));
                    ArrayList<String> chordNotesNames = new ArrayList<>();
                    for (Note chordNote : chordNotes) {
                        chordNotesNames.add(chordNote.getName());
                    }

                    for(Button button1: keyBoard){
                        String buttonNoteQuality = button1.getTooltip().getText()
                                .substring(0, button1.getTooltip().getText().length() -1);
                        button1.setText("");
                        button1.setAlignment(Pos.BOTTOM_CENTER);
                        if(blackKeys.contains(button1))
                            button1.setFont(new Font("aerials", 8));
                        if(chordNotesNames.contains(button1.getTooltip().getText())) {
                            final int nodeIndex = chordNotesNames.indexOf(button1.getTooltip().getText());
                            colorButton(button1, root.noteQuality().equals(buttonNoteQuality), nodeIndex);
                            showButtonNoteName(button1);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        Map<ToggleButton, ScaleType> buttonScaleTypeMap = new HashMap<>(){{
            put(majorScaleButton, ScaleType.MAJOR_SCALE);
            put(minorScaleButton, ScaleType.MINOR_SCALE);
            put(chineseScaleButton, ScaleType.CHINESE_SCALE);
            put(majorPentatonicScaleButton, ScaleType.MAJOR_PENTATONIC);
            put(minorPentatonicScaleButton, ScaleType.MINOR_PENTATONIC);
            put(wholeToneScaleButton, ScaleType.WHOLE_TONE);
        }};

        for (ToggleButton button : buttonScaleTypeMap.keySet()) {
            button.setOnMouseClicked(mouseEvent -> {
                resetButtons();
                try {
                    Note root = new Note("C3");
                    while(!root.noteQuality().equals(keyBox.getValue())){
                            root = root.sharp(1);
                    }
                    Scale scale = new Scale(buttonScaleTypeMap.get(button), root);
                    Note[] scaleNotes = scale.notes();

                    ArrayList<String> scaleNotesNames = new ArrayList<>();
                    for (Note scaleNote : scaleNotes) {
                        scaleNotesNames.add(scaleNote.getName());
                    }
                    logger.info(scaleNotesNames.toString());
                    for(Button button1: keyBoard){
                        button1.setText("");
                        String buttonNoteQuality = button1.getTooltip().getText()
                                .substring(0, button1.getTooltip().getText().length() - 1);
                        button1.setAlignment(Pos.BOTTOM_CENTER);
                        if(blackKeys.contains(button1))
                            button1.setFont(new Font("aerials", 8));
                        if(scaleNotesNames.contains(button1.getTooltip().getText())){
                            colorButton(button1, root.noteQuality().equals(buttonNoteQuality), scaleNotesNames.indexOf(button1.getTooltip().getText()));
                            showButtonNoteName(button1);
                        }
                    }
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void colorButton(final Button button, final boolean isRoot, final int noteIndex){
        if (isRoot) {
            button.setStyle(button.getStyle() + "-fx-background-color: " + keyColors.get(0) + ";");
        } else {
            button.setStyle(button.getStyle() + "-fx-background-color: " + keyColors.get(noteIndex + 1) + ";");
        }
    }

    private void draw() {
        drawSelectionButtons();
        drawKeyboard();
        addActionsToSelectionButtons();
    }

    private void switchNoteNamingMode() {
        if (Note.notesNamingMode == FLAT_MODE) {
            keyBox.getItems().clear();
            keyBox.setItems(FXCollections.observableArrayList(NOTE_QUALITIES_FLAT));
            keyBox.setValue(NOTE_QUALITIES_SHARP.get(0));
        } else {
            keyBox.getItems().clear();
            keyBox.setItems(FXCollections.observableArrayList(NOTE_QUALITIES_SHARP));
            keyBox.setValue(NOTE_QUALITIES_SHARP.get(0));
        }
        for (Button button : keyBoard) {
            try {
                String existingTooltip = button.getTooltip().getText();
                button.setTooltip(new Tooltip(new Note(existingTooltip).getName()));
            } catch (InvalidNoteException e) {
                throw new RuntimeException(e);
            }
        }
        resetButtons();
    }

    private ContextMenu generateContextMenu(final Stage stage) {
        final ContextMenu contextMenu = new ContextMenu();

        final MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> System.exit(0));

        final Menu preferenceMenu = new Menu("Change Naming Mode");
        final MenuItem flatItem = new MenuItem(menu.flatModeItem.getText());
        flatItem.setOnAction(event -> {
            Note.notesNamingMode = FLAT_MODE;
            switchNoteNamingMode();
        });
        MenuItem sharpItem = new MenuItem(menu.sharpModeItem.getText());
        MenuItem homeButton = new MenuItem("Home");
        homeButton.setOnAction(event -> {
            Main mainWindow = new Main();
            Stage mainStage = new Stage();
            try {
                mainWindow.start(mainStage);
                stage.close();
                mainStage.show();
            } catch (Exception e) {
                logger.info("Error opening Main Window!!!");
                e.printStackTrace();
            }
        });
        sharpItem.setOnAction(event -> {
            Note.notesNamingMode = SHARP_MODE;
            switchNoteNamingMode();
        });
        preferenceMenu.getItems().add(flatItem);
        preferenceMenu.getItems().add(sharpItem);

        final MenuItem resetItem = new MenuItem("Reset");
        resetItem.setOnAction(event -> resetButtons());

        contextMenu.getItems().add(0, preferenceMenu);
        contextMenu.getItems().add(1, exitItem);
        contextMenu.getItems().add(2, homeButton);
        contextMenu.getItems().add(3, resetItem);

        return contextMenu;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage learnStage) {
        draw();
        learnStage.setOnCloseRequest(windowEvent -> {
            Stage newStage = new Stage();
            Main newWindow = new Main();

            try  {
                newWindow.start(newStage);
                learnStage.close();
                newStage.show();
            } catch (Exception e) {
                logger.info("Error opening Main Window!!!");
                e.printStackTrace();
            }
        }
        );

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #E6BF83");
        root.setPadding(new Insets(10,10,10,10));
        final ContextMenu contextMenu = generateContextMenu(learnStage);
        root.setOnContextMenuRequested(event -> contextMenu.show(bottom, event.getScreenX(), event.getScreenY()));
        root.setOnMousePressed(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
        });

        menu.flatModeItem.setOnAction(e -> {
            Note.notesNamingMode = FLAT_MODE;
            switchNoteNamingMode();
        });
        menu.sharpModeItem.setOnAction(e -> {
            Note.notesNamingMode = SHARP_MODE;
            switchNoteNamingMode();
        });

        root.setTop(menu);
        root.setBottom(bottom);
        GridPane.setHalignment(bottom, HPos.CENTER);
        root.setCenter(keyPane);

        Scene scene = new Scene(root,1000,450);
        learnStage.setFullScreen(false);
        learnStage.setResizable(false);
        learnStage.setTitle("Learn Chords and Scales");
        learnStage.setScene(scene);
        learnStage.show();
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
