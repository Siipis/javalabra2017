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
    
    public static String separator = "\r\n";

    /**
     * Convert an article object into plain text
     *
     * @param article
     * @return
     */
    public String toFile(Article article) {
        String content = article.getContent();
        
        String keywords = article.getKeywordsAsString(); 
        
        if (keywords.isEmpty()) {
            return content;
        }
        
        return "[" + keywords + "]" + separator + content;
    }

    /**
     * Convert a plain text file into an article object
     *
     * @param content
     * @return
     */
    public Article toArticle(String content) {
        Article article = new Article();

        if (this.hasKeywords(content)) {
            article.setKeywords(this.toKeywords(content, article));
        }

        article.setContent(this.toContent(content));

        return article;
    }
    
    /**
     * Return true if article contains a keyword line
     * 
     * @param content
     * @return 
     */
    private boolean hasKeywords(String content) {
        String[] lines = content.split(separator);

        // If file starts with [key, words]
        return lines[0].trim().startsWith("[") && lines[0].trim().endsWith("]");
    }

    /**
     * Parse the keywords from article contents
     * 
     * @param content
     * @param article
     * @return 
     */
    private ArrayList<Keyword> toKeywords(String content, Article article) {
        ArrayList<Keyword> keywords = new ArrayList<>();

        if (this.hasKeywords(content)) {
            String[] lines = content.split(separator);

            String firstLine = lines[0].trim();

            String keywordString = firstLine.substring(1, firstLine.length() - 1);

            for (String k : keywordString.split(",")) {
                Keyword keyword = this.toKeyword(k, article);

                keywords.add(keyword);
            }
        }

        return keywords;
    }

    /**
     * Separate the article text from keywords
     * 
     * @param content
     * @return 
     */
    private String toContent(String content) {
        if (!this.hasKeywords(content)) {
            return content;
        }

        // Parse file contents
        String[] lines = content.split(separator);

        String text = "";
        for (int i = 1; i < lines.length; i++) {
            text += lines[i] + separator;
        }

        return text;
    }

    /**
     * Convert a string into a keyword
     *
     * @param keyword
     * @param article
     * @return
     */
    public Keyword toKeyword(String keyword, Article article) {
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
