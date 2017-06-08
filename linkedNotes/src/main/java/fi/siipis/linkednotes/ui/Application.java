package fi.siipis.linkednotes.ui;

import fi.siipis.linkednotes.core.*;
import fi.siipis.linkednotes.data.*;
import javafx.stage.Stage;

/**
 *
 * @author Amalia Surakka
 */
public class Application extends javafx.application.Application {

    private View view;

    public Application() {
        Navigator.getInstance().setRootPath(Utils.testRootPath); // Use the test root path for now
    }

    @Override
    public void start(Stage stage) {
        Library library = Library.getInstance();

        library.sync();
        
        view = new View(this, stage);

        view.welcome();

        stage.show();
    }

    public void readArticle(String path) {
        this.readArticle(this.getArticle(path));
    }

    public void readArticle(Article article) {
        view.reader(new SplitMap(article));
    }

    public void editArticle(String path) {
        view.editor(this.getArticle(path));
    }

    private Article getArticle(String path) {
        Library library = Library.getInstance();

        return library.findArticle(path);
    }
}
