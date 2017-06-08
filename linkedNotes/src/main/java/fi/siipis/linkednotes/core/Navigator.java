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

    public static Navigator getInstance() {
        return Factory.INSTANCE;
    }

    private static class Factory {
        private static final Navigator INSTANCE = new Navigator();
    }

    public String getRootPath() {
        return rootPath.getPath();
    }

    public void setRootPath(String rootPath) {
        rootPath = Utils.normalisePath(rootPath);

        this.rootPath = new File(rootPath);
        this.currentPath = this.rootPath;
    }

    public String getCurrentPath() {
        return currentPath.getPath();
    }

    public void setCurrentPath(String currentPath) {
        currentPath = Utils.normalisePath(currentPath);

        this.currentPath = new File(currentPath);
    }

    /**
     * Return the parent directory of the current path
     *
     * @return
     */
    public String parentPath() {
        if (currentIsRoot()) {
            return "";
        }

        return currentPath.getParent();
    }

    /**
     * Set the current path to the given path
     *
     * @param path
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
     * Return a list of files and directories in the path
     *
     * @return
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
     * Return a cleaned up relative full path
     *
     * @param path
     * @return
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
     * Checks whether the current path is the root path
     *
     * @return True if yes
     */
    public boolean currentIsRoot() {
        return currentPath.getAbsolutePath().equals(rootPath.getAbsolutePath());
    }
}
