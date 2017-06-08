/**
 * Node Container
 *
 * Convenience class for box formatting.
 * Allows changing the formatting of all boxes in a more controlled fashion.
 *
 */
package fi.siipis.linkednotes.ui.elements;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author Amalia Surakka
 */
public class Container extends VBox {

    /**
     * Add a node to the container
     *
     * @param node Node to add
     */
    public void add(Node node) {
        this.getChildren().add(node);
    }
}
