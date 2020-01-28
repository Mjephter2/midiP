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

public class ScaleWindow extends Application {
    private static final int NUMBER_OF_KEYS = 24;

    @Override
    public void start(Stage primaryStage2) throws Exception{
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: lightgray");
        root.setPadding(new Insets(10,10,10,10));
        Button homeButton = new Button("RETURN TO HOMEPAGE");
        homeButton.setPrefSize(160,30);
        GridPane bottom = new GridPane();
        bottom.setStyle("-fx-background-color: darkgray;");
        bottom.setPadding(new Insets(10,10,10,10));
        GridPane.setHalignment(bottom, HPos.CENTER);
        bottom.setHgap(10);
        bottom.setVgap(10);
        //bottom.setGridLinesVisible(true);
        bottom.add(homeButton,10,1);
        Label keyLabel = new Label("Select Scale ");
        keyLabel.setPrefWidth(70);
        ChoiceBox<String> keyBox = new ChoiceBox<>(FXCollections.observableArrayList("C", "Db","D", "Eb", "E",
                "F", "Gb","G", "Ab", "A", "Bb", "B"));
        keyBox.setValue("C");
        ToggleGroup scaleType = new ToggleGroup();
        ToggleButton majorScaleButton = new ToggleButton("Major Scale");

        majorScaleButton.setToggleGroup(scaleType);
        ToggleButton minorScaleButton = new ToggleButton("Minor Scale");
        minorScaleButton.setToggleGroup(scaleType);
        majorScaleButton.setPrefSize(100,50);
        minorScaleButton.setPrefSize(100,50);

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
        Button[] keyBoard = new Button[NUMBER_OF_KEYS];   //array containing the piano keys
        for(int i = 0; i < NUMBER_OF_KEYS; i++){ // refer to keyboardReferences.txt
            keyBoard[i] = new Button("");
            keyBoard[i].setTooltip(new Tooltip("KeyBoard[" + i + "]"));
        }
        LinkedList<Button> whiteKeys = new LinkedList<>();
        LinkedList<Button> blackKeys = new LinkedList<>();
        for(int i = 0; i < NUMBER_OF_KEYS; i++){
            if(i==1 || i==3 || i==6 || i==8 || i==10 || i==13 || i==15 || i==18 || i==20 || i==22) {
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
        for(Button button: whiteKeys){
            button.setPrefSize(40,120);
            button.setStyle("-fx-background_color:black");
            white_keyPane.getChildren().add(button);
        }
        // fix size of black keys and add to black_keyPane
        for(Button button: blackKeys){
            button.setPrefSize(30,80);
            button.setStyle("-fx-background-color: black");
        }
        black_keyPane.getChildren().add(keyBoard[1]);
        black_keyPane.getChildren().add(keyBoard[3]);
        Button filler1 = new Button();
        filler1.setPrefSize(30,50);
        black_keyPane.getChildren().add(filler1);
        filler1.setVisible(false);
        for(int i = 6; i<=10;i=i+2){
            black_keyPane.getChildren().add(keyBoard[i]);
        }
        Button filler2 = new Button();
        filler2.setPrefSize(30,50);
        black_keyPane.getChildren().add(filler2);
        filler2.setVisible(false);
        black_keyPane.getChildren().add(keyBoard[13]);
        black_keyPane.getChildren().add(keyBoard[15]);
        Button filler3 = new Button();
        filler3.setPrefSize(30,50);
        black_keyPane.getChildren().add(filler3);
        filler3.setVisible(false);
        for(int i = 18; i<=22;i=i+2){
            black_keyPane.getChildren().add(keyBoard[i]);
        }

        black_keyPane.setPadding(new Insets(0,0,0,25));
        black_keyPane.setSpacing(10);
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
        bottom.add(majorScaleButton,2,0,2,1);
        bottom.add(minorScaleButton,4,0,2,1);
        root.setBottom(bottom);
        BorderPane.setAlignment(bottom,Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(homeButton, Pos.BOTTOM_RIGHT);

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
                    System.out.println("Error opening chord Window!!!");
                    ex.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(root,700,400);
        primaryStage2.setFullScreen(false);
        primaryStage2.setResizable(false);
        primaryStage2.setTitle("Scale Display");
        primaryStage2.setScene(scene);
        primaryStage2.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
