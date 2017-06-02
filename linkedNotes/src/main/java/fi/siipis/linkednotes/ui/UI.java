package fi.siipis.linkednotes.ui;

import fi.siipis.linkednotes.ui.view.EditorView;
import fi.siipis.linkednotes.ui.view.ReaderView;
import fi.siipis.linkednotes.ui.view.WelcomeView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Amalia Surakka
 */
public class UI extends Application {

    private String title;

    private int minWidth;

    private int minHeight;

    public UI() {
        this.title = "linkedNotes";
        this.minWidth = 1024;
        this.minHeight = 600;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(title);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        
        this.viewWelcome(stage);
        
        stage.show();
    }
    
    public void viewWelcome(Stage stage) {
        WelcomeView view = new WelcomeView();
        
        stage.setScene(view.get());
    }

    public void viewEditor(Stage stage) {
        EditorView view = new EditorView();
        
        stage.setScene(view.get());
    }

    public void viewReader(Stage stage) {
        ReaderView view = new ReaderView();
        
        stage.setScene(view.get());
    }
}
