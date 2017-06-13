/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.siipis.linkednotes.data;

import fi.siipis.linkednotes.core.Navigator;
import fi.siipis.linkednotes.core.Utils;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Ami
 */
public class SplitMapTest {

    private Library library;

    private Article article;

    @Before
    public void setUp() {
        Navigator navigator = Navigator.getInstance();
        navigator.setRootPath(Utils.testRootPath);

        this.library = Library.getInstance();

        this.library.sync();

        this.article = this.library.findArticle(navigator.getFullPath("icecream/chocolate.txt"));
    }

    @Test
    public void testSplitLength() {
        SplitMap splitMap = new SplitMap(article);

        assertSame(3, splitMap.parts().size());
    }

    @Test
    public void testSplitItems() {
        SplitMap splitMap = new SplitMap(article);

        ArrayList<Object> parts = splitMap.parts();

        assertEquals("Chocolate does not contain ", parts.get(0));
        assertEquals(Keyword.class, parts.get(1).getClass());
        assertEquals(".", parts.get(2));

        Keyword k = (Keyword) parts.get(1);

        assertEquals("caramel", k.getName());
    }

    @Test
    public void testMultilineSplit() {
        Article article = new Article();
        article.setContent("Do you like cherry? What about apples? \n\nChocolate then?");
        article.setFilepath("icecream/text.txt");

        SplitMap splitMap = new SplitMap(article);

        ArrayList<Object> parts = splitMap.parts();

        assertSame(5, parts.size());
        assertEquals("Do you like ", splitMap.parts().get(0));
        assertEquals("? What about apples? \n\n", splitMap.parts().get(2));
        assertEquals(" then?", splitMap.parts().get(4));
    }
}
