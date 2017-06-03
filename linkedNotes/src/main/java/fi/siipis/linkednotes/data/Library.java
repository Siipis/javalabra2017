package fi.siipis.linkednotes.data;

import java.util.ArrayList;

/**
 *
 * @author Amalia Surakka
 */
public class Library {
    
    private ArrayList<Article> articles;
    
    private ArrayList<Keyword> keywords;
    
    private ArrayList<Occurrence> occurrences;
    
    public Library() {
        articles = new ArrayList<>();
        keywords = new ArrayList<>();
        occurrences = new ArrayList<>();
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
}
