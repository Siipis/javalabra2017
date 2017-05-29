package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.ui.UI;
import java.io.File;

/**
 *
 * @author Amalia Surakka
 */
public class Controller {

    private Navigator navigator;

    private FileHandler fileHandler;

    private Parser parser;

    private Library library;

    private UI ui;

    public Controller() {
        navigator = new Navigator();
        fileHandler = new FileHandler();
        parser = new Parser();
        library = new Library();
        ui = new UI();
    }

    /**
     * Build the underlying data and launch the UI
     */
    public void build() {
        // Temporary demo
        navigator.setRootPath("test/files");
        
        System.out.println("Opening directory 'icecream'.\n");

        navigator.open("icecream");
        
        System.out.println("Listing file contents:");
        
        for (File file : navigator.list()) {
            System.out.println("   - " + file.getPath());
        }
    }
}
