/**
 * Navigation Item
 * 
 * Data abstraction class for menu items.
 * Extends the default text behaviour for file paths.
 * 
 */
package fi.siipis.linkednotes.ui.elements;

import fi.siipis.linkednotes.core.Utils;
import javafx.scene.control.Label;

/**
 *
 * @author Amalia Surakka
 */
public class NavItem extends Label {

    private String path;

    public NavItem(String path) {
        super(Utils.plainFileName(path));

        this.path = Utils.canonisePath(path);
        
        this.setPrefWidth(200);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
