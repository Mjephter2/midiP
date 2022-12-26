package sample.views;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.models.*;
import sample.models.chords.*;
import sample.models.exceptions.InvalidNoteException;

import java.util.ArrayList;
import java.util.LinkedList;

public class LearnWindow extends Application {
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
    private static final ChoiceBox<String> keyBox = new ChoiceBox<>(FXCollections.observableArrayList(Utilities.NOTE_QUALITIES));
    private final GridPane bottom = new GridPane();
    private final ToggleGroup selectChordOrScale = new ToggleGroup();
    private final ToggleGroup scaleType = new ToggleGroup();
    private final ToggleGroup chordType = new ToggleGroup();
    private final HBox white_keyPane = new HBox();
    private final HBox black_keyPane = new HBox();
    private final GridPane keyPane = new GridPane();
    private final Button resetButton = new Button("RESET");

    private final String whiteKeysPressedCss = "-fx-background-color: linear-gradient(#fff 0%,#b9b9b9 100%);"
            + " -fx-background-radius: 0 0 4 4;"
            + " -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";
    private final String whiteKeysReleasedCss = "-fx-background-color: linear-gradient(to bottom,#eee 0%,#fff 100%);"
            + " -fx-background-radius: 0 0 5 5;"
            + " -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );";

    private final String blackKeysReleasedCss = "-fx-background-color: linear-gradient(to bottom left, #111 0%, #555 100%);"
            + " -fx-border-radius:0 0 3 3;"
            + "  -fx-border-color: #000;";
    private final String blackKeysPressedCSs = "-fx-background-color: linear-gradient(to top right, #111 0%, #555 100%);"
            + " -fx-padding: 0 0 0 -1;" +
            "  -fx-border-insets: 0 0 0 -1;" +
            "  -fx-background-insets: 0 0 0 -1;" +
            "  -fx-border-style: solid inside;" +
            "  -fx-border-width: 1;" +
            " -fx-border-radius:0 0 3 3;"
            + "  -fx-border-color: #000;";

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
        keyBox.setValue("Ab");

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
        selectChord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chordType.getToggles().forEach(toggle -> {
                    Node node = (Node) toggle;
                    node.setDisable(false);
                });
                scaleType.getToggles().forEach(toggle -> {
                    Node node = (Node) toggle;
                    node.setDisable(true);
                });
            }
        });
        selectScale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scaleType.getToggles().forEach(toggle -> {
                    Node node = (Node) toggle;
                    node.setDisable(false);
                });
                chordType.getToggles().forEach(toggle -> {
                    Node node = (Node) toggle;
                    node.setDisable(true);
                });
            }
        });

        bottom.add(new FillerButton(60,10), 7 , 0);
        bottom.add(new FillerButton(60,10), 8 , 0);
        bottom.add(new FillerButton(60,10), 9 , 0);
        bottom.add(resetButton,10,0);
    }

    private void drawKeyboard() {
        int start = Utilities.NOTE_NAMES.indexOf("C3");
        ArrayList<Integer> blackIndex = new ArrayList<>();
        blackIndex.add(1);
        blackIndex.add(4);
        blackIndex.add(6);
        blackIndex.add(9);
        blackIndex.add(11);
        for(int i = start; i < start + NUMBER_OF_KEYS; i++){
            if(blackIndex.contains(i % 12)){
                blackKeys.add(new Button(Utilities.NOTE_NAMES.get(i)));
            }else{
                whiteKeys.add(new Button(Utilities.NOTE_NAMES.get(i)));
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
        for(Button button: whiteKeys){
            button.setOnMousePressed(event -> {
                button.setStyle(whiteKeysPressedCss);
                button.setPrefSize(40,150);
            });

            button.setOnMouseReleased(event -> {
                button.setStyle(whiteKeysReleasedCss);
                button.setPrefSize(40,140);
            });
        }

        for(Button button: blackKeys){
            button.setOnMousePressed(event -> {
                button.setStyle(blackKeysPressedCSs);
                button.setPrefSize(30,82);
            });

            button.setOnMouseReleased(event -> {
                button.setStyle(blackKeysReleasedCss);
                button.setPrefSize(30,80);
            });
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
        resetButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
        });
    }

    private void addActionsToSelectionButtons() {
        majorTriadButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
            Note root = null;
            try {
                root = new Note("C3");
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            while(!root.noteQuality().equals(keyBox.getValue())){
                try {
                    root = root.sharp(1);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
            }
            Chord chord = null;
            try {
                chord = new Chord(ChordType.MAJOR_TRIAD, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for (Note chordNote : chordNotes) {
                chordNotesNames.add(chordNote.getName());
            }
            System.out.println(chordNotesNames);
            for (Button button: keyBoard) {
                if(chordNotesNames.contains(button.getTooltip().getText())){
                    colorButton(button);
                }
            }
        });

        minorTriadButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
            Note root = null;
            try {
                root = new Note("C3");
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            while(!root.noteQuality().equals(keyBox.getValue())){
                try {
                    root = root.sharp(1);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
            }
            Chord chord = null;
            try {
                chord = new Chord(ChordType.MINOR_TRIAD, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for (Note chordNote : chordNotes) {
                chordNotesNames.add(chordNote.getName());
            }
            System.out.println(chordNotesNames);
            for(Button button: keyBoard){
                if(chordNotesNames.contains(button.getTooltip().getText())){
                    colorButton(button);
                }
            }
        });

        major7thButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
            Note root = null;
            try {
                root = new Note("C3");
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            while(!root.noteQuality().equals(keyBox.getValue())){
                try {
                    root = root.sharp(1);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
            }
            Chord chord = null;
            try {
                chord = new Chord(ChordType.MAJOR_7TH, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for (Note chordNote : chordNotes) {
                chordNotesNames.add(chordNote.getName());
            }
            System.out.println(chordNotesNames);
            for (Button button: keyBoard) {
                if(chordNotesNames.contains(button.getTooltip().getText())){
                    colorButton(button);
                }
            }
        });

        minor7thButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
            Note root = null;
            try {
                root = new Note("C3");
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            while(!root.noteQuality().equals(keyBox.getValue())){
                try {
                    root = root.sharp(1);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
            }
            Chord chord = null;
            try {
                chord = new Chord(ChordType.MINOR_7TH, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for (Note chordNote : chordNotes) {
                chordNotesNames.add(chordNote.getName());
            }
            System.out.println(chordNotesNames);
            for (Button button: keyBoard) {
                if(chordNotesNames.contains(button.getTooltip().getText())){
                    colorButton(button);
                }
            }
        });

        major9thButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
            Note root = null;
            try {
                root = new Note("C3");
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            while(!root.noteQuality().equals(keyBox.getValue())){
                try {
                    root = root.sharp(1);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
            }
            Chord chord = null;
            try {
                chord = new Chord(ChordType.MAJOR_9TH, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for (Note chordNote : chordNotes) {
                chordNotesNames.add(chordNote.getName());
            }
            System.out.println(chordNotesNames);
            for(Button button: keyBoard){
                if(chordNotesNames.contains(button.getTooltip().getText())){
                    colorButton(button);
                }
            }
        });

        minor9thButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
            Note root = null;
            try {
                root = new Note("C3");
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            while(!root.noteQuality().equals(keyBox.getValue())){
                try {
                    root = root.sharp(1);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
            }
            Chord chord = null;
            try {
                chord = new Chord(ChordType.MINOR_9TH, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for (Note chordNote : chordNotes) {
                chordNotesNames.add(chordNote.getName());
            }
            System.out.println(chordNotesNames);
            for (Button button: keyBoard) {
                if(chordNotesNames.contains(button.getTooltip().getText())){
                    colorButton(button);
                }
            }
        });

        dominant7thButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
            Note root = null;
            try {
                root = new Note("C3");
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            while(!root.noteQuality().equals(keyBox.getValue())){
                try {
                    root = root.sharp(1);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
            }
            Chord chord = null;
            try {
                chord = new Chord(ChordType.DOMINANT_7TH, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for (Note chordNote : chordNotes) {
                chordNotesNames.add(chordNote.getName());
            }
            System.out.println(chordNotesNames);
            for (Button button: keyBoard) {
                if(chordNotesNames.contains(button.getTooltip().getText())) {
                    colorButton(button);
                }
            }
        });

        dominant9thButton.setOnMouseClicked(mouseEvent -> {
            resetButtons();
            Note root = null;
            try {
                root = new Note("C3");
            } catch (InvalidNoteException e1) {
                e1.printStackTrace();
            }
            while(!root.noteQuality().equals(keyBox.getValue())) {
                try {
                    root = root.sharp(1);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
            }
            Chord chord = null;
            try {
                chord = new Chord(ChordType.DOMINANT_9TH, root);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for (Note chordNote : chordNotes) {
                chordNotesNames.add(chordNote.getName());
            }
            System.out.println(chordNotesNames);
            for (Button button: keyBoard) {
                if (chordNotesNames.contains(button.getTooltip().getText())) {
                    colorButton(button);
                }
            }
        });

        majorScaleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resetButtons();
                Note root = null;
                try {
                    root = new Note("C3");
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                while(!root.noteQuality().equals(keyBox.getValue())){
                    try {
                        root = root.sharp(1);
                    } catch (InvalidNoteException e) {
                        e.printStackTrace();
                    }
                }
                Button start = null;
                for(int i = 0; i < keyBoard.size(); i++){
                    if(keyBoard.get(i).getTooltip().getText().equals(root.getName())){
                        start = keyBoard.get(i);
                        break;
                    }
                }
                if(start != null){
                    int index = keyBoard.indexOf(start);
                    colorButton(keyBoard.get(index));
                    colorButton(keyBoard.get(index + 2));
                    colorButton(keyBoard.get(index + 4));
                    colorButton(keyBoard.get(index + 5));
                    colorButton(keyBoard.get(index + 7));
                    colorButton(keyBoard.get(index + 9));
                    colorButton(keyBoard.get(index + 11));
                    colorButton(keyBoard.get(index + 12));
                }
            }
        });

        minorScaleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resetButtons();
                Note root = null;
                try {
                    root = new Note("C3");
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                while(!root.noteQuality().equals(keyBox.getValue())){
                    try {
                        root = root.sharp(1);
                    } catch (InvalidNoteException e) {
                        e.printStackTrace();
                    }
                }
                Button start = null;
                for(int i = 0; i < keyBoard.size(); i++){
                    if(keyBoard.get(i).getTooltip().getText().equals(root.getName())){
                        start = keyBoard.get(i);
                        break;
                    }
                }
                if(start != null){
                    int index = keyBoard.indexOf(start);
                    colorButton(keyBoard.get(index));
                    colorButton(keyBoard.get(index + 2));
                    colorButton(keyBoard.get(index + 3));
                    colorButton(keyBoard.get(index + 5));
                    colorButton(keyBoard.get(index + 7));
                    colorButton(keyBoard.get(index + 8));
                    colorButton(keyBoard.get(index + 10));
                    colorButton(keyBoard.get(index + 12));
                }
            }
        });
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
    public void start(Stage learn) {
        draw();
//        learn.setOnCloseRequest(windowEvent -> {
//            Main mainWindow = new Main();
//            Stage mainStage = new Stage();
//            try {
//                mainWindow.start(mainStage);
//                learn.close();
//                mainStage.show();
//            } catch (Exception ex) {
//                System.out.println("Error opening Main Window!!!");
//                ex.printStackTrace();
//            }
//        });
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
    }

    private void resetButtons(){
        resetBlackKeys();
        resetWhiteKeys();
    }

    private void resetWhiteKeys() {
        for(Button button: whiteKeys){
            button.setStyle(whiteKeysReleasedCss);
        }
    }

    private void resetBlackKeys() {
        for(Button button: blackKeys) {
            button.setStyle(blackKeysReleasedCss);
        }
    }

}
