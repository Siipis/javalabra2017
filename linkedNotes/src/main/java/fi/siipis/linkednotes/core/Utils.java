package fi.siipis.linkednotes.core;

import java.io.File;
import java.util.regex.Matcher;

/**
 *
 * @author Amalia Surakka
 */
public class Utils {
    public static String rootPath = "files";
    
    public static String testRootPath = "test" + File.separator + "files";
        /**
     * Normalise the file path
     * 
     * @param path
     * @return 
     */
    public static String normalisePath(String path) {
        path = path.trim();
                
        path = path.replaceAll("/", Matcher.quoteReplacement(File.separator));
                
        return path;
    }
}
