package sample.views;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * GUI for application main window.
 */
public final class Main extends Application {
    public static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    /**
     * Top Level pane.
     */
    private final BorderPane root = new BorderPane();

    /**
     * Center Pane.
     */
    private final GridPane center = new GridPane();

    private final MenuBar menuBar = new CommonMenu();

    /**
     * VBox for the top portion of window.
     */
    private final VBox top = new VBox();

    /**
     * Button for opening the Scales / Chords learning window.
     */
    private final Button mainWindowLearnButton = new Button(
            "LEARN\n"
                    + "CHORDS\n"
                    + "and\n"
                    + "SCALES");

    /**
     * Button for opening the Free Play window.
     */
    private final Button mainWindowFreePlayButton = new Button("FREE\nPLAY");

    /**
     * main Font in the main window.
     */
    private final Font mainWindowFont = new Font("Times New Roman", 35);

    /**
     * Label to placed at bottom of window.
     */
    private final Label info = new Label("");

    /**
     * Welcome Label.
     */
    private final Label mainWelcomeLabel = new Label(
            "Welcome to midiP!\nSelect one option below!");

    @Override
    public void start(final Stage primaryStage) throws Exception {
        root.setStyle("-fx-background-color: lightgray;");
        root.setPadding(new Insets(0, 10, 10, 10));
        Scene scene = new Scene(root, 500, 650, Color.RED);
        primaryStage.setResizable(false);
        primaryStage.setTitle("midiP");
        primaryStage.setScene(scene);

        // Add menu bar to the top of root
        root.setTop(top);
        top.getChildren().add(menuBar);
        // style center grid and add components
        center.setHgap(5);
        center.setVgap(5);
        center.setPadding(new Insets(0, 50, 2, 50));
        center.setAlignment(Pos.CENTER);
        center.add(mainWelcomeLabel, 0, 0);
        center.add(mainWindowFreePlayButton, 0, 1);
        center.add(mainWindowLearnButton, 0, 2);
        GridPane.setHalignment(center, HPos.CENTER);
        GridPane.setValignment(center, VPos.CENTER);

        // style welcome label
        mainWelcomeLabel.setTextAlignment(TextAlignment.CENTER);
        mainWelcomeLabel.setWrapText(true);
        mainWelcomeLabel.setFont(new Font("aerial", 25));
        GridPane.setHalignment(mainWelcomeLabel, HPos.CENTER);
        GridPane.setHalignment(mainWindowFreePlayButton, HPos.CENTER); // mainWindowFreePlayButton button centered
        GridPane.setHalignment(mainWindowLearnButton, HPos.CENTER); // mainWindowLearnButton button centered
        // style learn button
        mainWindowLearnButton.setTextAlignment(TextAlignment.CENTER);
        mainWindowLearnButton.setWrapText(true);
        mainWindowLearnButton.setFont(mainWindowFont);
        mainWindowLearnButton.setStyle("-fx-background-radius: 30px;"
                + "-fx-background-color: darkgray;");
        mainWindowLearnButton.setPrefSize(210, 300);
        mainWindowLearnButton.setOnMouseEntered(e -> {
            mainWindowLearnButton.setStyle(
                    "-fx-text-fill: white;"
                            + "-fx-background-radius: 30px;"
                            + "-fx-background-color: #010B02;");
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
                LOGGER.info("Error opening Learn Window!!!");
                ex.printStackTrace();
            }
        });

        // style free play button
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
                LOGGER.info("Error opening FreePlay Window!!!");
                ex.printStackTrace();
            }
        });

        // add label at bottom of window
        BorderPane bottom = new BorderPane();
        bottom.setRight(info);
        root.setCenter(center);
        root.setBottom(bottom);
        BorderPane.setAlignment(info, Pos.BOTTOM_RIGHT);

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
