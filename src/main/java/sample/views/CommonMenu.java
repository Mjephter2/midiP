package sample.views;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import sample.models.Note;
import sample.models.NotesNamingMode;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class CommonMenu extends MenuBar {
    public CommonMenu() {
        super();
        this.setUseSystemMenuBar(true);

        Menu m = new Menu("midiP");
        // create menu items and add actions
        MenuItem aboutMidiP = new MenuItem("About midiP");
        aboutMidiP.setOnAction(action -> {
            try {
                Desktop.getDesktop().browse(
                        new URL("https://github.com/Mjephter2/midiP").toURI());
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
        MenuItem quitMidiP = new MenuItem("Quit midiP");
        quitMidiP.setOnAction(action -> {
            System.exit(0);
        });

        Menu preferenceSub = new Menu("Preferences");
        Menu setNoteNamingModeItem = new Menu("Set Note Naming Mode");
        preferenceSub.getItems().add(setNoteNamingModeItem);
        MenuItem flatModeItem = new MenuItem("Flat Mode: b");
        flatModeItem.setOnAction(action -> {
            Note.notesNamingMode = NotesNamingMode.FLAT_MODE;
        });
        MenuItem sharpModeItem = new MenuItem("Sharp Mode: #");
        sharpModeItem.setOnAction(action -> {
            Note.notesNamingMode = NotesNamingMode.SHARP_MODE;
        });
        setNoteNamingModeItem.getItems().add(flatModeItem);
        setNoteNamingModeItem.getItems().add(sharpModeItem);

        // add menu items to menu
        m.getItems().add(aboutMidiP);
        m.getItems().add(preferenceSub);
        m.getItems().add(quitMidiP);

        // add menus to menu bar
        this.getMenus().add(m);
        //Add style on menu bar so that it is visible from the user
        this.setStyle("-fx-background-color: #77838f");
    };
}
