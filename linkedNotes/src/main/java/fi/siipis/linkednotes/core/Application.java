package fi.siipis.linkednotes.core;

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
        
    public Application() {
        Navigator.getInstance().setRootPath(Utils.testRootPath); // Use the test root path for now
    }
        
    @Override
    public void start(Stage stage) {
        Library.getInstance().update();
        
        stage.setTitle("linkedNotes");
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setMinWidth(1024);
        stage.setMinHeight(600);
                
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
