package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.DataClasses.Utilities;

import java.util.ArrayList;
import java.util.LinkedList;

public class FreePlayWindow extends Application {
    private static final int NUMBER_OF_KEYS = 88;
    private Button[] keyBoard = new Button[NUMBER_OF_KEYS];
    private LinkedList<Button> whiteKeys = new LinkedList<>();
    private LinkedList<Button> blackKeys = new LinkedList<>();
    private static int screenWidth = 1248;
    private static int whiteKeySize = 24;
    private static int blackKeySize = 20;
    {
        int i = 0;
        while(i < 88){
            ArrayList<Integer> blackIndex = new ArrayList<>();
            blackIndex.add(1);
            blackIndex.add(4);
            blackIndex.add(6);
            blackIndex.add(9);
            blackIndex.add(11);
            if(blackIndex.contains(i % 12)){
                blackKeys.add(new Button(Utilities.NOTE_NAMES.get(i)));
                //System.out.println("Added " + Utilities.NOTE_NAMES.get(i) + " to the black keys");
            }else{
                whiteKeys.add(new Button(Utilities.NOTE_NAMES.get(i)));
                //System.out.println("Added " + Utilities.NOTE_NAMES.get(i) + " to the white keys");
            }
            i++;
        }
        //System.out.println("white keys: " + whiteKeys.size());
        //System.out.println("black keys: " + blackKeys.size());
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage freePlay) {
        BorderPane root = new BorderPane();
//        freePlay.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent windowEvent) {
//                Main mainWindow = new Main();
//                Stage mainStage = new Stage();
//                try {
//                    mainWindow.start(mainStage);
//                    freePlay.close();
//                    mainStage.show();
//                } catch (Exception ex) {
//                    System.out.println("Error opening Main Window!!!");
//                    ex.printStackTrace();
//                }
//            }
//        });

        HBox white_keyPane = new HBox();
        white_keyPane.setPickOnBounds(false);
        HBox black_keyPane = new HBox();
        black_keyPane.setPickOnBounds(false);
        black_keyPane.setPadding(new Insets(0,0,0,15));
        black_keyPane.setSpacing(4);

        //System.out.println(screenSize.width);
        for(Button button: whiteKeys){
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(whiteKeySize,112);
            white_keyPane.getChildren().add(button);
        }
        for(Button button: blackKeys){
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(blackKeySize,80);
            black_keyPane.getChildren().add(button);
            button.setStyle("-fx-background-color: black");
        }
        black_keyPane.getChildren().add(1, new FillerButton(blackKeySize,80));
        int i = 1;
        while(i < black_keyPane.getChildren().size() - 1){
            black_keyPane.getChildren().add(i + 3, new FillerButton(blackKeySize,80));
            black_keyPane.getChildren().add(i + 7, new FillerButton(blackKeySize,80));
            i = i + 7;
        }

        GridPane keyPane = new GridPane();
        //keyPane.setPadding(new Insets(55,20,20,20));
        keyPane.add(white_keyPane,0,0,2,1);
        keyPane.add(black_keyPane,0,0,2,1);
        root.setCenter(keyPane);

        Scene scene = new Scene(root,screenWidth,112);
        freePlay.setFullScreen(false);
        freePlay.setResizable(false);
        freePlay.setTitle("Free Play");
        freePlay.setScene(scene);
        freePlay.show();
    }
}
