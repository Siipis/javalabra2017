package fi.siipis.linkednotes.data;

import fi.siipis.linkednotes.core.Utils;
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

    public Article() {
        this.keywords = new ArrayList<>();
        this.saved = new Date();
        this.edited = new Date();
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        filepath = Utils.normalisePath(filepath);

        this.filepath = filepath;
    }

    public String getName() {
        return name;
    }
    
    /**
     * Return the partial file name, e.g. foo/bar.txt => bar
     * 
     * @return 
     */
    public String getPlainName() {
        String n = name;
        
        if (n == null || n.isEmpty()) {
            return "";
        }
        
        n = n.toLowerCase();
        
        int slashIndex = n.lastIndexOf("/");
        
        // If no slashes exist, ignore this
        if (slashIndex < 0) {
            slashIndex = 0;
        }
        
        int dotIndex = n.lastIndexOf(".");
        
        // If no dots exist, ignore this
        if (dotIndex < 0) {
            dotIndex = n.length();
        }
        
        n = n.substring(slashIndex, dotIndex);
        
        return n;
    }
    
    public void setName(String name) {
        name = Utils.normalisePath(name);

        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content.trim();
    }

    public String getKeywordsAsString() {
        String keywordString = "";

        for (Keyword k : keywords) {
            if (!keywordString.isEmpty()) {
                keywordString += ", ";
            }

            keywordString += k.getName();
        }
        
        if (keywordString.isEmpty()) {
            keywordString = getPlainName();
        }

        return keywordString;
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

    public void touchSaved() {
        this.saved = new Date();
    }

    public Date getEdited() {
        return edited;
    }

    public void setEdited(Date edited) {
        this.edited = edited;
    }

    public void touchEdited() {
        this.edited = new Date();
    }

    @Override
    public String toString() {
        return "Article{" + "filepath=" + filepath + ", name=" + name + ", content=" + content + ", keywords=" + getKeywordsAsString() + ", saved=" + saved + ", edited=" + edited + '}';
    }
}
