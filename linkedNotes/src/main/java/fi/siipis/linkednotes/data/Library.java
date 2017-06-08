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
    
    private ArrayList<Occurrence> occurrences;
    
    private Library() {
        articles = new ArrayList<>();
        keywords = new ArrayList<>();
        occurrences = new ArrayList<>();
    }

    public static Library getInstance() {
        return Factory.INSTANCE;
    }

    private static class Factory {
        private static final Library INSTANCE = new Library();
    }

    public void empty() {
        this.articles.clear();
        this.keywords.clear();
        this.occurrences.clear();
    }
    
        /**
     * Fetch all file data
     */
    public void sync() {
        this.empty();
        
        Navigator navigator = Navigator.getInstance();
        Parser parser = Parser.getInstance();
        
        String currentPath = navigator.getCurrentPath();
        
        this.addDirToLibrary(".");
        
        for (Article article : this.getArticles()) {
            this.addOccurrences(parser.toOccurrences(article, this));
        }
        
        navigator.setCurrentPath(currentPath);
    }
    
    /**
     * Read a directory into articles
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
    
    public Article findArticle(String filePath) {
        if (articles.isEmpty()) {
            return null;
        }
        
        Article finder = new Article();
        finder.setFilepath(filePath);
        
        return articles.get(articles.indexOf(finder)); // This works because articles are equated by file path
    }
    
    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
    
    public void addArticle(Article article) {
        if (articles.contains(article)) {
            return;
        }
        
        articles.add(article);
    }
    
    public void addArticles(ArrayList<Article> articles) {
        ArrayList<Article> unique = new ArrayList<>(articles);
        
        unique.removeIf(o -> this.articles.contains(o));
        
        this.articles.addAll(unique);
    }

    public void removeArticle(Article article) {
        articles.remove(article);
    }

    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }
    
    public void addKeyword(Keyword keyword) {
        if (keywords.contains(keyword)) {
            return;
        }
        
        keywords.add(keyword);
    }

    public void addKeywords(ArrayList<Keyword> keywords) {
        ArrayList<Keyword> unique = new ArrayList<>(keywords);
        
        unique.removeIf(o -> this.keywords.contains(o));
        
        this.keywords.addAll(unique);
    }

    public void removeKeyword(Keyword keyword) {
        keywords.remove(keyword);
    }
    
    public ArrayList<Occurrence> getOccurrences() {
        return occurrences;
    }
    
    public ArrayList<Occurrence> getOccurrences(Article article) {
        ArrayList<Occurrence> occurrences = new ArrayList<>(this.getOccurrences());
        
        occurrences.removeIf(o -> {
            return !o.getArticle().equals(article);
        });
        
        return occurrences;
    }

    public void setOccurrences(ArrayList<Occurrence> occurrences) {
        this.occurrences = occurrences;
    }
    
    public void addOccurrence(Occurrence occurrence) {
        if (occurrences.contains(occurrence)) {
            return;
        }
        
        occurrences.add(occurrence);
    }
    
    public void addOccurrences(ArrayList<Occurrence> occurrences) {
        ArrayList<Occurrence> unique = new ArrayList<>(occurrences);
        
        unique.removeIf(o -> this.occurrences.contains(o));
        
        this.occurrences.addAll(unique);
    }
        
    public void removeOccurrence(Occurrence occurrence) {
        occurrences.remove(occurrence);
    }

    @Override
    public String toString() {
        return "Library contains " + articles.size() + " articles, " + keywords.size() + " keywords, and " + occurrences.size() + " occurrences.";
    }
}
