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

    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<Occurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(ArrayList<Occurrence> occurrences) {
        this.occurrences = occurrences;
    }
}
