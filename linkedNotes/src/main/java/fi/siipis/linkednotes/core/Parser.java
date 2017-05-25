package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.data.Keyword;
import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.data.Occurrence;
import java.util.ArrayList;

/**
 *
 * @author Amalia Surakka
 */
public class Parser {

    /**
     * Convert an article object into plain text
     *
     * @param article
     * @return
     */
    public String toFile(Article article) {
        // TODO: coming soon
        return "";
    }

    /**
     * Convert a plain text file into an article object
     *
     * @param content
     * @return
     */
    public Article toArticle(String content) {
        // TODO: coming soon
        return new Article("", "", "");
    }

    /**
     * Convert a string into a keyword
     *
     * @param keyword
     * @param article
     * @return
     */
    public Keyword toKeyword(String keyword, Article article) {
        // TODO: add string sanitation
        return new Keyword(keyword, article);
    }

    /**
     * Fetch all keyword occurrences in an article
     *
     * @param article Article to create occurrences for
     * @param library
     * @return
     */
    public ArrayList<Occurrence> toOccurrences(Article article, Library library) {
        // TODO: coming soon
        ArrayList<Occurrence> occurrences = new ArrayList<>();

        return occurrences;
    }
}
