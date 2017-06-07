package fi.siipis.linkednotes.ui.view;

import fi.siipis.linkednotes.ui.elements.Container;
import javafx.scene.text.Text;

/**
 *
 * @author Amalia Surakka
 */
public class WelcomeView extends View {
    
    public void init() {
        Container container = new Container();

        Text welcomeText = new Text("Welcome!");

        container.add(welcomeText);

        this.setContent(container);
    }
}
