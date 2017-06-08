package fi.siipis.linkednotes.ui.elements;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Amalia Surakka
 */
public class Container extends VBox {

    public void add(Node node) {
        this.getChildren().add(node);
    }
}
