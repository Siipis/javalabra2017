/**
 * Side Bar
 *
 * Convenience class for side bar formatting.
 * Sub class of the View. Handles rendering and events of the side bar.
 */
package fi.siipis.linkednotes.ui.view;

import fi.siipis.linkednotes.core.*;
import fi.siipis.linkednotes.data.Library;
/**
 * Navigation Builder
 *
 * Builds the navigation bar.
 */
import fi.siipis.linkednotes.ui.*;
import fi.siipis.linkednotes.ui.elements.NavItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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

    private Library library;

    /**
     * Constructor
     *
     * @param view View container
     */
    public NavBuilder(View view) {
        this.container = new VBox();

        this.view = view;

        this.navigator = Navigator.getInstance();

        this.fileHandler = FileHandler.getInstance();

        this.library = Library.getInstance();

        VBox navBar = view.getFrame().getNavBar();

        navBar.getChildren().setAll(this.container);
    }

    /**
     * Re-draw the menu
     *
     * @return Menu container
     */
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

    /**
     * Create a new list item and attach event handlers
     *
     * @param path File path
     * @return Menu item
     */
    private NavItem createListItem(String path) {
        NavItem navItem = new NavItem(path);

        navItem.getStyleClass().add("nav-item");

        if (library.getCurrentArticle() != null) {
            String articlePath = library.getCurrentArticle().getFilepath();

            if (articlePath.equals(path)) {
                navItem.getStyleClass().add("nav-item-active");
            }
        }

        navItem.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                navigator.open(navItem.getPath());

                this.update();

                if (fileHandler.isFile(path)) {
                    view.getApplication().readArticle(library.findArticle(path));
                } else {
                    view.viewWelcome();
                }
            }
        });

        this.attachContextMenu(navItem);

        return navItem;
    }

    /**
     * Create list item with different path than displayed text
     *
     * @param text Text to display
     * @param path File path
     * @return Menu item
     */
    private NavItem createListItem(String text, String path) {
        NavItem navItem = this.createListItem(path);

        navItem.setText(text);

        return navItem;
    }

    /**
     * Attach a context menu to the item
     *
     * @param navItem Menu item
     */
    private void attachContextMenu(NavItem navItem) {
        String path = navItem.getPath();

        if (Utils.canonisePath(path).equals(navigator.getRootPath())) {
            return;
        }

        ContextMenu contextMenu = new ContextMenu();

        MenuItem rename = new MenuItem("Rename");
        MenuItem delete = new MenuItem("Delete");

        if (fileHandler.isDirectory(path)) {
            rename.setOnAction((event) -> {
                view.getApplication().renameDirectory(path);
            });

            delete.setOnAction((event) -> {
                view.getApplication().deleteDirectory(path);
            });
        } else if (fileHandler.isFile(path)) {
            rename.setOnAction((event) -> {
                view.getApplication().renameArticle(library.findArticle(path));
            });

            delete.setOnAction((event) -> {
                view.getApplication().deleteArticle(library.findArticle(path));
            });
        } else {
            return;
        }

        contextMenu.getItems().add(rename);
        contextMenu.getItems().add(delete);

        navItem.setContextMenu(contextMenu);
    }
}
