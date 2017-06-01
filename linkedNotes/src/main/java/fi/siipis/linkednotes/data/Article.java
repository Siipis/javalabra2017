package fi.siipis.linkednotes.data;

import fi.siipis.linkednotes.core.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Amalia Surakka
 */
public class Article {

    private String filepath;

    private String content;

    private ArrayList<Keyword> keywords;

    private Date saved;

    private Date edited;

    /**
     * Constructor
     */
    public Article() {
        this.keywords = new ArrayList<>();
        this.saved = new Date();
        this.edited = new Date();
    }

    /**
     * Get relative file path
     * 
     * @return 
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Set relative file path
     * @param filepath 
     */
    public void setFilepath(String filepath) {
        filepath = Utils.normalisePath(filepath);

        this.filepath = filepath;
    }

    /**
     * Get file name, e.g. foo/bar.txt => bar.txt
     * 
     * @return 
     */
    public String getName() {
        if (filepath == null || filepath.isEmpty()) {
            return "";
        }
        
        int slashIndex = filepath.lastIndexOf(File.separator);
        
        // If no slashes exist, ignore this
        if (slashIndex < 0) {
            return filepath;
        }
        
        int startAt = slashIndex + File.separator.length();

        return filepath.substring(startAt);
    }
    
    /**
     * Get partial file name, e.g. foo/bar.txt => bar
     * 
     * @return 
     */
    public String getPlainName() {
        String n = getName();
        
        if (n == null || n.isEmpty()) {
            return "";
        }
        
        n = n.toLowerCase();
        
        int dotIndex = n.lastIndexOf(".");
        
        // If no dots exist, ignore this
        if (dotIndex < 0) {
            dotIndex = n.length();
        }
        
        n = n.substring(0, dotIndex);
        
        return n;
    }
    
    /**
     * Get article content
     * 
     * @return 
     */
    public String getContent() {
        return content;
    }

    /**
     * Set article content
     * 
     * @param content 
     */
    public void setContent(String content) {
        this.content = content.trim();
    }

    /**
     * Get keywords as comma separated string
     * 
     * @return 
     */
    public String getKeywordsAsString() {
        String keywordString = "";

        for (Keyword k : keywords) {
            if (!keywordString.isEmpty()) {
                keywordString += ", ";
            }

            keywordString += k.getName();
        }
        
        if (keywordString.isEmpty()) {
            // If no keywords exist, use the article name as a keyword
            keywordString = getPlainName();
        }

        return keywordString;
    }

    /**
     * Get list of keywords
     * 
     * @return 
     */
    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    /**
     * Set list of keywords
     * 
     * @param keywords 
     */
    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }

    /**
     * Get time saved
     * 
     * @return 
     */
    public Date getSaved() {
        return saved;
    }

    /**
     * Set time saved
     * 
     * @param saved 
     */
    public void setSaved(Date saved) {
        this.saved = saved;
    }

    /**
     * Set time saved to now
     */
    public void touchSaved() {
        this.saved = new Date();
    }

    /**
     * Get time edited
     * 
     * @return 
     */
    public Date getEdited() {
        return edited;
    }

    /**
     * Set time edited
     * 
     * @param edited 
     */
    public void setEdited(Date edited) {
        this.edited = edited;
    }

    /**
     * Set time edited to 
     */
    public void touchEdited() {
        this.edited = new Date();
    }

    @Override
    public String toString() {
        return "Article{" + "filepath=" + filepath + ", content=" + content + ", keywords=" + getKeywordsAsString() + ", saved=" + saved + ", edited=" + edited + '}';
    }    
}
