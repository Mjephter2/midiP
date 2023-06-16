package sample.views;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.models.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Sample GUI of a full Grand Staff
 */
public class GrandStaffSample extends Application {
    Line[] lines = new Line[52];
    Circle[] circles = new Circle[52];
    Text[] texts = new Text[52];
    public String currentKey;
    public static HashMap<String, Line> noteLines = new HashMap<>();
    public static Circle[] noteCircles = new Circle[52];
    public static Text[] noteTexts = new Text[52];

    public void showNote(final String noteName) {
        Line line = noteLines.get(noteName);
        System.out.println(line);

        int placement = -1;
        for (int i = 0; i < lines.length; i++) {
            placement++;
            if (line == lines[i]) {
                System.out.println("line found");
                System.out.println("placement " + placement);
                break;
            }
        }
        noteCircles[52 - placement].setFill(Color.BLUE);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        double initialStartX = 100.0;
        double initialEndX = 800.0;
        double initialY = 10.0;

        for (int i = 0; i < 21; i++) {
            lines[i] = new Line(initialStartX, initialY + 10 * i, initialEndX, initialY + 10 * i);
            lines[i].setStrokeWidth(2);
            lines[i].setStroke(Color.TRANSPARENT);

            circles[i] = new Circle(lines[i].getStartX(), lines[i].getStartY(), 5);
            circles[i].setVisible(true);
            circles[i].setTranslateX(100);

            texts[i] = new Text();
            texts[i].setManaged(true);
            texts[i].setX(10);
            texts[i].setY(lines[i].getStartY());
            texts[i].setFont(new Font(10));
        }

        double fClefStartY = initialY + 2 * 10;
        for (int i = 21; i < lines.length; i++) {
            lines[i] = new Line(initialStartX, fClefStartY + 10 * i, initialEndX, fClefStartY + 10 * i);
            circles[i] = new Circle(initialStartX, lines[i].getStartY(), 5);

            lines[i].setStrokeWidth(1.5);
            lines[i].setStroke(Color.TRANSPARENT);

            circles[i] = new Circle(lines[i].getStartX(), lines[i].getStartY(), 5);
            circles[i].setVisible(true);
            circles[i].setTranslateX(100);

            texts[i] = new Text();
            if (i == lines.length - 1 || i == 24 || i == 27) {
                texts[i].setText(String.valueOf(i));
            }
            texts[i].setX(10);
            texts[i].setY(lines[i].getStartY());
            texts[i].setFont(new Font(10));
        }

        for (int i : new int[]{11, 13, 15, 17, 19, 23, 25, 27, 29, 31}) {
            lines[i].setStroke(Color.RED);
        }

        // center middle C
        lines[21].setStartY(lines[21].getStartY() - 10);
        lines[21].setEndY(lines[21].getEndY() - 10);
        lines[21].setStartX(lines[21].getStartX() + 425);
        lines[21].setEndX(lines[21].getEndX() - 225);
        lines[21].setVisible(false);
        lines[21].setStroke(Color.BLACK);

        // order notes on staff from bottom to top
        int i = 51;
        List<String> whiteNotesNames = Utilities.WHITE_NOTE_NAMES;
        for (int j = 0; j < 52; j++) {
            String noteName = whiteNotesNames.get(j);
            noteLines.put(noteName, lines[i-j]);
            noteCircles[j] = circles[i-j];
            noteTexts[j] = texts[i-j];
            noteTexts[j].setText(whiteNotesNames.get(j));
        }

        for (int j = 0; j < 20; j++) {
            noteTexts[j].setFill(Color.TRANSPARENT);
        }
        for (int j = 28; j < 34; j++) {
            noteTexts[j].setFill(Color.TRANSPARENT);
        }
        for (int j = 42; j < 52; j++) {
            noteTexts[j].setFill(Color.TRANSPARENT);
        }

        for (int j = 0; j < 52; j++) {
            noteCircles[j].setFill(Color.TRANSPARENT);
        }

        for (int j = 20; j < 28; j++) {
            noteTexts[j].setText(j % 2 == 0 ? "b" : "#");
            noteTexts[j].setFont(Font.font(20));
        }

        for (int j = 34; j < 42; j++) {
            noteTexts[j].setText(j % 2 == 0 ? "b" : "#");
            noteTexts[j].setFont(Font.font(20));
        }

        Line verticalLine = new Line(initialStartX, initialY + 10, initialStartX, initialY + 10 * 23);
        verticalLine.setStrokeWidth(2);
        verticalLine.setTranslateX((initialStartX - initialEndX) / 2.0);
        verticalLine.setTranslateY(-45);

        InputStream stream = null;
        try {
            stream = new FileInputStream("src/main/java/sample/views/trebleClef.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image trebleClefImage = new Image(stream);
        ImageView trebleClef = new ImageView();
        trebleClef.setImage(trebleClefImage);
        trebleClef.setX(10);
        trebleClef.setY(10);
        trebleClef.setFitWidth(75);
        trebleClef.setFitHeight(135);
        trebleClef.setTranslateX(-305);
        trebleClef.setTranslateY(-110);

        stream = null;
        try {
            stream = new FileInputStream("src/main/java/sample/views/bassClef.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image bassClefImage = new Image(stream);
        ImageView bassClef = new ImageView();
        bassClef.setImage(bassClefImage);
        bassClef.setX(10);
        bassClef.setY(10);
        bassClef.setFitWidth(75);
        bassClef.setFitHeight(70);
        bassClef.setTranslateX(-305);
        bassClef.setTranslateY(18);

        stream = null;
        try {
            stream = new FileInputStream("src/main/java/sample/views/leftBrace.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image leftBraceImage = new Image(stream);
        ImageView leftBrace = new ImageView();
        leftBrace.setImage(leftBraceImage);
        leftBrace.setX(10);
        leftBrace.setY(10);
        leftBrace.setFitWidth(30);
        leftBrace.setFitHeight(225);
        leftBrace.setTranslateX(-367);
        leftBrace.setTranslateY(-46);

        //Create a Group object for lines
        Group lineGroup = new Group(lines);
        //Create a Group object for circle
        Group circleGroup = new Group(circles);
        circleGroup.setTranslateX(100);
        //Create a Group for texts objects
        Group textsGroup = new Group(texts);
        textsGroup.setTranslateX(-90);

        StackPane root = new StackPane();
        root.getChildren().add(0, lineGroup);
        root.getChildren().add(1, circleGroup);
        root.getChildren().add(2, textsGroup);
        root.getChildren().add(3, verticalLine);
        root.getChildren().add(4, trebleClef);
        root.getChildren().add(5, leftBrace);
        root.getChildren().add(6, bassClef);

        showNote("C4");
        showNote("B1");

        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Grand Staff Sample");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
