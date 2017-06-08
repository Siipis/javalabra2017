package fi.siipis.linkednotes.ui.view;

import fi.siipis.linkednotes.core.Application;
import fi.siipis.linkednotes.ui.elements.Container;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Amalia Surakka
 */
public class EditorView extends View {

    public EditorView(Application application) {
        super(application);
    }
    
    public void init() {
        Container container = new Container();

        container.add(getEditor());
        container.add(getKeywordField());

        this.setContent(container);
    }

    private TextArea getEditor() {
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);

        return textArea;
    }
    
    private TextField getKeywordField() {
        TextField textField = new TextField();
        
        return textField;
    }
}
