/**
 * Side Bar
 *
 * Convenience class for side bar formatting.
 * Sub class of the View. Handles rendering and events of the side bar.
 */
package fi.siipis.linkednotes.ui.view;

import fi.siipis.linkednotes.core.*;
import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.ui.*;
import fi.siipis.linkednotes.ui.elements.NavItem;
import javafx.scene.layout.VBox;

/**
 *
 * @author Amalia Surakka
 */
public class NavBuilder {

    private View view;

    private VBox container;

    private Navigator navigator;

    private FileHandler fileHandler;

    public NavBuilder(View view) {
        this.container = new VBox();

        this.view = view;

        this.navigator = Navigator.getInstance();

        this.fileHandler = FileHandler.getInstance();

        VBox navBar = (VBox) view.getScene().lookup("#navBar");

        navBar.getChildren().setAll(this.container);
    }

    public VBox update() {
        container.getChildren().clear();

        String currentPath = navigator.getCurrentPath();

        if (fileHandler.isFile(currentPath)) {
            navigator.open("..");
        }

        if (!navigator.currentIsRoot()) {
            container.getChildren().add(this.createListItem("..", navigator.parentPath()));
        }

        for (String path : navigator.list()) {
            container.getChildren().add(this.createListItem(path));
        }

        if (fileHandler.isFile(currentPath)) {
            navigator.open(currentPath);
        }

        return container;
    }

    private NavItem createListItem(String path) {
        NavItem navItem = new NavItem(path);

        navItem.getStyleClass().add("nav-item");

        navItem.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                navigator.open(navItem.getPath());

                this.update();

                if (fileHandler.isFile(path)) {
                    view.getApplication().readArticle(Library.getInstance().findArticle(path));
                }
            }
        });

        return navItem;
    }
    
    private NavItem createListItem(String text, String path) {
        NavItem navItem = this.createListItem(path);
        
        navItem.setText(text);
        
        return navItem;
    }
}
