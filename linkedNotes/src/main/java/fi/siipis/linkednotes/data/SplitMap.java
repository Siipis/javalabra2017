package fi.siipis.linkednotes.data;

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

        this.init();
    }

    private void init() {
        Collections.sort(this.occurrences, (Occurrence o1, Occurrence o2) -> o1.getPosition() - o2.getPosition());
    }

    // TODO: replace by implementing Iterable
    public ArrayList<Object> parts() {
        ArrayList<Object> split = new ArrayList<>();

        String content = this.article.getContent();
        int previousPosition = 0;

        // Split the content into text and keywords
        for (Occurrence o : this.occurrences) {
            String partial = content.substring(previousPosition, o.getPosition() + 1);
                        
            String combine = partial + o.getKeyword().getName();
            
            previousPosition = combine.length();

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
