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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.DataClasses.*;
import sample.DataClasses.chords.Chord;
import sample.DataClasses.chords.Dominant7th;
import sample.DataClasses.chords.Dominant9th;
import sample.DataClasses.chords.Major7th;
import sample.DataClasses.chords.Major9th;
import sample.DataClasses.chords.MajorTriad;
import sample.DataClasses.chords.Minor7th;
import sample.DataClasses.chords.Minor9th;
import sample.DataClasses.chords.MinorTriad;
import sample.DataClasses.exceptions.InvalidNoteException;

import java.util.ArrayList;
import java.util.LinkedList;

public class LearnWindow extends Application {
    private static final int NUMBER_OF_KEYS = 27;
    private LinkedList<Button> keyBoard = new LinkedList<>();   //LinkedList containing the piano keys
    private LinkedList<Button> whiteKeys = new LinkedList<>();
    private LinkedList<Button> blackKeys = new LinkedList<>();
    private ToggleButton majorTriadButton = new ToggleButton("Major Triad");
    private ToggleButton minorTriadButton = new ToggleButton("Minor Triad");
    private ToggleButton major7thButton = new ToggleButton("Major 7th");
    private ToggleButton minor7thButton = new ToggleButton(("Minor 7th"));
    private ToggleButton major9thButton = new ToggleButton("Major 9th");
    private ToggleButton minor9thButton = new ToggleButton("Minor 9th");
    private ToggleButton dominant7thButton = new ToggleButton("Dominant 7th");
    private ToggleButton dominant9thButton = new ToggleButton("Dominant 9th");
    private ToggleButton majorScaleButton = new ToggleButton("Major Scale");
    private ToggleButton minorScaleButton = new ToggleButton("Minor Scale");
    private RadioButton selectChord = new RadioButton("CHORD");
    private RadioButton selectScale = new RadioButton("SCALE");
    private static ChoiceBox<String> keyBox = new ChoiceBox<>(FXCollections.observableArrayList("C", "Db", "D", "Eb", "E",
            "F", "Gb", "G", "Ab", "A", "Bb", "B"));
    private GridPane bottom = new GridPane();
    private ToggleGroup selectChordOrScale = new ToggleGroup();
    private ToggleGroup scaleType = new ToggleGroup();
    private ToggleGroup chordType = new ToggleGroup();
    private HBox white_keyPane = new HBox();
    private HBox black_keyPane = new HBox();
    private GridPane keyPane = new GridPane();
    private Button resetButton = new Button("RESET");

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
            button.setPrefSize(40,120);
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            white_keyPane.getChildren().add(button);
            keyBoard.add(button);
        }

        for(Button button: blackKeys){
            button.setPrefSize(30,80);
            button.setStyle("-fx-background-color: black");
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            black_keyPane.getChildren().add(button);
            keyBoard.add(button);
        }
        for(Button button: keyBoard){
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    colorButton(button);
                }
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
            for(Button button: keyBoard){
                setToDefault(button);
            }
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
                chord = new MajorTriad(root);
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for(int i = 0; i < chordNotes.length; i++){
                chordNotesNames.add(chordNotes[i].getName());
            }
            System.out.println(chordNotesNames);
            for(Button button: keyBoard){
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
                chord = new MinorTriad(root);
            } catch (InvalidNoteException e) {
                e.printStackTrace();
            }
            Note[] chordNotes = chord.notes();
            ArrayList<String> chordNotesNames = new ArrayList<>();
            for(int i = 0; i < chordNotes.length; i++){
                chordNotesNames.add(chordNotes[i].getName());
            }
            System.out.println(chordNotesNames);
            for(Button button: keyBoard){
                if(chordNotesNames.contains(button.getTooltip().getText())){
                    colorButton(button);
                }
            }
        });

        major7thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                Chord chord = null;
                try {
                    chord = new Major7th(root);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                Note[] chordNotes = chord.notes();
                ArrayList<String> chordNotesNames = new ArrayList<>();
                for(int i = 0; i < chordNotes.length; i++){
                    chordNotesNames.add(chordNotes[i].getName());
                }
                System.out.println(chordNotesNames);
                for(Button button: keyBoard){
                    if(chordNotesNames.contains(button.getTooltip().getText())){
                        colorButton(button);
                    }
                }
            }
        });

        minor7thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                Chord chord = null;
                try {
                    chord = new Minor7th(root);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                Note[] chordNotes = chord.notes();
                ArrayList<String> chordNotesNames = new ArrayList<>();
                for(int i = 0; i < chordNotes.length; i++){
                    chordNotesNames.add(chordNotes[i].getName());
                }
                System.out.println(chordNotesNames);
                for(Button button: keyBoard){
                    if(chordNotesNames.contains(button.getTooltip().getText())){
                        colorButton(button);
                    }
                }
            }
        });

        major9thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                Chord chord = null;
                try {
                    chord = new Major9th(root);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                Note[] chordNotes = chord.notes();
                ArrayList<String> chordNotesNames = new ArrayList<>();
                for(int i = 0; i < chordNotes.length; i++){
                    chordNotesNames.add(chordNotes[i].getName());
                }
                System.out.println(chordNotesNames);
                for(Button button: keyBoard){
                    if(chordNotesNames.contains(button.getTooltip().getText())){
                        colorButton(button);
                    }
                }
            }
        });

        minor9thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                Chord chord = null;
                try {
                    chord = new Minor9th(root);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                Note[] chordNotes = chord.notes();
                ArrayList<String> chordNotesNames = new ArrayList<>();
                for(int i = 0; i < chordNotes.length; i++){
                    chordNotesNames.add(chordNotes[i].getName());
                }
                System.out.println(chordNotesNames);
                for(Button button: keyBoard){
                    if(chordNotesNames.contains(button.getTooltip().getText())){
                        colorButton(button);
                    }
                }
            }
        });

        dominant7thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                Chord chord = null;
                try {
                    chord = new Dominant7th(root);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                Note[] chordNotes = chord.notes();
                ArrayList<String> chordNotesNames = new ArrayList<>();
                for(int i = 0; i < chordNotes.length; i++){
                    chordNotesNames.add(chordNotes[i].getName());
                }
                System.out.println(chordNotesNames);
                for(Button button: keyBoard){
                    if(chordNotesNames.contains(button.getTooltip().getText())){
                        colorButton(button);
                    }
                }
            }
        });
        dominant9thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resetButtons();
                Note root = null;
                try {
                    root = new Note("C3");
                } catch (InvalidNoteException e1) {
                    e1.printStackTrace();
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
                    chord = new Dominant9th(root);
                } catch (InvalidNoteException e) {
                    e.printStackTrace();
                }
                Note[] chordNotes = chord.notes();
                ArrayList<String> chordNotesNames = new ArrayList<>();
                for(int i = 0; i < chordNotes.length; i++){
                    chordNotesNames.add(chordNotes[i].getName());
                }
                System.out.println(chordNotesNames);
                for(Button button: keyBoard){
                    if(chordNotesNames.contains(button.getTooltip().getText())){
                        colorButton(button);
                    }
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
        if(button.getStyle().contains("blue") || button.getStyle().contains("red")){
            setToDefault(button);
        }else if(whiteKeys.contains(button)) button.setStyle("-fx-background-color: blue");
        else button.setStyle("-fx-background-color: red");
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
        root.setStyle("-fx-background-color: slategrey");
        root.setPadding(new Insets(10,10,10,10));
        root.setBottom(bottom);
        GridPane.setHalignment(bottom, HPos.CENTER);
        root.setCenter(keyPane);

        Scene scene = new Scene(root,1000,220);
        learn.setFullScreen(false);
        learn.setResizable(false);
        learn.setTitle("Learn Chords and Scales");
        learn.setScene(scene);
        learn.show();
    }

    private void setToDefault(Button button){
        if(blackKeys.contains(button)){
            button.setStyle("-fx-background-color: black");
        }else{
            button.setStyle(new Button().getStyle());
        }
    }
    private void resetButtons(){
        for(Button button: whiteKeys){
            setToDefault(button);
        }
        for(Button button: blackKeys){
            setToDefault(button);
        }
    }
}
