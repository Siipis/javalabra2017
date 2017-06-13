/**
 * View Frame
 * 
 * Builder for the main UI frame.
 */
package fi.siipis.linkednotes.ui.view;

import fi.siipis.linkednotes.ui.View;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 *
 * @author Amalia Surakka
 */
public class ViewFrame extends BorderPane {
    
    private View view;
    
    private VBox navBar;
    
    /**
     * Constructor
     * 
     * @param view View container
     */
    public ViewFrame(View view) {
        this.view = view;
        
        this.init();
    }
    
    /**
     * Initialise the class
     */
    private void init() {
        this.navBar = new VBox();
        
        navBar.setId("navBar");

        this.setLeft(navBar);
        
        // Create menu bar
        MenuBar mainMenu = new MenuBar();
        
        Menu file = new Menu("File");
        
        // Create New... menu
        Menu newMenu = new Menu("New...");
        
        MenuItem newArticle = new MenuItem("Article");
        newArticle.setOnAction((event) -> {
            view.getApplication().createArticle();
        });

        MenuItem newDirectory = new MenuItem("Directory");
        newDirectory.setOnAction((event) -> {
            view.getApplication().createDirectory();
        });

        newMenu.getItems().add(newArticle);
        newMenu.getItems().add(newDirectory);
        
        file.getItems().add(newMenu);
        
        // Create exit option
        file.getItems().add(new SeparatorMenuItem());

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((event) -> {
            view.getApplication().closeApplication();
        });
        
        file.getItems().add(exit);
        
        mainMenu.getMenus().add(file);
        
        // Attach main menu
        this.setTop(mainMenu);
    }
    
    /**
     * @return Navigation bar
     */
    public VBox getNavBar() {
        return this.navBar;
    }
} 
