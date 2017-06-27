/**
 * Split Map
 *
 * Parser class for transporting data to the view.
 * Converts an article into strings and keywords.
 */
package fi.siipis.linkednotes.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Amalia Surakka
 */
public class SplitMap {

    private Article article;

    private Library library;

    private ArrayList<Object> parts;

    public SplitMap(Article article) {
        this.article = article;
        this.library = Library.getInstance();
        this.parts = new ArrayList<>();

        this.initParts();
    }

    /**
     * @return Associated article
     */
    public Article getArticle() {
        return this.article;
    }

    /**
     * Get the SplitMap parts
     *
     * @return List of strings and keywords
     */
    public ArrayList<Object> parts() {
        return parts;
    }

    /**
     * Initialise the SplitMap
     */
    private void initParts() {
        String content = this.article.getContent();
        HashMap<String, Keyword> keywords = this.getKeywords();

        if (keywords.isEmpty()) {
            parts.add(content); // If no keywords exist, use the full content

            return;
        }

        // Split the content where a keyword occurs
        String[] split = content.split(this.getPattern());

        for (int i = 0; i < split.length; i++) {
            String partial = split[i];
            String keyword = partial.trim().replaceAll("\\W", "");

            // Is the item a keyword?
            if (keywords.containsKey(keyword.toLowerCase())) {
                String before = partial.substring(0, partial.indexOf(keyword));
                String after = partial.substring(before.length() + keyword.length());

                if (before.length() > 0) {
                    parts.add(before);
                }

                String remnant = partial.substring(before.length(), partial.length() - after.length());

                parts.add(new Keyword(remnant, keywords.get(keyword.toLowerCase()).getArticle())); // Preserves font case, but removes punctuation

                if (after.length() > 0) {
                    parts.add(after);
                }
            } else {
                // Add non-keyword strings as such
                parts.add(partial);
            }
        }
    }

    /**
     * @return Regex pattern for article splitting
     */
    private String getPattern() {
        String pattern = "";

        for (Keyword k : library.getKeywords(article)) {
            if (!pattern.isEmpty()) {
                pattern += "|"; // Add OR sign to regex
            }

            String keywordPattern = "(\\W|^)(" + k.getName() + ")(\\W|$)";

            pattern += "(?=(" + keywordPattern + "))|(?<=(" + keywordPattern + "))"; // Include keywords in results
        }

        if (pattern.isEmpty()) {
            return "";
        }

        return "(?i)" + pattern;
    }

    /**
     * @return List of keywords in article
     */
    private HashMap<String, Keyword> getKeywords() {
        HashMap<String, Keyword> keywords = new HashMap<>();

        for (Keyword k : this.library.getKeywords(article)) {
            keywords.put(k.getName(), k);
        }

        return keywords;
    }
}
