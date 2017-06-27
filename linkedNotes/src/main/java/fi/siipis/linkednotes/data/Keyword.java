/**
 * Keyword
 *
 * Data class for article keywords.
 */
package fi.siipis.linkednotes.data;

import java.util.Objects;

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
     * @return Keyword name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Keyword name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Related article
     */
    public Article getArticle() {
        return article;
    }

    /**
     * @param article Related article
     */
    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Keyword other = (Keyword) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Keyword{" + "name=" + name + ", article=" + article + '}';
    }
}
