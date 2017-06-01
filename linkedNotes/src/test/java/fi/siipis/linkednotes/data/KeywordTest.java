/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.siipis.linkednotes.data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ami
 */
public class KeywordTest {

    private Article article;

    @Before
    public void setUp() {
        article = new Article();
    }

    @Test
    public void testName() {
        Keyword keyword = new Keyword("foo", article);
        
        assertEquals("foo", keyword.getName());
    }

    @Test
    public void testNameCase() {
        Keyword keyword = new Keyword("FOO", article);
        
        assertEquals("foo", keyword.getName());
    }

    @Test
    public void testMalformedName() {
        Keyword keyword = new Keyword("FO oo    ", article);
        
        assertEquals("fo oo", keyword.getName());
    }
}
