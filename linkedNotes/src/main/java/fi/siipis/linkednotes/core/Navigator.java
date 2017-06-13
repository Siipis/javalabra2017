/**
 * Navigator
 *
 * Convenience class for exploring file directories.
 * Abstraction for "opening" directories and traversing the file system.
 * All paths are given relative to the root folder.
 *
 */
package fi.siipis.linkednotes.core;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Amalia Surakka
 */
public class Navigator {

    private File rootPath;

    private File currentPath;

    private Navigator() {
        this.setRootPath(Utils.rootPath);
    }

    /**
     * @return Singleton instance of class
     */
    public static Navigator getInstance() {
        return Factory.INSTANCE;
    }

    private static class Factory {

        private static final Navigator INSTANCE = new Navigator();
    }

    /**
     * @return Path of root directory
     */
    public String getRootPath() {
        return rootPath.getPath();
    }

    /**
     * @param rootPath Path to root directory
     */
    public void setRootPath(String rootPath) {
        rootPath = Utils.normalisePath(rootPath);

        this.rootPath = new File(rootPath);
        this.currentPath = this.rootPath;
    }

    /**
     * @return Currently opened directory
     */
    public String getCurrentPath() {
        return currentPath.getPath();
    }

    /**
     * @param currentPath Open directory
     */
    public void setCurrentPath(String currentPath) {
        currentPath = Utils.normalisePath(currentPath);

        this.currentPath = new File(currentPath);
    }

    /**
     * @return Parent directory of the current path
     */
    public String parentPath() {
        if (currentIsRoot()) {
            return "";
        }

        return currentPath.getParent();
    }

    /**
     * Opens a file or a directory Use . or .. to traverse up the directory tree
     *
     * @param path Path to open
     * @return File from path
     */
    public File open(String path) {
        String newPath;

        if (path.equals(".")) {
            newPath = this.getRootPath();
        } else {
            if (path.equals("..") && this.currentIsRoot()) {
                // Don't allow navigating outside root
                newPath = this.getCurrentPath();
            } else {
                newPath = this.getFullPath(path);
            }
        }

        if (new File(newPath).exists()) {
            this.setCurrentPath(newPath);
        } else {
            System.err.println("Coult not navigate to " + newPath);
        }

        return currentPath;
    }

    /**
     * @return List of files in the current path
     */
    public ArrayList<String> list() {
        ArrayList<String> contents = new ArrayList<>();

        if (currentPath.isDirectory()) {
            for (String filename : currentPath.list()) {
                contents.add(getFullPath(filename));
            }
        }

        return contents;
    }

    /**
     * @param path Path relative to root
     * @return Full path from the root directory
     */
    public String getFullPath(String path) {
        if (path == null) {
            return null;
        }

        path = Utils.normalisePath(path);

        if (!path.startsWith("/") && !path.startsWith(this.getRootPath())) {
            path = Utils.normalisePath(this.getCurrentPath() + "/" + path);
        }

        return Utils.canonisePath(path, getRootPath());
    }
    
    /**
     * @param path Relative path from root
     * @return Path to parent directory
     */
    public String getParentPath(String path) {
        File file = new File(this.getFullPath(path));
        
        return file.getParent();
    }

    /**
     * @return True if the current path is the root directory
     */
    public boolean currentIsRoot() {
        return currentPath.getAbsolutePath().equals(rootPath.getAbsolutePath());
    }
}
