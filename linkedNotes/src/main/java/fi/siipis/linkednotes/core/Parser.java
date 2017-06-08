package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.*;
import java.util.ArrayList;
import java.io.File;

/**
 *
 * @author Amalia Surakka
 */
public class Parser {

    public static String separator = "\r\n";

    private FileHandler fileHandler;

    private Parser() {
        this.fileHandler = fileHandler.getInstance();
    }

    public static Parser getInstance() {
        return Factory.INSTANCE;
    }

    private static class Factory {

        private static final Parser INSTANCE = new Parser();
    }

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
     * Convert a plain text file into an article
     *
     * @param content
     * @return
     */
    public Article toArticle(String content) {
        Article article = new Article();

        article.setKeywords(this.toKeywords(content, article));

        article.setContent(this.toContent(content));

        return article;
    }

    /**
     * Convert a file into an article
     *
     * @param file
     * @return
     */
    public Article toArticle(File file) {
        String content = fileHandler.readFile(file);

        return toArticle(content);
    }

    /**
     * Return true if article contains a keyword line
     *
     * @param content
     * @return
     */
    private boolean hasKeywords(String content) {
        if (content == null) {
            return false;
        }

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
        ArrayList<Occurrence> occurrences = new ArrayList<>();

        for (Article a : library.getArticles()) {
            for (Keyword k : library.getKeywords()) {
                if (a.equals(k.getArticle())) {
                    continue; // Don't cross-reference self
                }

                occurrences.addAll(this.toOccurrences(a, k));
            }
        }

        return occurrences;
    }

    /**
     * Fetch all occurrences of a given keyword in an article
     *
     * @param article
     * @param keyword
     * @return
     */
    public ArrayList<Occurrence> toOccurrences(Article article, Keyword keyword) {
        ArrayList<Occurrence> occurrences = new ArrayList<>();

        String find = keyword.getName();
        String content = article.getContent();

        if (content.isEmpty() && !content.contains(find)) {
            return occurrences;
        }

        String[] split = content.split("(?i)[\\W](" + find + ")\\W");

        if (content.toLowerCase().startsWith(find)) {
            occurrences.add(new Occurrence(keyword, article, 0));
        }

        if (split.length > 1) {
            for (int i = 0; i < split.length; i++) {
                if (i + 1 == split.length) {
                    continue; // Don't treat the end of the array as a position
                }

                int pos = split[i].length() + 1;

                occurrences.add(new Occurrence(keyword, article, pos));
            }
        } else {
            // In one sentence splits, the keyword might be right at the end
            // eg. "sweet" => Apples are sweet.
            if (content.length() > split[0].length()) {
                occurrences.add(new Occurrence(keyword, article, split[0].length()));
            }
        }
        
        return occurrences;
    }
}
