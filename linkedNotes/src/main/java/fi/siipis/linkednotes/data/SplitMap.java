/**
 * Split Map
 * 
 * Helper class for transporting data to the view.
 * Converts an article into strings and keywords.
 */
package fi.siipis.linkednotes.data;

import fi.siipis.linkednotes.core.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Amalia Surakka
 */
public class SplitMap {

    private Article article;

    private Library library;

    private ArrayList<Occurrence> occurrences;

    public SplitMap(Article article) {
        this.article = article;
        this.library = Library.getInstance();
        this.occurrences = new ArrayList<>(library.getOccurrences(article));

        if (!library.getArticles().contains(article)) {
            this.occurrences = Parser.getInstance().toOccurrences(article);
        }
        
        this.init();
    }

    private void init() {
        Collections.sort(this.occurrences, (Occurrence o1, Occurrence o2) -> o1.getPosition() - o2.getPosition());
    }
    
    /**
     * @return Associated article
     */
    public Article getArticle() {
        return this.article;
    }

    /**
     * Transform the article into strings and keywords
     * making it possible to render the file contents in the view
     * 
     * @return List of strings and keywords
     */
    // TODO: replace by implementing Iterable
    public ArrayList<Object> parts() {
        ArrayList<Object> split = new ArrayList<>();

        String content = this.article.getContent();
        int previousPosition = 0;

        // Split the content into text and keywords
        for (Occurrence o : this.occurrences) {
            int oPos = o.getPosition();

            String partial = content.substring(previousPosition, oPos);
            
            if (content.length() > oPos && content.substring(oPos, oPos + 1).equals(" ")) {
                // Ensures spaces are preserved during splitting
                partial += " ";
            }
                        
            String combine = partial + o.getKeyword().getName();
            
            previousPosition = previousPosition + combine.length();

            split.add(partial);
            split.add(o.getKeyword());
        }

        // Include the tail of the content where no keywords are left
        if (previousPosition < content.length()) {
            split.add(content.substring(previousPosition));
        }

        return split;

    }
}
