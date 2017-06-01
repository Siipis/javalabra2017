package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.data.Keyword;
import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.data.Occurrence;
import fi.siipis.linkednotes.ui.UI;
import java.io.File;
import java.util.ArrayList;

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
        fileHandler = new FileHandler(navigator);
        parser = new Parser(fileHandler);
        library = new Library();
        ui = new UI();
    }

    /**
     * Build the underlying data and launch the UI
     */
    public void build() {
        // Temporary demo
        navigator.setRootPath("test/files");
        
        fileHandler.createFile("icecream/cherry.txt");
        
        fileHandler.writeFile("icecream/cherry.txt", "Do you like cherry?");
        
        System.out.println("Opening directory 'icecream'.\n");

        navigator.open(".");

        navigator.open("icecream");
        
        System.out.println("Listing file contents:");
        
        for (File file : navigator.list()) {
            System.out.println("   - " + file.getPath());
        }
        
        Article article = parser.toArticle(fileHandler.findFile("cherry.txt"));

        System.out.println("Reading keywords from icecream/cherry.txt");
        
        for (Occurrence o : parser.toOccurrences(article, new Keyword("cherry", article))) {
            System.out.println(o);;
        }
    }
}
