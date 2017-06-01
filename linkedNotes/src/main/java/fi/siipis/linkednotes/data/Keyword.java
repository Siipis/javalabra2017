package fi.siipis.linkednotes.data;

/**
 *
 * @author Amalia Surakka
 */
public class Keyword {

    private String name;

    private Article article;

    public Keyword(String name, Article article) {
        this.setName(name);
        this.setArticle(article);
    }

    /**
     * Get keyword name
     * 
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Set keyword name
     * 
     * @param name 
     */
    public void setName(String name) {
        name = name.trim();
        name = name.toLowerCase();

        this.name = name;
    }

    /**
     * Get related article
     * 
     * @return 
     */
    public Article getArticle() {
        return article;
    }

    /**
     * Set related article
     * 
     * @param article 
     */
    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Keyword{" + "name=" + name + ", article=" + article + '}';
    }
}
