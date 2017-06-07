package fi.siipis.linkednotes.core;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Amalia Surakka
 */
public class UtilsTest {
        
    private static String s = File.separator;
    
    @Test
    public void testNormalisePath() {
        assertEquals("foo" + s + "bar", Utils.normalisePath("  foo\\bar  "));

        assertEquals("." + s + "foo" + s + "bar", Utils.normalisePath("./foo\\bar  "));
    }
    
    @Test
    public void testCanonisePath() {
        String rootPath = Utils.testRootPath;
        
        assertEquals("foo" + s + "baz", Utils.canonisePath("foo/bar/..//baz", rootPath));
    }
}
