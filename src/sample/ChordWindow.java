package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChordWindow extends Application {

    @Override
    public void start(Stage primaryStage2) throws Exception{
        BorderPane root = new BorderPane();
        Button test = new Button("tEST");
        root.setCenter(test);
        test.setOnMouseClicked(new EventHandler<MouseEvent>() {
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

        Scene scene = new Scene(root,400,400);
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
