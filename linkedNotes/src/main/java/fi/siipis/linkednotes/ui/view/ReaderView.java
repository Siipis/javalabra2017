package fi.siipis.linkednotes.ui.view;

import javafx.scene.text.Text;

/**
 *
 * @author Amalia Surakka
 */
public class ReaderView extends View {

    public void init() {
        Text text = new Text("Lorem ipsum dolor sit amet...");

        this.setContent(text);
    }
}
