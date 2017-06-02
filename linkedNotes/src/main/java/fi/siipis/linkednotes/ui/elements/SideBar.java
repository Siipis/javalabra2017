package fi.siipis.linkednotes.ui.elements;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Amalia Surakka
 */
public class SideBar extends Container {
    
    public SideBar() {
        this.init();
    }
    
    private void init() {
        ListView<String> listView = new ListView<>();
        
        listView.getItems().addAll(
                "apple.txt",
                "orange.txt",
                "cherry.txt"
        );
        
        listView.setCellFactory(lv -> {
            ListCell<String> cell = new ListCell<>();
            
            ContextMenu contextMenu = new ContextMenu(
                    new MenuItem("Rename"),
                    new MenuItem("Delete")
            );
            
            cell.textProperty().bind(cell.itemProperty());
            
            cell.setContextMenu(contextMenu);
            
            return cell;
        });
        
        this.add(listView);
    }

}
