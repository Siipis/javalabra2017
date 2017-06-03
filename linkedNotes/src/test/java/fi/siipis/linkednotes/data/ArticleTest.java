package fi.siipis.linkednotes.data;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ami
 */
public class ArticleTest {
    
    @Test
    public void testArticleNotEquals() {
        Article article1 = new Article();
        article1.setContent("Article");
        article1.setFilepath("foo.txt");

        Article article2 = new Article();
        article2.setContent("Article");
        article2.setFilepath("foo2.txt");
        
        assertNotEquals(article1, article2);
    }

    @Test
    public void testArticleEquals() {
        Article article1 = new Article();
        article1.setContent("Article");
        article1.setFilepath("foo.txt");

        Article article2 = new Article();
        article2.setContent("Article");
        article2.setFilepath("foo.txt");
        
        assertEquals(article1, article2);
    }
}
