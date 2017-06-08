package fi.siipis.linkednotes.ui;

import fi.siipis.linkednotes.data.*;
import fi.siipis.linkednotes.ui.elements.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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

    public void reader(SplitMap splitMap) {
        Container container = new Container();
        TextFlow textFlow = new TextFlow();

        container.add(textFlow);

        for (Object o : splitMap.parts()) {
            if (o.getClass().equals(String.class)) {
                // Add regular string
                Text text = new Text();
                text.setText((String) o);

                textFlow.getChildren().add(text);
            } else if (o.getClass().equals(Keyword.class)) {
                // Add keyword string
                KeywordText text = new KeywordText();
                Keyword keyword = (Keyword) o;

                text.setKeyword(keyword);
                text.setText(keyword.getName());
                text.setFill(Color.BLUE);
                
                text.setOnMouseClicked((event) -> {
                    KeywordText k = (KeywordText) event.getSource();
                    
                    application.readArticle(k.getKeyword().getArticle());
                });

                textFlow.getChildren().add(text);
            }
        }

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
