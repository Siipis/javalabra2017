package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.ui.View;
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
        Library.getInstance().update();

        view = new View(this, stage);

        view.welcome();

        stage.show();
    }

    public void readArticle(String path) {
        view.reader(this.getArticle(path));
    }

    public void editArticle(String path) {
        view.editor(this.getArticle(path));
    }

    private Article getArticle(String path) {
        Library library = Library.getInstance();

        Article article = library.findArticle(path);

        if (article == null) {
            FileHandler fileHandler = FileHandler.getInstance();
            Parser parser = Parser.getInstance();

            article = parser.toArticle(fileHandler.readFile(path));

            library.addArticle(article);
        }

        return article;
    }
}
