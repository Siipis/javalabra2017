package fi.siipis.linkednotes.ui.view;

import fi.siipis.linkednotes.ui.View;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Amalia Surakka
 */
public class WelcomeView extends VBox {

    private View view;
    
    public WelcomeView(View view) {
        this.view = view;
        
        this.init();
    }
    
    private void init() {
        Text text = new Text("Welcome!");
        
        this.getChildren().setAll(text);
    }
    
    public void view() {
        this.view.setContent(this);
    }
}
