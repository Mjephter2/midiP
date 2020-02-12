/*
*author: Maurice Jephter
 */


package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.LinkedList;

public class ChordWindow extends Application {
    private static final int NUMBER_OF_KEYS = 27;
    private Button[] keyBoard = new Button[NUMBER_OF_KEYS];   //array containing the piano keys
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


    @Override
    public void start(Stage primaryStage2) throws Exception{
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: lightgray");
        root.setPadding(new Insets(10,10,10,10));
        Button homeButton = new Button("RETURN TO HOMEPAGE");
        Button resetButton = new Button("RESET");
        homeButton.setPrefSize(160,30);
        GridPane bottom = new GridPane();
        //bottom.setGridLinesVisible(true);
        bottom.setStyle("-fx-background-color: darkgray;");
        bottom.setPadding(new Insets(10,10,10,10));
        GridPane.setHalignment(bottom, HPos.CENTER);
        bottom.setHgap(10);
        bottom.setVgap(10);

        // chord type buttons
        majorTriadButton.setPrefSize(100,50);
        minorTriadButton.setPrefSize(100,50);
        bottom.add(major7thButton,4,0);
        bottom.add(minor7thButton,2,1);
        bottom.add(major9thButton,5,0);
        bottom.add(minor9thButton,3,1);
        bottom.add(dominant7thButton,4,1);
        bottom.add(dominant9thButton,5,1);
        major7thButton.setPrefSize(100,50);
        minor7thButton.setPrefSize(100,50);
        major9thButton.setPrefSize(100,50);
        minor9thButton.setPrefSize(100,50);
        dominant7thButton.setPrefSize(100,50);
        dominant9thButton.setPrefSize(100,50);
        bottom.add(homeButton,10,1);
        bottom.add(resetButton,10,0);
        Label keyLabel = new Label("Select Chord ");
        keyLabel.setPrefWidth(70);
        ChoiceBox<String> keyBox = new ChoiceBox<>(FXCollections.observableArrayList("C", "Db","D", "Eb", "E",
                "F", "Gb","G", "Ab", "A", "Bb", "B"));
        keyBox.setValue("Ab");

        majorTriadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!majorTriadButton.isSelected()) {
                    return;
                }
                reset();
                String key = keyBox.getValue();
                int index = 0;
                //System.out.println(key + " Minor Scale");

