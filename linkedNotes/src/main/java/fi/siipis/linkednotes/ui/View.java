package fi.siipis.linkednotes.ui;

import fi.siipis.linkednotes.core.Application;
import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.ui.elements.Container;
import fi.siipis.linkednotes.ui.elements.Frame;
import fi.siipis.linkednotes.ui.elements.SideBar;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Amalia Surakka
 */
public class View {

    private Stage stage;

    private Application application;

    private Frame frame;

    private SideBar sideBar;

    private Scene scene;

    public View(Application application, Stage stage) {
        this.application = application;

        this.initView();
        this.initStage(stage);

        stage.setScene(scene);
    }

    private void initView() {
        this.frame = new Frame();
        this.sideBar = new SideBar(application);
        this.sideBar.update();
        this.frame.setLeft(sideBar);
        this.scene = new Scene(frame);
    }

    private void initStage(Stage stage) {
        this.stage = stage;

        stage.setTitle("linkedNotes");
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setMinWidth(1024);
        stage.setMinHeight(600);
    }

    public void welcome() {
        Container container = new Container();

        Text welcomeText = new Text("Welcome!");

        container.add(welcomeText);

        this.setContent(container);
    }

    public void reader(Article article) {
        Container container = new Container();
        Text text = new Text(article.getContent());

        container.add(text);

        this.setContent(container);
    }

    public void editor(Article article) {
        Container container = new Container();

        TextArea textArea = new TextArea(article.getContent());
        textArea.setWrapText(true);

        TextField textField = new TextField(article.getKeywordsAsString());

        container.add(textArea);
        container.add(textField);

        this.setContent(container);
    }

    public Frame getFrame() {
        return this.frame;
    }

    public Scene getScene() {
        return this.scene;
    }

    private void setContent(Node node) {
        this.frame.setCenter(node);
    }
}
