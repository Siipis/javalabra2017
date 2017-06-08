package fi.siipis.linkednotes.ui.view;

import fi.siipis.linkednotes.core.Application;
import fi.siipis.linkednotes.ui.elements.Frame;
import fi.siipis.linkednotes.ui.elements.SideBar;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 *
 * @author Amalia Surakka
 */
public abstract class View {

    private Application application;
    
    private Frame frame;

    private SideBar sideBar;

    private Scene scene;

    public View(Application application) {
        this.application = application;
        
        this.frame = new Frame();

        this.sideBar = new SideBar(application);
        this.sideBar.update();
        this.frame.setLeft(sideBar);

        this.scene = new Scene(frame);

        this.init();
    }

    public Frame getFrame() {
        return this.frame;
    }

    public Scene get() {
        return this.scene;
    }

    public abstract void init();

    public void setContent(Node node) {
        this.frame.setCenter(node);
    }
}
