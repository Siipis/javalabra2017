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
    
    public static String canonisePath(String path) {
        return canonisePath(path, rootPath);
    }
    
    public static String canonisePath(String path, String rootPath) {
        path = normalisePath(path);
        
        File file = new File(rootPath + File.separator + path);
        File rootFile = new File(rootPath);
        
        try {
            path = file.getCanonicalPath();
            rootPath = rootFile.getCanonicalPath();
            
            // Don't allow going outside the root folder
            if (!path.startsWith(rootPath)) {
                return File.separator; // Represents root
            }
            
            return path.substring((rootPath + File.separator).length());
        } catch (Exception e) {
            System.out.println(e.toString());
            
            return null;
        }
    }
}
