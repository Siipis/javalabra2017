/**
 * Library
 *
 * Data class for articles and their relationships.
 * The application shares a single synchronised library.
 */
package fi.siipis.linkednotes.data;

import fi.siipis.linkednotes.core.*;
import java.util.ArrayList;

/**
 *
 * @author Amalia Surakka
 */
public class Library {

    private ArrayList<Article> articles;

    private ArrayList<Keyword> keywords;

    private Article currentArticle;

    private Library() {
        articles = new ArrayList<>();
        keywords = new ArrayList<>();
    }

    /**
     * @return Singleton instance of class
     */
    public static Library getInstance() {
        return Factory.INSTANCE;
    }

    private static class Factory {

        private static final Library INSTANCE = new Library();
    }

    /**
     * Empty the library
     */
    public void empty() {
        this.articles.clear();
        this.keywords.clear();
    }

    /**
     * Synchronise the data with all file contents
     */
    public void sync() {
        this.empty();

        Navigator navigator = Navigator.getInstance();
        Parser parser = Parser.getInstance();

        String currentPath = navigator.getCurrentPath();

        this.addDirToLibrary(".");

        navigator.setCurrentPath(currentPath);
    }

    /**
     * Convert a directory into articles
     *
     * @param open Path to open
     */
    private void addDirToLibrary(String open) {
        Navigator navigator = Navigator.getInstance();
        FileHandler fileHandler = FileHandler.getInstance();
        Parser parser = Parser.getInstance();

        navigator.open(open);

        for (String path : navigator.list()) {
            if (fileHandler.isFile(path)) {
                String file = fileHandler.readFile(path);

                if (file != null) {
                    Article article = parser.toArticle(file);

                    article.setFilepath(path);

                    this.addArticle(article);

                    this.addKeywords(article.getKeywords());
                }
            } else if (fileHandler.isDirectory(path)) {
                this.addDirToLibrary(path);
            }
        }
    }

    /**
     * Look for an article in the library
     *
     * @param filePath Article path
     * @return Article if found
     */
    public Article findArticle(String filePath) {
        this.sync();

        if (articles.isEmpty()) {
            return null;
        }

        Article finder = new Article();
        finder.setFilepath(filePath);

        int found = articles.indexOf(finder);

        if (found == -1) {
            return null;
        }

        return articles.get(found); // This works because articles are equated by file path
    }

    /**
     * @return All articles
     */
    public ArrayList<Article> getArticles() {
        return articles;
    }

    /**
     * @param articles All articles
     */
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    /**
     * Add an article to the library
     *
     * @param article Article to add
     */
    public void addArticle(Article article) {
        if (articles.contains(article)) {
            return;
        }

        articles.add(article);
    }

    /**
     * Add multiple articles to the library
     *
     * @param articles Articles to add
     */
    public void addArticles(ArrayList<Article> articles) {
        ArrayList<Article> unique = new ArrayList<>(articles);

        unique.removeIf(o -> this.articles.contains(o));

        this.articles.addAll(unique);
    }

    /**
     * Remove an article from the library
     *
     * @param article Article to remove
     */
    public void removeArticle(Article article) {
        articles.remove(article);
    }

    /**
     * Get all keywords in all articles
     *
     * @return List of keywords
     */
    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    /**
     * Get all keywords in an article
     *
     * @param article Article to find keywords for
     * @return List of keywords in article
     */
    public ArrayList<Keyword> getKeywords(Article article) {
        ArrayList<Keyword> filtered = new ArrayList<>(keywords);

        filtered.removeIf((k) -> {
            if (article.equals(k.getArticle())) {
                return true; // Don't cross-reference self
            }

            if (!article.getParentPath().equals(k.getArticle().getParentPath())) {
                return true; // Only list occurrences within the same directory
            }

            return false;
        });

        return filtered;
    }

    /**
     * Set all keywords
     *
     * @param keywords List of keywords
     */
    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }

    /**
     * Add a keyword to the library
     *
     * @param keyword Keyword to add
     */
    public void addKeyword(Keyword keyword) {
        if (keywords.contains(keyword)) {
            return;
        }

        keywords.add(keyword);
    }

    /**
     * Add multiple keywords to the library
     *
     * @param keywords Keywords to add
     */
    public void addKeywords(ArrayList<Keyword> keywords) {
        ArrayList<Keyword> unique = new ArrayList<>(keywords);

        unique.removeIf(o -> this.keywords.contains(o));

        this.keywords.addAll(unique);
    }

    /**
     * Remove a keyword from the library
     *
     * @param keyword Keyword to remove
     */
    public void removeKeyword(Keyword keyword) {
        keywords.remove(keyword);
    }

    /**
     * @return Currently opened article
     */
    public Article getCurrentArticle() {
        return currentArticle;
    }

    /**
     * @param currentArticle Currently opened article
     */
    public void setCurrentArticle(Article currentArticle) {
        this.currentArticle = currentArticle;
    }

    @Override
    public String toString() {
        return "Library contains " + articles.size() + " articles, " + keywords.size() + " keywords.";
    }
}
