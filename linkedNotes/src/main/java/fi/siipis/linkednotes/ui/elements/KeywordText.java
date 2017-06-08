/**
 * Text Keyword
 *
 * Data abstraction class for text keywords.
 * Extends the default text behaviour for keywords.
 */
package fi.siipis.linkednotes.ui.elements;

import fi.siipis.linkednotes.data.Keyword;
import javafx.scene.text.Text;

/**
 *
 * @author Amalia Surakka
 */
public class KeywordText extends Text {

    private Keyword keyword;

    /**
     * @param keyword Keyword associated with the text
     */
    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    /**
     * @return Keyword associated with the text
     */
    public Keyword getKeyword() {
        return this.keyword;
    }
}
