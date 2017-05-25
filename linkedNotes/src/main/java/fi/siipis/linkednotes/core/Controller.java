package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.ui.UI;

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
        // TODO: coming soon
    }
}