                switch (key) {
                    case "C":   index = 0;  break;
                    case "Db":  index = 1;  break;
                    case "D":   index = 2;  break;
                    case "Eb":  index = 3;  break;
                    case "E":   index = 4;  break;
                    case "F":   index = 5;  break;
                    case "Gb":  index = 6;  break;
                    case "G":   index = 7;  break;
                    case "Ab":  index = 8;  break;
                    case "A":   index = 9;  break;
                    case "Bb":  index = 10; break;
                    case "B":   index = 11; break;
                }
                for (int i = 0; i <= 12; i++){
                    if(i==0 || i==4 || i==7) {
                        if (blackKeys.contains(keyBoard[index + i])) {
                            keyBoard[index + i].setStyle("-fx-background-color: red");
                        } else keyBoard[index + i].setStyle("-fx-background-color: blue");
                    }
                    else{
                        continue;
                    }
                }
            }
        });
        minorTriadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!minorTriadButton.isSelected()) {
                    return;
                }
                reset();
                String key = keyBox.getValue();
                int index = 0;
                //System.out.println(key + " Minor Scale");

                switch (key) {
                    case "C":   index = 0;  break;
                    case "Db":  index = 1;  break;
                    case "D":   index = 2;  break;
                    case "Eb":  index = 3;  break;
                    case "E":   index = 4;  break;
                    case "F":   index = 5;  break;
                    case "Gb":  index = 6;  break;
                    case "G":   index = 7;  break;
                    case "Ab":  index = 8;  break;
                    case "A":   index = 9;  break;
                    case "Bb":  index = 10; break;
                    case "B":   index = 11; break;
                }
                for (int i = 0; i <= 12; i++){
                    if(i==0 || i==3 || i==7) {
                        if (blackKeys.contains(keyBoard[index + i])) {
                            keyBoard[index + i].setStyle("-fx-background-color: red");
                        } else keyBoard[index + i].setStyle("-fx-background-color: blue");
                    }
                    else{
                        continue;
                    }
                }
            }
        });
        major7thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!major7thButton.isSelected()) {
                    return;
                }
                reset();
                String key = keyBox.getValue();
                int index = 0;
                //System.out.println(key + " Minor Scale");

                switch (key) {
                    case "C":   index = 0;  break;
                    case "Db":  index = 1;  break;
                    case "D":   index = 2;  break;
                    case "Eb":  index = 3;  break;
                    case "E":   index = 4;  break;
                    case "F":   index = 5;  break;
                    case "Gb":  index = 6;  break;
                    case "G":   index = 7;  break;
                    case "Ab":  index = 8;  break;
                    case "A":   index = 9;  break;
                    case "Bb":  index = 10; break;
                    case "B":   index = 11; break;
                }
                for (int i = 0; i <= 12; i++){
                    if(i==0 || i==4 || i==7 || i==11) {
                        if (blackKeys.contains(keyBoard[index + i])) {
                            keyBoard[index + i].setStyle("-fx-background-color: red");
                        } else keyBoard[index + i].setStyle("-fx-background-color: blue");
                    }
                    else{
                        continue;
                    }
                }
            }
        });
        minor7thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!minor7thButton.isSelected()) {
                    return;
                }
                reset();
                String key = keyBox.getValue();
                int index = 0;
                //System.out.println(key + " Minor Scale");

                switch (key) {
                    case "C":   index = 0;  break;
                    case "Db":  index = 1;  break;
                    case "D":   index = 2;  break;
                    case "Eb":  index = 3;  break;
                    case "E":   index = 4;  break;
                    case "F":   index = 5;  break;
                    case "Gb":  index = 6;  break;
                    case "G":   index = 7;  break;
                    case "Ab":  index = 8;  break;
                    case "A":   index = 9;  break;
                    case "Bb":  index = 10; break;
                    case "B":   index = 11; break;
                }
                for (int i = 0; i <= 12; i++){
                    if(i==0 || i==3 || i==7 || i==10) {
                        if (blackKeys.contains(keyBoard[index + i])) {
                            keyBoard[index + i].setStyle("-fx-background-color: red");
                        } else keyBoard[index + i].setStyle("-fx-background-color: blue");
                    }
                    else{
                        continue;
                    }
                }
            }
        });
        major9thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!major9thButton.isSelected()) {
                    return;
                }
                reset();
                String key = keyBox.getValue();
                int index = 0;
                //System.out.println(key + " Minor Scale");

                switch (key) {
                    case "C":   index = 0;  break;
                    case "Db":  index = 1;  break;
                    case "D":   index = 2;  break;
                    case "Eb":  index = 3;  break;
                    case "E":   index = 4;  break;
                    case "F":   index = 5;  break;
                    case "Gb":  index = 6;  break;
                    case "G":   index = 7;  break;
                    case "Ab":  index = 8;  break;
                    case "A":   index = 9;  break;
                    case "Bb":  index = 10; break;
                    case "B":   index = 11; break;
                }
                for (int i = 0; i <= 14; i++){
                    if(i==0 || i==4 || i==7 || i==11 || i==14) {
                        if (blackKeys.contains(keyBoard[index + i])) {
                            keyBoard[index + i].setStyle("-fx-background-color: red");
                        } else keyBoard[index + i].setStyle("-fx-background-color: blue");
                    }
                    else{
                        continue;
                    }
                }
            }
        });
        minor9thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!minor9thButton.isSelected()) {
                    return;
                }
                reset();
                String key = keyBox.getValue();
                int index = 0;
                //System.out.println(key + " Minor Scale");

                switch (key) {
                    case "C":   index = 0;  break;
                    case "Db":  index = 1;  break;
                    case "D":   index = 2;  break;
                    case "Eb":  index = 3;  break;
                    case "E":   index = 4;  break;
                    case "F":   index = 5;  break;
                    case "Gb":  index = 6;  break;
                    case "G":   index = 7;  break;
                    case "Ab":  index = 8;  break;
                    case "A":   index = 9;  break;
                    case "Bb":  index = 10; break;
                    case "B":   index = 11; break;
                }
                for (int i = 0; i <= 14; i++){
                    if(i==0 || i==3 || i==7 || i==10 || i==14) {
                        if (blackKeys.contains(keyBoard[index + i])) {
                            keyBoard[index + i].setStyle("-fx-background-color: red");
                        } else keyBoard[index + i].setStyle("-fx-background-color: blue");
                    }
                    else{
                        continue;
                    }
                }
            }
        });
        dominant7thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!dominant7thButton.isSelected()) {
                    return;
                }
                reset();
                String key = keyBox.getValue();
                int index = 0;
                //System.out.println(key + " Minor Scale");

                switch (key) {
                    case "C":   index = 0;  break;
                    case "Db":  index = 1;  break;
                    case "D":   index = 2;  break;
                    case "Eb":  index = 3;  break;
                    case "E":   index = 4;  break;
                    case "F":   index = 5;  break;
                    case "Gb":  index = 6;  break;
                    case "G":   index = 7;  break;
                    case "Ab":  index = 8;  break;
                    case "A":   index = 9;  break;
                    case "Bb":  index = 10; break;
                    case "B":   index = 11; break;
                }
                for (int i = 0; i <= 12; i++){
                    if(i==0 || i==4 || i==7 || i==10) {
                        if (blackKeys.contains(keyBoard[index + i])) {
                            keyBoard[index + i].setStyle("-fx-background-color: red");
                        } else keyBoard[index + i].setStyle("-fx-background-color: blue");
                    }
                    else{
                        continue;
                    }
                }
            }
        });
        dominant9thButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!dominant9thButton.isSelected()) {
                    return;
                }
                reset();
                String key = keyBox.getValue();
                int index = 0;
                //System.out.println(key + " Minor Scale");

                switch (key) {
                    case "C":   index = 0;  break;
                    case "Db":  index = 1;  break;
                    case "D":   index = 2;  break;
                    case "Eb":  index = 3;  break;
                    case "E":   index = 4;  break;
                    case "F":   index = 5;  break;
                    case "Gb":  index = 6;  break;
                    case "G":   index = 7;  break;
                    case "Ab":  index = 8;  break;
                    case "A":   index = 9;  break;
                    case "Bb":  index = 10; break;
                    case "B":   index = 11; break;
                }
                for (int i = 0; i <= 14; i++){
                    if(i==0 || i==4 || i==7 || i==10 || i==14) {
                        if (blackKeys.contains(keyBoard[index + i])) {
                            keyBoard[index + i].setStyle("-fx-background-color: red");
                        } else keyBoard[index + i].setStyle("-fx-background-color: blue");
                    }
                    else{
                        continue;
                    }
                }
            }
        });

        ToggleGroup chordType = new ToggleGroup();
        majorTriadButton.setToggleGroup(chordType);
        minorTriadButton.setToggleGroup(chordType);
        majorTriadButton.setToggleGroup(chordType);
        minorTriadButton.setToggleGroup(chordType);
        major7thButton.setToggleGroup(chordType);
        minor7thButton.setToggleGroup(chordType);
        major9thButton.setToggleGroup(chordType);
        minor9thButton.setToggleGroup(chordType);
        dominant7thButton.setToggleGroup(chordType);
        dominant9thButton.setToggleGroup(chordType);



        bottom.add(keyLabel, 0, 0);
        Label fillerLabel1 = new Label("filler label"); fillerLabel1.setVisible(false);
        Label fillerLabel2 = new Label("filler label"); fillerLabel2.setVisible(false);
        Label fillerLabel3 = new Label("filler label"); fillerLabel3.setVisible(false);
        Label fillerLabel4 = new Label("filler label"); fillerLabel4.setVisible(false);
        Label fillerLabel5 = new Label("filler label"); fillerLabel5.setVisible(false);
        Label fillerLabel6 = new Label("filler label"); fillerLabel6.setVisible(false);
        Label fillerLabel7 = new Label("filler label"); fillerLabel7.setVisible(false);
        bottom.add(fillerLabel1,1,1);
        bottom.add(fillerLabel2,2,1);
        bottom.add(fillerLabel3,3,1);
        bottom.add(fillerLabel4,4,1);
        bottom.add(fillerLabel5,5,1);
        bottom.add(fillerLabel6,6,0);
        bottom.add(fillerLabel7,8,0);

        //keyboard display
        for(int i = 0; i < NUMBER_OF_KEYS; i++){ // refer to keyboardReferences.txt
            keyBoard[i] = new Button("");
            keyBoard[i].setTooltip(new Tooltip("KeyBoard[" + i + "]"));
        }
        for(int i = 0; i < NUMBER_OF_KEYS; i++) {
            if (i == 1 || i == 3 || i == 6 || i == 8 || i == 10 || i == 13 || i == 15 || i == 18 || i == 20 || i == 22 || i ==25) {
                blackKeys.add(keyBoard[i]);
                continue;
            }
            whiteKeys.add(keyBoard[i]);
        }

        HBox white_keyPane = new HBox();
        white_keyPane.setPickOnBounds(false);
        HBox black_keyPane = new HBox();
        black_keyPane.setPickOnBounds(false);

        // fix size of white keys and add to white_keyPane
        String buttonOriginalStyle = keyBoard[0].getStyle();
        for(Button button: whiteKeys){
            button.setPrefSize(40,120);
            white_keyPane.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(button.getStyle().contains("-fx-background-color: blue")) {
                        button.setStyle(buttonOriginalStyle);
                    }
                    else button.setStyle("-fx-background-color: blue");
                }
            });
        }

        // fix size of black keys and add to black_keyPane
        for(Button button: blackKeys){
            button.setPrefSize(30,80);
            button.setStyle("-fx-background-color: black");
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(button.getStyle().contains("-fx-background-color: black")) {
                        button.setStyle("-fx-background-color: red");
                    }
                    else button.setStyle("-fx-background-color: black");
                }
            });
        }
        black_keyPane.getChildren().add(keyBoard[1]);
        black_keyPane.getChildren().add(keyBoard[3]);
        Button filler1 = new Button();
        filler1.setPrefSize(32,50);
        black_keyPane.getChildren().add(filler1);
        filler1.setVisible(false);
        for(int i = 6; i<=10;i=i+2){
            black_keyPane.getChildren().add(keyBoard[i]);
        }
        Button filler2 = new Button();
        filler2.setPrefSize(33,50);
        black_keyPane.getChildren().add(filler2);
        filler2.setVisible(false);
        black_keyPane.getChildren().add(keyBoard[13]);
        black_keyPane.getChildren().add(keyBoard[15]);
        Button filler3 = new Button();
        filler3.setPrefSize(32,50);
        black_keyPane.getChildren().add(filler3);
        filler3.setVisible(false);
        for(int i = 18; i<=22;i=i+2){
            black_keyPane.getChildren().add(keyBoard[i]);
        }
        Button filler4 = new Button();
        filler4.setPrefSize(33,50);
        black_keyPane.getChildren().add(filler4);
        filler4.setVisible(false);
        black_keyPane.getChildren().add(keyBoard[25]);

        black_keyPane.setPadding(new Insets(0,0,0,25));
        black_keyPane.setSpacing(10);
        white_keyPane.setSpacing(0.60);
        GridPane keyPane = new GridPane();
        keyPane.setPadding(new Insets(55,20,20,20));
        keyPane.add(white_keyPane,0,0,2,1);
        keyPane.add(black_keyPane,0,0,2,1);
        root.setCenter(keyPane);

        // 1st octave
        // C note




        keyPane.setAlignment(Pos.BASELINE_CENTER);
        BorderPane.setAlignment(keyPane,Pos.CENTER);

        bottom.add(keyBox,1,0);
        bottom.add(majorTriadButton,2,0);
        bottom.add(minorTriadButton,3,0);
        root.setBottom(bottom);
        BorderPane.setAlignment(bottom,Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(homeButton, Pos.BOTTOM_RIGHT);

        resetButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                reset();
            }
        });
        homeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage primaryStage = new Stage();
                Main mainWindow = new Main();
                try {
                    mainWindow.start(primaryStage);
                    primaryStage2.close();
                    primaryStage.show();
                } catch (Exception ex) {
                    System.out.println("Error opening Main Window!!!");
                    ex.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(root,1000,400);
        primaryStage2.setFullScreen(false);
        primaryStage2.setResizable(false);
        primaryStage2.setTitle("Chord Display");
        primaryStage2.setScene(scene);
        primaryStage2.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
    private void reset(){
        for(Button button: whiteKeys){
            button.setStyle(null);
        }
        for(Button button: blackKeys){
            button.setStyle("-fx-background-color: black");
        }
        
        majorTriadButton.setSelected(false);
        minorTriadButton.setSelected(false);
        major7thButton.setSelected(false);
        minor7thButton.setSelected(false);
        major9thButton.setSelected(false);
        minor9thButton.setSelected(false);
        dominant7thButton.setSelected(false);
        dominant9thButton.setSelected(false);
    }

}
