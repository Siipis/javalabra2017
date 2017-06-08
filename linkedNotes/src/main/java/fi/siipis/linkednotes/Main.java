package fi.siipis.linkednotes;

import fi.siipis.linkednotes.ui.Application;

/**
 *
 * @author Amalia Surakka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Application application = new Application();

            application.launch(Application.class);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
