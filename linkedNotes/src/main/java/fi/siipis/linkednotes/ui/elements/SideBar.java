/**
 * Side Bar
 *
 * Convenience class for side bar formatting.
 * Sub class of the View. Handles rendering and events of the side bar.
 */
package fi.siipis.linkednotes.ui.elements;

import fi.siipis.linkednotes.core.*;
import fi.siipis.linkednotes.ui.*;
import javafx.scene.control.*;

/**
 *
 * @author Amalia Surakka
 */
public class SideBar extends Container {

    private Application application;

    private ListView<String> listView = new ListView<>();

    public SideBar(Application application) {
        this.application = application;

        this.init();
    }

    private void init() {
        this.add(listView);
    }

    /**
     * Re-draw the side bar. Depends on the Navigator class to track the
     * currently opened folder.
     */
    public void update() {
        Navigator navigator = Navigator.getInstance();

        listView.setCellFactory(lv -> {
            return createCell();
        });

        listView.getItems().clear();

        String currentPath = navigator.getCurrentPath();
        
        if (FileHandler.getInstance().isDirectory(currentPath)) {
            if (!navigator.currentIsRoot()) {
                listView.getItems().add(currentPath + "/..");
            }

            listView.getItems().addAll(navigator.list());
        } else {
            if (!navigator.currentIsRoot()) {
                listView.getItems().add(currentPath + "/../..");
            }

            listView.getItems().addAll(navigator.list(".."));
        }
    }

    /**
     * Create a list item for the side bar and attach click event listeners
     *
     * @return List item
     */
    private ListCell<String> createCell() {
        ListCell<String> cell = new ListCell<>();
        Navigator navigator = Navigator.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();

        cell.textProperty().bind(cell.itemProperty());

        cell.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                ListCell source = (ListCell) event.getSource();

                String path = source.getText();

                if (fileHandler.isDirectory(path)) {
                    navigator.open(path);

                    this.update();
                } else {
                    application.readArticle(path);
                }

                event.consume();
            }
        });

        cell.setOnContextMenuRequested((event) -> {
            ListCell source = (ListCell) event.getSource();

            if (source.getText() == null) {
                source.getContextMenu().hide();
                event.consume();
            }
        });

        ContextMenu contextMenu = new ContextMenu();

        MenuItem rename = new MenuItem("Rename");

        rename.setOnAction((event) -> {
            String path = cell.getText();

            application.renameArticle(path);
        });

        MenuItem delete = new MenuItem("Delete");

        delete.setOnAction((event) -> {
            String path = cell.getText();

            application.deletePath(path);
        });

        contextMenu.getItems().add(rename);
        contextMenu.getItems().add(delete);

        cell.setContextMenu(contextMenu);

        return cell;
    }
}
