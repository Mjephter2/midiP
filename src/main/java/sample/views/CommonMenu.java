package sample.views;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class CommonMenu extends MenuBar {
    public Menu flatModeItem = new Menu("Flat Mode: b");
    public Menu sharpModeItem = new Menu("Sharp Mode: #");
    public Menu selectMidiInput = new Menu("Select Midi Input");

    public CommonMenu(final boolean showMidiList) {
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
        quitMidiP.setOnAction(action -> System.exit(0));

        Menu preferenceSub = new Menu("Preferences");
        Menu setNoteNamingModeItem = new Menu("Set Note Naming Mode");
        preferenceSub.getItems().add(0, setNoteNamingModeItem);

        setNoteNamingModeItem.getItems().add(0, flatModeItem);
        setNoteNamingModeItem.getItems().add(1, sharpModeItem);

        // add menu items to menu
        m.getItems().add(0, aboutMidiP);
        m.getItems().add(1, preferenceSub);
        m.getItems().add(2, quitMidiP);

        // add menus to menu bar
        this.getMenus().add(m);
        if (showMidiList) {
            this.getMenus().add(selectMidiInput);
        }
        //Add style on menu bar so that it is visible from the user
        this.setStyle("-fx-background-color: #77838f");
    }
}
