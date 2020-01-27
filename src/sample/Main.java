package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: lightgray;");
        root.setPadding(new Insets(10,10,10,10));
        GridPane center = new GridPane();
        center.setHgap(2);
        center.setVgap(2);
        Label info = new Label("");
        Label info2 = new Label("");
        info2.setVisible(false);


        center.setPadding(new Insets(0,30,2,30));
        //scale button
        Button mainWindowScaleButton = new Button("SCALES");
        Button mainWindowChordButton = new Button("CHORDS");
        Font mainWindowFont = new Font("Times New Roman",35);

        mainWindowScaleButton.setFont(mainWindowFont);
        mainWindowScaleButton.setStyle("-fx-text-fill: blue; -fx-background-radius: 30px; -fx-background-color: darkgray;");
        mainWindowScaleButton.setPrefSize(200,200);

        mainWindowScaleButton.setOnMouseEntered(e -> {
            mainWindowScaleButton.setPrefSize(210,210);
            mainWindowChordButton.setPrefSize(190,190);
            mainWindowScaleButton.setStyle("-fx-text-fill: white; -fx-background-radius: 30px; -fx-background-color: green;");
            info.setText("Scale view");
            info2.setText("Scale view");
        });
        mainWindowScaleButton.setOnMouseExited(e -> {
            mainWindowScaleButton.setPrefSize(200,200);
            mainWindowChordButton.setPrefSize(200,200);
            mainWindowScaleButton.setStyle("-fx-text-fill: blue; -fx-background-radius: 30px;-fx-background-color: darkgray;");
            info.setText("");
            info2.setText("");
        });
        mainWindowScaleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                ChordWindow chordWindowRoot = new ChordWindow();
                Stage chordStage = new Stage();
                try {
                    chordWindowRoot.start(chordStage);
                    primaryStage.close();
                    chordStage.show();
                } catch (Exception ex) {
                    System.out.println("Error opening chord Window!!!");
                    ex.printStackTrace();
                }
            }
        });
        //chord button
        mainWindowChordButton.setStyle("-fx-text-fill: blue;-fx-background-radius: 30px; -fx-background-color: darkgray;");
        mainWindowChordButton.setPrefSize(200,200);
        mainWindowChordButton.setFont(mainWindowFont);

        mainWindowChordButton.setOnMouseEntered(e -> {
            mainWindowChordButton.setPrefSize(210,210);
            mainWindowScaleButton.setPrefSize(190,190);
            mainWindowChordButton.setStyle("-fx-text-fill: white; -fx-background-radius: 30px;-fx-background-color: green;");
            info.setText("Chord view");
            info2.setText(info.getText());
        });
        mainWindowChordButton.setOnMouseExited(e -> {
            mainWindowChordButton.setPrefSize(200,200);
            mainWindowScaleButton.setPrefSize(200,200);
            mainWindowChordButton.setStyle("-fx-text-fill: blue; -fx-background-radius: 30px;-fx-background-color: darkgray;");
            info.setText("");
            info2.setText(info.getText());
        });

        root.setCenter(center);
        GridPane.setHalignment(center, HPos.CENTER);
        center.add(mainWindowChordButton,0,0);
        center.add(mainWindowScaleButton,1,0);
        Label mainWelcomeLabel = new Label("Welcome to Piano Display");
        root.setTop(mainWelcomeLabel);
        mainWelcomeLabel.setFont(new Font("aerial",25));
        BorderPane.setAlignment(mainWelcomeLabel,Pos.CENTER);

        BorderPane bottom = new BorderPane();
        root.setBottom(bottom);
        Button exit = new Button("Exit");
        bottom.setCenter(exit);
        BorderPane.setAlignment(exit,Pos.BOTTOM_CENTER);
        bottom.setRight(info);
        BorderPane.setAlignment(info,Pos.BOTTOM_RIGHT);
        bottom.setLeft(info2);
        BorderPane.setAlignment(info2,Pos.BOTTOM_LEFT);
        exit.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        exit.setPrefSize(35,35);
        exit.isScaleShape();
        exit.setOnMouseClicked(e -> System.exit(0));
        exit.setOnMouseEntered(e -> {
            exit.setPrefSize(40,40);
            info.setText("Exit Piano Display");
            info2.setText(info.getText());
        });
        exit.setOnMouseExited(e -> {
            exit.setPrefSize(35,35);
            info.setText("");
            info2.setText(info.getText());
        });

        Scene scene = new Scene(root);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Piano Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
