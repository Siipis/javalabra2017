package fi.siipis.linkednotes.ui.elements;

/**
 *
 * @author Amalia Surakka
 */
public class SideBar extends Container {
    
    public SideBar() {
        this.init();
    }
    
    private void init() {
        this.add(new MenuItem("apple.txt"));
        this.add(new MenuItem("orange.txt"));
        this.add(new MenuItem("cherry.txt"));
    }
}
