/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.siipis.linkednotes.data;

import java.io.File;
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
public class ArticleTest {
    
    @Test
    public void testContent() {
        Article article = new Article();
        
        article.setContent("Hello world!  ");
        
        assertEquals(article.getContent(), "Hello world!");
    }

    @Test
    public void testFilepath() {
        Article article = new Article();
        
        article.setFilepath("foo/bar.txt");
        
        assertEquals(article.getFilepath(), "foo" + File.separator + "bar.txt");
    }

    @Test
    public void testName() {
        Article article = new Article();
        
        article.setFilepath("foo/bar.txt");

        assertEquals(article.getName(), "bar.txt");
    }

    @Test
    public void testPlainName() {
        Article article = new Article();
        
        article.setFilepath("foo/bar.txt");
        
        assertEquals(article.getPlainName(), "bar");
    }
}
