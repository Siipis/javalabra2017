package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.ui.view.EditorView;
import fi.siipis.linkednotes.ui.view.ReaderView;
import fi.siipis.linkednotes.ui.view.WelcomeView;
import javafx.stage.Stage;

/**
 *
 * @author Amalia Surakka
 */
public class Application extends javafx.application.Application {

    private WelcomeView welcomeView;

    private EditorView editorView;

    private ReaderView readerView;
    
    private Stage stage;

    public Application() {
        Navigator.getInstance().setRootPath(Utils.testRootPath); // Use the test root path for now
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        Library.getInstance().update();

        stage.setTitle("linkedNotes");
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setMinWidth(1024);
        stage.setMinHeight(600);

        welcomeView = new WelcomeView(this);
        editorView = new EditorView(this);
        readerView = new ReaderView(this);

        stage.setScene(welcomeView.get());

        stage.show();
    }
    
    public void readArticle(String path) {
        Library library = Library.getInstance();
        
        Article article = library.findArticle(path);
        
        if (article == null) {
            FileHandler fileHandler = FileHandler.getInstance();
            Parser parser = Parser.getInstance();

            article = parser.toArticle(fileHandler.readFile(path));
            
            library.addArticle(article);
        }
        
        readerView.setArticle(article);
        
        this.stage.setScene(readerView.get());
    }
    
    public void editArticle(String path) {
        
    }
}
