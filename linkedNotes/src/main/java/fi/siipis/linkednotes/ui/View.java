/**
 * View
 *
 * View class.
 * Renders the UI objects and handles minor events.
 *
 */
package fi.siipis.linkednotes.ui;

import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.data.SplitMap;
import fi.siipis.linkednotes.ui.view.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.*;

/**
 *
 * @author Amalia Surakka
 */
public class View {

    private Application application;

    private Stage stage;

    private Scene scene;

    private BorderPane frame;

    private NavBuilder navBuilder;

    private WelcomeView welcomeView;

    private ReaderView readerView;

    private EditorView editorView;

    /**
     * Constructor
     *
     * @param application Controller
     * @param stage Stage
     */
    public View(Application application, Stage stage) {
        this.application = application;

        this.initStage(stage);
        this.initView();
        
        welcomeView.view();
    }

    /**
     * Prepare the window
     *
     * @param s Stage
     */
    private void initStage(Stage s) {
        this.stage = s;

        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setController(this);
            this.frame = (BorderPane) loader.load(this.getClass().getResource("/main.fxml"));

            this.scene = new Scene(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }

        stage.setTitle("linkedNotes");
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setMinWidth(1024);
        stage.setMinHeight(600);

        stage.setScene(scene);

        stage.show();
    }

    private void initView() {
        this.navBuilder = new NavBuilder(this);

        this.welcomeView = new WelcomeView(this);
        this.readerView = new ReaderView(this);
        this.editorView = new EditorView(this);

        navBuilder.update();
    }

    public void viewWelcome() {
        welcomeView.view();
    }
    
    public void viewReader(SplitMap splitMap) {
        readerView.view(splitMap);
    }
    
    public void viewEditor(Article article) {
        editorView.view(article);
    }

    public Application getApplication() {
        return this.application;
    }

    /**
     * @return Layout object
     */
    public BorderPane getFrame() {
        return this.frame;
    }

    /**
     * @return Scene object
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Set the view contents
     *
     * @param node Node to display
     */
    public void setContent(Node node) {
        this.frame.setCenter(node);
    }

    public void updateNavBar() {
        this.navBuilder.update();
    }
}
