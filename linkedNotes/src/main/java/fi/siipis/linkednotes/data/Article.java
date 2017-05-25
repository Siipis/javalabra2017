package fi.siipis.linkednotes.data;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Amalia Surakka
 */
public class Article {
    private String filepath;
    
    private String name;
    
    private String content;
    
    private ArrayList<Keyword> keywords;
    
    private Date saved;
    
    private Date edited;

    public Article(String filepath, String name, String content) {
        this.filepath = filepath;
        this.name = name;
        this.content = content;
        this.keywords = new ArrayList<>();
        this.saved = new Date();
        this.edited = new Date();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Date getSaved() {
        return saved;
    }

    public void setSaved(Date saved) {
        this.saved = saved;
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited(Date edited) {
        this.edited = edited;
    }
}
