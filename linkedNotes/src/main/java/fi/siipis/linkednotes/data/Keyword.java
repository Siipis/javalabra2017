package fi.siipis.linkednotes.data;

/**
 *
 * @author Amalia Surakka
 */
public class Keyword {
    private String keyword;
    
    private Article article;

    public Keyword(String keyword, Article article) {
        this.keyword = keyword;
        this.article = article;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
