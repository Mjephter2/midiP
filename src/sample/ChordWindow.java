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
import javafx.stage.Stage;

public class ChordWindow extends Application {

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
