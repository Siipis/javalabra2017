package fi.siipis.linkednotes.data;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Amalia Surakka
 */
public class OccurrenceTest {
    
    @Test
    public void testOccurrenceNotEquals() {
        Article a = new Article();
        Keyword k = new Keyword("foo", a);

        Occurrence o1 = new Occurrence(k, a, 0);
        Occurrence o2 = new Occurrence(k, a, 5);
        
        assertNotEquals(o1, o2);
    }
    
    @Test
    public void testOccurrenceEquals() {
        Article a1 = new Article();
        Article a2 = new Article();
        Keyword k = new Keyword("foo", a1);

        Occurrence o1 = new Occurrence(k, a1, 0);
        Occurrence o2 = new Occurrence(k, a2, 0);
        
        assertEquals(o1, o2);
    }
}
