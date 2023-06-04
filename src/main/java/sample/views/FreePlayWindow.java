package sample.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.models.FillerButton;
import sample.models.Note;
import sample.models.Utilities;
import sample.models.exceptions.InvalidNoteException;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static sample.models.NotesNamingMode.FLAT_MODE;
import static sample.models.NotesNamingMode.SHARP_MODE;
import static sample.views.Styles.whiteKeysReleasedCss;
import static sample.views.Styles.whiteKeysPressedCss;
import static sample.views.Styles.blackKeysReleasedCss;
import static sample.views.Styles.blackKeysPressedCSs;

/**
 * GUI for the full size view of a midi piano
 * allowing for free play without audio feedback.
 */
public final class FreePlayWindow extends Application {
    /**
     * Window configuration.
     */
    private final FreePlayWindowConfig freePlayWindowConfig = FreePlayWindowConfig.fullWidthConfig();

    private static int numKeys = 88;

    Map<Integer, String> numKeysToStartKey = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(88, "A0"),
            new AbstractMap.SimpleEntry<>(76, "C1"),
            new AbstractMap.SimpleEntry<>(61, "C2"),
            new AbstractMap.SimpleEntry<>(49, "C2")
    );

    /**
     * Array of Button representing the keys on the piano.
     */
    private final Button[] keyBoard = new Button[numKeys];

    /**
     * List of white keys on the piano in order.
     */
    private final LinkedList<Button> whiteKeys = new LinkedList<>();

    /**
     * List of black keys on the piano in order.
     */
    private final LinkedList<Button> blackKeys = new LinkedList<>();

    HBox blackKeyPane = new HBox();

    HBox whiteKeyPane = new HBox();

    BorderPane root = new BorderPane();

    /**
     * Top Level Menu Bar.
     */
    private final CommonMenu menu = new CommonMenu(true);

    /**
     * Default Button style to help
     * reset buttons to original look.
     */
    private static final String BUTTON_ORIGINAL_STYLE = new Button().getStyle();

    /**
     * Index of the last key pressed
     * as captured from a physical midi device.
     */

    private boolean showNotesNames = false;

    private final MenuItem keyboard88 = new MenuItem("88 Keys");
    private final MenuItem keyboard76 = new MenuItem("76 Keys");
    private final MenuItem keyboard61 = new MenuItem("61 Keys (NEEDS WORK)");
    private final MenuItem keyboard49 = new MenuItem("49 Keys (NEEDS WORK)");

    private void switchNotesNamingMode() {

        for (Button button: keyBoard) {
            String existingTooltip = button.getTooltip().getText();
            try {
                button.setTooltip(new Tooltip(new Note(existingTooltip).getName()));
            } catch (InvalidNoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Initializes the components of the UI.
     */
    private void initialize() {

        final int firstKeyIndex = Utilities.NOTE_NAMES_FLAT.indexOf(numKeysToStartKey.get(numKeys));
        List<Integer> blackIndex = List.of(1, 4, 6, 9, 11);

        final boolean isFlatMode = Note.notesNamingMode == FLAT_MODE;
        for (int i = 0; i < numKeys; i++) {
            String noteName = isFlatMode ?
                    Utilities.NOTE_NAMES_FLAT.get(i + firstKeyIndex)
                    :
                    Utilities.NOTE_NAMES_SHARP.get(i + firstKeyIndex);
            Button button = new Button(noteName);
            if(blackIndex.contains(i % 12)){
                blackKeys.add(button);
            }else{
                whiteKeys.add(button);
            }
            keyBoard[i] = button;
        }

        for (Button button : keyBoard) {
            button.setOnMousePressed(mouseEvent
                    ->
                    button.setStyle("-fx-background-color: gray"));
            button.setOnMouseReleased(mouseEvent -> {
                if (blackKeys.contains(button)) {
                    button.setStyle("-fx-background-color: black");
                } else if (whiteKeys.contains(button)) {
                    button.setStyle(BUTTON_ORIGINAL_STYLE);
                }
            });
        }

        populatedKeyPanes();
    }

    private void populatedKeyPanes() {
        for (Button button : whiteKeys) {
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(freePlayWindowConfig.getWhiteKeysPrefWidth(), freePlayWindowConfig.getWhiteKeysPrefHeight());
            button.setPadding(new Insets(0, 0, 0, 0));
            button.setOnMousePressed(event -> {
                button.setStyle(whiteKeysPressedCss);
                button.setTranslateY(2);
            });

            button.setOnMouseReleased(event -> {
                button.setStyle(whiteKeysReleasedCss);
                button.setTranslateY(0);
            });

            whiteKeyPane.getChildren().add(button);
        }

        for (Button button : blackKeys) {
            button.setTooltip(new Tooltip(button.getText()));
            button.setText("");
            button.setPrefSize(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight());
            button.setPadding(new Insets(0, freePlayWindowConfig.getBlackKeysPaddingRight(), 0, 0));
            button.setOnMousePressed(event -> {
                button.setStyle(blackKeysPressedCSs);
                button.setPrefSize(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight() + 2);
            });

            button.setOnMouseReleased(event -> {
                button.setStyle(blackKeysReleasedCss);
                button.setPrefSize(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight());
            });
            blackKeyPane.getChildren().add(button);
        }
        FillerButton filler = new FillerButton((int) freePlayWindowConfig.getBlackKeysPrefWidth(), (int) freePlayWindowConfig.getBlackKeysPrefHeight());
        filler.setPadding(new Insets(0, freePlayWindowConfig.getBlackKeysPaddingRight(), 0, 0));
        blackKeyPane.getChildren().add(1, filler);
        int i = switch (numKeys) {
            case 88,76 -> 1;
            case 61,49 -> 0;
            default -> throw new IllegalStateException("Unexpected value: " + numKeys);
        };
        while (i < blackKeyPane.getChildren().size() - 1) {
            FillerButton fillerButton1 = new FillerButton(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight());
            FillerButton fillerButton2 = new FillerButton(freePlayWindowConfig.getBlackKeysPrefWidth(), freePlayWindowConfig.getBlackKeysPrefHeight());
            fillerButton1.setPadding(new Insets(0, freePlayWindowConfig.getBlackKeysPaddingRight(), 0, 0));
            fillerButton2.setPadding(new Insets(0, freePlayWindowConfig.getBlackKeysPaddingRight(), 0, 0));
            blackKeyPane.getChildren().add(i + 3, fillerButton1);
            blackKeyPane.getChildren().add(i + 7, fillerButton2);
            i = i + 7;
        }
        for (Button button: blackKeys) {
            button.setStyle(blackKeysReleasedCss);
        }
        for (Button button: whiteKeys) {
            button.setStyle(whiteKeysReleasedCss);
        }
    }

    private void resizeKeyboard(final int numKeys) {
        FreePlayWindow.numKeys = numKeys;
        blackKeyPane.getChildren().clear();
        whiteKeyPane.getChildren().clear();
        whiteKeys.clear();
        blackKeys.clear();

        initialize();
    }

    private void displayNotesNames() {
        for (Button blackButton : whiteKeys) {
            String buttonText = blackButton.getTooltip().getText();
            buttonText = buttonText.substring(0, buttonText.length() - 1);
            blackButton.setFont(new Font("cambria", 8));
            blackButton.setText(buttonText);
            blackButton.setAlignment(Pos.BOTTOM_CENTER);
         }
        for (Button whiteButton : blackKeys) {
            String buttonText = whiteButton.getTooltip().getText();
            buttonText = buttonText.substring(0, buttonText.length() - 1);
            whiteButton.setText(buttonText);
            whiteButton.setTextFill(Color.WHITE);
            whiteButton.setFont(new Font("cambria", 8));
            whiteButton.setAlignment(Pos.BOTTOM_CENTER);
         }
     }

    /**
     * Main entry point.
     * @param args application parameters
     */
    public static void main(final String[] args) {
        launch(args);
    }

    private ContextMenu generateContextMenu(final Stage stage) {
        final ContextMenu contextMenu = new ContextMenu();

        final MenuItem homeItem = new MenuItem("Home");
        homeItem.setOnAction(event -> {
            Main mainWindow = new Main();
            Stage mainStage = new Stage();
            try {
                mainWindow.start(mainStage);
                stage.close();
                mainStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        final MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> System.exit(0));

        final MenuItem showNotesNamesItem = new MenuItem("Show Notes Names");
        showNotesNamesItem.setOnAction(e -> {
            showNotesNames = !showNotesNames;
            if (showNotesNames) {
                displayNotesNames();
            } else {
                for (Button button : whiteKeys) {
                    button.setText("");
                }

                for (Button button : blackKeys) {
                    button.setText("");
                }
            }
        });

        contextMenu.getItems().add(0, showNotesNamesItem);
        contextMenu.getItems().add(1, homeItem);
        contextMenu.getItems().add(2, exitItem);

        return contextMenu;
    }

    @Override
    public void start(final Stage freePlay) {
        initialize();

        menu.flatModeItem.setOnAction(e -> {
            Note.notesNamingMode = FLAT_MODE;
            switchNotesNamingMode();
        });
        menu.sharpModeItem.setOnAction(e -> {
            Note.notesNamingMode = SHARP_MODE;
            switchNotesNamingMode();
        });

        Menu keyboardSize = new Menu("Keyboard Size");
        keyboardSize.getItems().addAll(keyboard88,keyboard76, keyboard61, keyboard49);
        keyboard49.setOnAction(e -> resizeKeyboard(49));
        keyboard61.setOnAction(e -> resizeKeyboard(61));
        keyboard76.setOnAction(e -> resizeKeyboard(76));
        keyboard88.setOnAction(e -> resizeKeyboard(88));
        menu.getMenus().add(keyboardSize);

        root.setStyle("-fx-background-color: #E6BF83");

        whiteKeyPane.setPickOnBounds(false);
        whiteKeyPane.setSpacing(FreePlayWindowConfig.WHITE_KEY_PANE_SPACING);

        blackKeyPane.setPickOnBounds(false);
        blackKeyPane.setPadding(new Insets(0, 0, 0, freePlayWindowConfig.getBlackKeyPaneLeftPadding()));
        blackKeyPane.setSpacing(freePlayWindowConfig.getBlackKeyPaneSpacing());

        // Pane that will contain show_notes ToggleButton and home button
        BorderPane bottomPane = new BorderPane();

        // Hbox inside which we put show_notes and home button
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        // Assign buttons Hbox on the right side of bottomPane
        buttons.setAlignment(Pos.CENTER);
        bottomPane.setCenter(buttons);
        bottomPane.setBackground(Background.fill(Color.TRANSPARENT));
        bottomPane.setPadding(new Insets(0, 0, 20, 0));

        // add menu bar
        bottomPane.setTop(menu);
        root.setBottom(bottomPane);

        // add context menu
        final ContextMenu contextMenu = generateContextMenu(freePlay);
        root.setOnContextMenuRequested(event -> contextMenu.show(bottomPane, event.getScreenX(), event.getScreenY()));
        root.setOnMousePressed(event -> {
            if (contextMenu.isShowing()) {
                contextMenu.hide();
            }
        });

        GridPane keyPane = new GridPane();
        keyPane.setAlignment(Pos.CENTER);
        keyPane.add(whiteKeyPane, 0, 0, 2, 1);
        keyPane.add(blackKeyPane, 0, 0, 2, 1);
        root.setCenter(keyPane);

        Scene scene = new Scene(root, freePlayWindowConfig.getWindowWidth(), freePlayWindowConfig.getWindowHeight());
        freePlay.setFullScreen(false);
        freePlay.setResizable(false);
        freePlay.setTitle("Free Play");
        freePlay.setScene(scene);
        freePlay.show();

        freePlay.setOnCloseRequest(windowEvent -> {
            Main mainWindow = new Main();
            try {
                mainWindow.start(new Stage());
                freePlay.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
