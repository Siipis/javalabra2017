/**
 * Article Button
 * 
 * Data abstraction class for toggling editing/reading.
 * Extends the default button behaviour for buttons.
 */
package fi.siipis.linkednotes.ui.elements;

import fi.siipis.linkednotes.data.Article;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 *
 * @author Amalia Surakka
 */
public class ArticleButton extends Button {
    
    private Article article;

    public ArticleButton() {
    }

    public ArticleButton(Article article) {
        super();
        this.article = article;
    }
    
    public ArticleButton(String text) {
        super(text);
    }

    public ArticleButton(String text, Article article) {
        super(text);
        this.article = article;
    }
    
    public ArticleButton(String text, Node graphic) {
        super(text, graphic);
    }

    /**
     * @return Associated article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * @param article Associated article
     */
    public void setArticle(Article article) {
        this.article = article;
    }
}
