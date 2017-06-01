/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.data.Keyword;
import java.util.ArrayList;
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
public class ParserTest {
    
    private Parser parser;

    @Before
    public void setUp() {
        parser = new Parser();
    }

    @Test
    public void testToKeyword() {
        Article article = new Article();

        Keyword keyword = parser.toKeyword("foo", article);
        
        assertNotNull(keyword);
        assertSame("foo", keyword.getName());
    }
    
    @Test
    public void testToFileWithoutKeywords() {
        Article article = new Article();
                
        article.setContent("Hello world!");
        
        String content = parser.toFile(article);
        
        assertSame("Hello world!", content);
    }

    @Test
    public void testToFileWithKeywords() {
        Article article = new Article();
                
        article.setContent("Hello world!");

        ArrayList<Keyword> keywords = new ArrayList<>();
        
        keywords.add(parser.toKeyword("foo", article));
        keywords.add(parser.toKeyword("bar", article));
        
        article.setKeywords(keywords);
        
        String content = parser.toFile(article);
        
        assertEquals(content, "[foo, bar]" + parser.separator + "Hello world!");
    }
    
    @Test
    public void testToArticleWithoutKeywords() {
        String content = "Hello world!";
        
        Article article = parser.toArticle(content);
        
        assertNotNull(article);
        assertSame(article.getKeywords().size(), 0);
        assertEquals(article.getContent(), "Hello world!");
    }
    
    @Test
    public void testToArticleWithKeywords() {
        String content = "[foo, bar] " + parser.separator + "Hello world!";
        
        Article article = parser.toArticle(content);
        
        assertNotNull(article);
        assertSame(article.getKeywords().size(), 2);
        assertEquals(article.getContent(), "Hello world!");
    }
}
