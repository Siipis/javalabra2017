package fi.siipis.linkednotes.data;

import fi.siipis.linkednotes.core.Navigator;
import fi.siipis.linkednotes.core.Utils;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Amalia Surakka
 */
public class LibraryTest {

    private Library library;

    @Before
    public void setUp() {
        this.library = Library.getInstance();
        
        this.library.empty();
    }

    private ArrayList<Article> getArticles() {
        Article a1 = new Article();
        a1.setContent("Article 1");

        Article a2 = new Article();
        a2.setContent("Article 2");

        return new ArrayList<Article>() {
            {
                add(a1);
                add(a2);
            }
        };
    }

    private ArrayList<Keyword> getKeywords() {
        Article a = new Article();

        Keyword k1 = new Keyword("Keyword 1", a);

        Keyword k2 = new Keyword("Keyword 2", a);

        Keyword k3 = new Keyword("Keyword 3", a);

        return new ArrayList<Keyword>() {
            {
                add(k1);
                add(k2);
                add(k3);
            }
        };
    }

    private ArrayList<Occurrence> getOccurrences() {
        Article a = new Article();
        Keyword k = new Keyword("Keyword", a);

        Occurrence o1 = new Occurrence(k, a, 0);

        Occurrence o2 = new Occurrence(k, a, 5);

        Occurrence o3 = new Occurrence(k, a, 10);

        return new ArrayList<Occurrence>() {
            {
                add(o1);
                add(o2);
                add(o3);
            }
        };
    }

    @Test
    public void testSetArticles() {
        this.library.setArticles(getArticles());

        assertSame(this.library.getArticles().size(), 2);
    }

    @Test
    public void testAddArticle() {
        ArrayList<Article> articles = this.getArticles();
        
        this.library.setArticles(articles);
        
        this.library.addArticle(new Article());
        
        assertSame(this.library.getArticles().size(), 3);
        
        this.library.addArticle(articles.get(0));

        assertSame(this.library.getArticles().size(), 3); // Don't allow duplicates
    }

    @Test
    public void testSetKeywords() {
        Article a = new Article();

        Keyword k1 = new Keyword("Keyword 1", a);

        Keyword k2 = new Keyword("Keyword 2", a);

        Keyword k3 = new Keyword("Keyword 3", a);

        this.library.setKeywords(new ArrayList<Keyword>() {
            {
                add(k1);
                add(k2);
                add(k3);
            }
        });

        assertSame(this.library.getKeywords().size(), 3);
    }

    @Test
    public void testSetOccurrences() {
        Article a = new Article();
        Keyword k = new Keyword("Keyword", a);

        Occurrence o1 = new Occurrence(k, a, 0);

        Occurrence o2 = new Occurrence(k, a, 5);

        Occurrence o3 = new Occurrence(k, a, 10);

        this.library.setOccurrences(new ArrayList<Occurrence>() {
            {
                add(o1);
                add(o2);
                add(o3);
            }
        });

        assertSame(this.library.getOccurrences().size(), 3);
    }
    
    @Test
    public void testSyncedLibrary() {
        Navigator.getInstance().setRootPath(Utils.testRootPath);
        
        this.library.sync();
        
        assertSame(4, this.library.getArticles().size());
        assertSame(6, this.library.getKeywords().size());
        assertSame(2, this.library.getOccurrences().size());
    }
    
    @Test
    public void testLibrarySyncConsistency() {
        Navigator.getInstance().setRootPath(Utils.testRootPath);
        
        this.library.sync();

        this.library.sync();

        this.library.sync();

        this.library.sync();
        
        assertSame(4, this.library.getArticles().size());
        assertSame(5, this.library.getKeywords().size());
        assertSame(2, this.library.getOccurrences().size());        
    }
}
