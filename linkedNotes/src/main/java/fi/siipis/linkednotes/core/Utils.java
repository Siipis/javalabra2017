/**
 * Global Utilities
 *
 * Generic helper for commonly shared functionalities.
 */
package fi.siipis.linkednotes.core;

import java.io.File;
import java.util.regex.Matcher;

/**
 *
 * @author Amalia Surakka
 */
public class Utils {

    /**
     * Path where files are stored
     */
    public static String rootPath = "files";

    /**
     * Path where testing files are stored
     */
    public static String testRootPath = "test" + File.separator + "files";

    /**
     * Normalise a file path
     *
     * @param path File path
     * @return Normalised file path
     */
    public static String normalisePath(String path) {
        path = path.trim();

        path = path.replaceAll("/", Matcher.quoteReplacement(File.separator));

        return path;
    }

    /**
     * Canonise a file path
     *
     * @param path File path
     * @return Path as relative to the root directory
     */
    public static String canonisePath(String path) {
        return canonisePath(path, rootPath);
    }

    /**
     * Canonise the file path relative to the given root
     *
     * @param path File path
     * @param rootPath Root path to use
     * @return Path as relative to the given directory
     */
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
