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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name.trim();
        name = name.toLowerCase();

        this.name = name;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
