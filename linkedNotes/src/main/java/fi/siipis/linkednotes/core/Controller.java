package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.data.Keyword;
import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.data.Occurrence;
import fi.siipis.linkednotes.ui.UI;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;

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
        this.navigator = new Navigator();
        this.fileHandler = new FileHandler(navigator);
        this.parser = new Parser(fileHandler);
        this.library = new Library();
        this.ui = new UI();
    }

    /**
     * Build the underlying data and launch the UI
     */
    public void build() {
        ui.launch(UI.class);
    }
}
