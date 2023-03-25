package sample.views;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class CommonMenu extends MenuBar {
    public CommonMenu() {
        super();
        this.setUseSystemMenuBar(true);
        // create a menu
        Menu m = new Menu("Menu");
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
        // add menu items to menu
        m.getItems().add(aboutMidiP);
        m.getItems().add(quitMidiP);
        // add menu to menu bar
        this.getMenus().add(m);
        //Add style on menu bar so that it is visible from the user
        this.setStyle("-fx-background-color: #77838f");
    };
}
