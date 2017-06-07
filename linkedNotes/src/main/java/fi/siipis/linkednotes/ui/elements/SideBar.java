package fi.siipis.linkednotes.ui.elements;

import fi.siipis.linkednotes.core.FileHandler;
import fi.siipis.linkednotes.core.Navigator;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

/**
 *
 * @author Amalia Surakka
 */
public class SideBar extends Container {

    private ListView<String> listView = new ListView<>();

    public SideBar() {
        this.init();
    }

    private void init() {
        this.add(listView);
    }

    public void update() {
        Navigator navigator = Navigator.getInstance();

        listView.getItems().clear();

        if (!navigator.currentIsRoot()) {
            listView.getItems().add("..");
        }

        listView.getItems().addAll(navigator.list());

        listView.setCellFactory(lv -> {
            return createCell();
        });
    }

    private ListCell<String> createCell() {
        ListCell<String> cell = new ListCell<>();
        Navigator navigator = Navigator.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();

        cell.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                ListCell source = (ListCell) event.getSource();

                String path = source.getText();
                
                if (fileHandler.isDirectory(path)) {
                    navigator.open(path);

                    this.update();
                } else {
                    // TODO
                    System.out.println("Open file: " + path);
                }

                event.consume();
            }
        });

        ContextMenu contextMenu = new ContextMenu(
                new MenuItem("Rename"),
                new MenuItem("Delete")
        );

        cell.textProperty().bind(cell.itemProperty());

        cell.setContextMenu(contextMenu);

        return cell;
    }
}
