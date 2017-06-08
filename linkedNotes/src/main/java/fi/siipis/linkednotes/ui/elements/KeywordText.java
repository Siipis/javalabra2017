package fi.siipis.linkednotes.ui.elements;

import fi.siipis.linkednotes.data.Keyword;
import javafx.scene.text.Text;

/**
 *
 * @author Amalia Surakka
 */
public class KeywordText extends Text {

    private Keyword keyword;

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Keyword getKeyword() {
        return this.keyword;
    }
}
