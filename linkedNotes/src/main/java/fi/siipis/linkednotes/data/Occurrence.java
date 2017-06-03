package fi.siipis.linkednotes.data;

import java.util.Objects;

/**
 *
 * @author Amalia Surakka
 */
public class Occurrence {
    private Keyword keyword;
    
    private Article article;
    
    private int position;

    public Occurrence(Keyword keyword, Article article, int position) {
        this.keyword = keyword;
        this.article = article;
        this.position = position;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
        final Occurrence other = (Occurrence) obj;
        if (this.position != other.position) {
            return false;
        }
        if (!Objects.equals(this.keyword, other.keyword)) {
            return false;
        }
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Occurrence{" + "keyword=" + keyword.getName() + ", article=" + article.getPlainName() + ", position=" + position + '}';
    }
}
