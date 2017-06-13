/**
 * Welcome View
 * 
 * Builds the welcome view.
 */
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
    
    /**
     * Constructor
     * 
     * @param view View container 
     */
    public WelcomeView(View view) {
        this.view = view;
        
        this.init();
    }
    
    /**
     * Initialise the class
     */
    private void init() {
        Text text = new Text("Welcome!");
        
        this.getChildren().setAll(text);
    }
    
    /**
     * Render the view
     */
    public void view() {
        this.view.setContent(this);
    }
}
