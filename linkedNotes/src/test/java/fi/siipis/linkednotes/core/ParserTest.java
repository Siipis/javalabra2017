/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.siipis.linkednotes.core;

import fi.siipis.linkednotes.data.Article;
import fi.siipis.linkednotes.data.Keyword;
import fi.siipis.linkednotes.data.Library;
import fi.siipis.linkednotes.data.Occurrence;
import java.util.ArrayList;
import org.junit.Before;
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
        parser = Parser.getInstance();
        
        Navigator.getInstance().setRootPath(Utils.testRootPath);
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

    @Test
    public void testKeyWordToOccurrences() {
        Article ref = new Article();
        Keyword keyword = new Keyword("three", ref);

        Article article = new Article();
        article.setContent("one two three four");

        ArrayList<Occurrence> occurrences = parser.toOccurrences(article, keyword);

        assertNotNull(occurrences);
        assertSame(occurrences.size(), 1);

        Occurrence o = occurrences.get(0);

        assertSame(o.getArticle(), article);
        assertEquals(o.getKeyword().getName(), "three");
        assertEquals(o.getKeyword().getArticle(), ref);
        assertSame(o.getPosition(), 8);
    }

    @Test
    public void testLibraryToOccurrences() {
        Article apple = parser.toArticle("[apple, apples]" + parser.separator + "Orange? Apples are not oranges. They're still orange sometimes.");
        apple.setFilepath("apple.txt");

        Article orange = parser.toArticle("[orange, oranges]" + parser.separator + "Oranges are orange.");
        orange.setFilepath("orange.txt");

        ArrayList<Article> articles = new ArrayList<>();
        articles.add(apple);
        articles.add(orange);

        ArrayList<Keyword> keywords = new ArrayList<>();
        keywords.addAll(apple.getKeywords());
        keywords.addAll(orange.getKeywords());

        Library library = Library.getInstance();
        library.empty();

        library.setArticles(articles);
        library.setKeywords(keywords);

        ArrayList<Occurrence> occurrences = parser.toOccurrences(apple, library);
        
        assertNotNull(occurrences);
        assertSame(occurrences.size(), 3);

        assertSame(occurrences.get(0).getPosition(), 0);
        assertSame(occurrences.get(2).getPosition(), 23);
    }
}
