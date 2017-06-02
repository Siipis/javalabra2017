package fi.siipis.linkednotes;

import fi.siipis.linkednotes.core.Controller;
import fi.siipis.linkednotes.ui.UI;

/**
 *
 * @author Amalia Surakka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        
        controller.build();
    }
    
}
