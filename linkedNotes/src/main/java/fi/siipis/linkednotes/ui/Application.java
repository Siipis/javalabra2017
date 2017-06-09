/**
 * Application
 *
 * Controller class.
 * Handles events and updates the view as needed.
 * May also be called by event handlers in the view layer.
 */
package fi.siipis.linkednotes.ui;

import fi.siipis.linkednotes.core.*;
import fi.siipis.linkednotes.data.*;
import fi.siipis.linkednotes.ui.elements.KeywordText;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Amalia Surakka
 */
public class Application extends javafx.application.Application implements Initializable {

    private View view;

    private Navigator navigator;

    private Library library;

    private Parser parser;

    private FileHandler fileHandler;

    public Application() {
        this.navigator = Navigator.getInstance();

        this.navigator.setRootPath(Utils.testRootPath); // Use the test root path for now

        this.library = Library.getInstance();

        this.parser = Parser.getInstance();

        this.fileHandler = FileHandler.getInstance();

        library.sync();
    }

    /**
     * Initialises the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * JavaFX launch method
     *
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) {
        this.view = new View(this, stage);
    }

    @FXML
    private void createDirectory(ActionEvent event) {

    }

    @FXML
    private void createArticle(ActionEvent event) {

    }

    @FXML
    private void closeApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void readArticle(Article article) {
        library.setCurrentArticle(article);
        
        SplitMap splitMap = new SplitMap(article);

        view.viewReader(splitMap);
    }

    @FXML
    public void editArticle(Article article) {
        view.viewEditor(article);
    }

    @FXML
    public void saveArticle(Article article) {
        String content = parser.toFile(article);

        if (fileHandler.writeFile(article.getFilepath(), content)) {
            article.touchSaved();

            library.sync();

            this.readArticle(article);
        } else {
            // TODO: display error
        }
    }
}
