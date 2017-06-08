package fi.siipis.linkednotes.ui.view;

import fi.siipis.linkednotes.core.Application;
import fi.siipis.linkednotes.data.Article;
import javafx.scene.text.Text;

/**
 *
 * @author Amalia Surakka
 */
public class ReaderView extends View {

    private Text text;
    
    public ReaderView(Application application) {
        super(application);
    }

    public void init() {
        text = new Text();

        this.setContent(text);
    }
    
    public void setArticle(Article article) {
        text.setText(article.getContent());
    }
}
