package sample.views;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
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
    private final Button mainWindowLearnButton = new Button("LEARN\nCHORDS\nand\nSCALES");

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
        mainWindowLearnButton.setTextAlignment(TextAlignment.CENTER);
        mainWindowLearnButton.setWrapText(true);
        root.setStyle("-fx-background-color: lightgray;");
        root.setPadding(new Insets(10, 10, 10, 10));
        center.setHgap(2);
        center.setVgap(2);
        center.setPadding(new Insets(0, 30, 2, 30));

        Label info = new Label("");

        Label mainWelcomeLabel = new Label("Welcome to midiP\n"
                + "Select one option below!");
        mainWelcomeLabel.setTextAlignment(TextAlignment.CENTER);
        root.setTop(mainWelcomeLabel);
        mainWelcomeLabel.setFont(new Font("aerial", 25));
        BorderPane.setAlignment(mainWelcomeLabel, Pos.TOP_CENTER);

        mainWindowLearnButton.setFont(mainWindowFont);
        mainWindowLearnButton.setStyle("-fx-background-radius: 30px;"
                            + "-fx-background-color: darkgray;");
        mainWindowLearnButton.setPrefSize(210, 300);
        mainWindowLearnButton.setOnMouseEntered(e -> {
            mainWindowLearnButton.setStyle(
                    "-fx-text-fill: white;"
                            + "-fx-background-radius: 30px;"
                            + "-fx-background-color: #010B02;");
//            mainWindowLearnButton.setBackground(new Background( new BackgroundImage(new Image("file:/test.png"), null, null, null, new BackgroundSize(100, 100, true, true, true, false))));
            info.setText("Learn");
        });
        mainWindowLearnButton.setOnMouseExited(e -> {
            mainWindowLearnButton.setStyle("-fx-background-radius: 30px;"
                            + "-fx-background-color: darkgray;");
            info.setText("");
        });
        mainWindowLearnButton.setOnMouseClicked(e -> {
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
        });

        mainWindowFreePlayButton.setStyle(
                "-fx-background-radius: 30px;"
                        + "-fx-background-color: darkgray;");
        mainWindowFreePlayButton.setPrefSize(200, 200);
        mainWindowFreePlayButton.setFont(mainWindowFont);
        mainWindowFreePlayButton.setOnMouseEntered(e -> {
            mainWindowFreePlayButton.setStyle(
                    "-fx-text-fill: white;"
                            + "-fx-background-radius: 30px;"
                            + "-fx-background-color: #010B02;");
            info.setText("Play freely");
        });
        mainWindowFreePlayButton.setOnMouseExited(e -> {
            mainWindowFreePlayButton.setStyle(
                    "-fx-background-radius: 30px;"
                            + "-fx-background-color: darkgray;");
            info.setText("");
        });
        mainWindowFreePlayButton.setOnMouseClicked(e -> {
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
        });

        root.setCenter(center);
        GridPane.setHalignment(center, HPos.CENTER);
        GridPane.setValignment(center, VPos.CENTER);
        center.setAlignment(Pos.CENTER);
        center.add(mainWindowFreePlayButton, 0, 0);
        center.add(mainWindowLearnButton, 0, 1);

        BorderPane bottom = new BorderPane();
        root.setBottom(bottom);
        bottom.setRight(info);
        BorderPane.setAlignment(info, Pos.BOTTOM_RIGHT);

        root.setPrefSize(280, 500);
        Scene scene = new Scene(root);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("midiP");
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
