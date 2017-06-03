package fi.siipis.linkednotes.data;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Amalia Surakka
 */
public class KeywordTest {
    
    @Test
    public void testKeywordNotEquals() {
        Article a = new Article();
        
        Keyword k1 = new Keyword("foo", a);
        Keyword k2 = new Keyword("bar", a);
        
        assertNotEquals(k1, k2);
    }
    
    @Test
    public void testKeywordEquals() {
        Article a = new Article();
        
        Keyword k1 = new Keyword("foo", a);
        Keyword k2 = new Keyword("foo", a);
        
        assertEquals(k1, k2);
    }
}
