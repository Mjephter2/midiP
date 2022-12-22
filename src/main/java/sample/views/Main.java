package sample.views;

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

/**
 * GUI for application main window.
 */
public final class Main extends Application {
    /**
     * Top Level pane.
     */
    private final BorderPane root = new BorderPane();

    /**
     * Center Pane.
     */
    private final GridPane center = new GridPane();

    /**
     * Button for opening the Scales / Chords learning window.
     */
    private final Button mainWindowLearnButton = new Button("LEARN");

    /**
     * Button for opening the Free Play window.
     */
    private final Button mainWindowFreePlayButton = new Button("FREE\nPLAY");

    /**
     * main Font in the main window.
     */
    private final Font mainWindowFont = new Font("Times New Roman", 35);

    @Override
    public void start(final Stage primaryStage) throws Exception {
        root.setStyle("-fx-background-color: lightgray;");
        root.setPadding(new Insets(10, 10, 10, 10));
        center.setHgap(2);
        center.setVgap(2);
        center.setPadding(new Insets(0, 30, 2, 30));

        Label info = new Label("");
        Label info2 = new Label("");
        info2.setVisible(false);

        Label mainWelcomeLabel = new Label("Welcome to Piano Display");
        root.setTop(mainWelcomeLabel);
        mainWelcomeLabel.setFont(new Font("aerial", 25));
        BorderPane.setAlignment(mainWelcomeLabel, Pos.CENTER);

        mainWindowLearnButton.setFont(mainWindowFont);
        mainWindowLearnButton.setStyle(
                "-fx-text-fill: blue;"
                            + "-fx-background-radius: 30px;"
                            + "-fx-background-color: darkgray;");
        mainWindowLearnButton.setPrefSize(200, 200);
        mainWindowLearnButton.setOnMouseEntered(e -> {
            mainWindowLearnButton.setPrefSize(210, 210);
            mainWindowFreePlayButton.setPrefSize(190, 190);
            mainWindowLearnButton.setStyle(
                    "-fx-text-fill: white;"
                            + "-fx-background-radius: 30px;"
                            + "-fx-background-color: green;");
            info.setText("Learn");
            info2.setText("    ");
        });
        mainWindowLearnButton.setOnMouseExited(e -> {
            mainWindowLearnButton.setPrefSize(200, 200);
            mainWindowFreePlayButton.setPrefSize(200, 200);
            mainWindowLearnButton.setStyle(
                    "-fx-text-fill: blue;"
                            + "-fx-background-radius: 30px;"
                            + "-fx-background-color: darkgray;");
            info.setText("");
            info2.setText("");
        });
        mainWindowLearnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                LearnWindow learnWindow = new LearnWindow();
                Stage chordStage = new Stage();
                try {
                    learnWindow.start(chordStage);
                    primaryStage.close();
                    chordStage.show();
                } catch (Exception ex) {
                    System.out.println("Error opening Learn Window!!!");
                    ex.printStackTrace();
                }
            }
        });

        mainWindowFreePlayButton.setStyle(
                "-fx-text-fill: blue;"
                        + "-fx-background-radius: 30px;"
                        + "-fx-background-color: darkgray;");
        mainWindowFreePlayButton.setPrefSize(200, 200);
        mainWindowFreePlayButton.setFont(mainWindowFont);
        mainWindowFreePlayButton.setOnMouseEntered(e -> {
            mainWindowFreePlayButton.setPrefSize(210, 210);
            mainWindowLearnButton.setPrefSize(190, 190);
            mainWindowFreePlayButton.setStyle(
                    "-fx-text-fill: white;"
                            + "-fx-background-radius: 30px;"
                            + "-fx-background-color: green;");
            info.setText("Play freely");
            info2.setText("               ");
        });
        mainWindowFreePlayButton.setOnMouseExited(e -> {
            mainWindowFreePlayButton.setPrefSize(200, 200);
            mainWindowLearnButton.setPrefSize(200, 200);
            mainWindowFreePlayButton.setStyle(
                    "-fx-text-fill: blue;"
                            + "-fx-background-radius: 30px;"
                            + "-fx-background-color: darkgray;");
            info.setText("");
            info2.setText(info.getText());
        });
        mainWindowFreePlayButton.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(final MouseEvent e) {
                FreePlayWindow chordWindowRoot = new FreePlayWindow();
                Stage chordStage = new Stage();
                try {
                    chordWindowRoot.start(chordStage);
                    primaryStage.close();
                    chordStage.show();
                } catch (Exception ex) {
                    System.out.println("Error opening FreePlay Window!!!");
                    ex.printStackTrace();
                }
            }
        });

        root.setCenter(center);
        GridPane.setHalignment(center, HPos.CENTER);
        center.add(mainWindowFreePlayButton, 0, 0);
        center.add(mainWindowLearnButton, 1, 0);

        BorderPane bottom = new BorderPane();
        root.setBottom(bottom);
        Button exit = new Button("Exit");
        exit.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        exit.setPrefSize(35, 35);
        exit.isScaleShape();
        exit.setOnMouseClicked(e -> System.exit(0));
        exit.setOnMouseEntered(e -> {
            exit.setPrefSize(40, 40);
            info.setText("Exit Application");
            info2.setText(info.getText());
        });
        exit.setOnMouseExited(e -> {
            exit.setPrefSize(35, 35);
            info.setText("");
            info2.setText(info.getText());
        });
        bottom.setCenter(exit);
        BorderPane.setAlignment(exit, Pos.BOTTOM_CENTER);
        bottom.setRight(info);
        BorderPane.setAlignment(info, Pos.BOTTOM_RIGHT);
        bottom.setLeft(info2);
        BorderPane.setAlignment(info2, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Midi_Display");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Application entry point.
     * @param args ...
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
