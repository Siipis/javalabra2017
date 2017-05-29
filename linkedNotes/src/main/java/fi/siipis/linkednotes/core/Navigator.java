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

    public Navigator() {
        this(Utils.rootPath);
    }
    
    public Navigator(String path) {
        this.setRootPath(path);
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
        String newPath = getCurrentPath() + File.separator + Utils.normalisePath(path);

        if (path.equals(".")) {
            newPath = getRootPath();
        } else if (path.equals("..")) {
            // Don't allow navigating outside the root
            if (currentIsRoot()) {
                newPath = getRootPath();
            } else {
                String c = getCurrentPath();

                newPath = c.substring(0, c.lastIndexOf(File.separator));
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
    public ArrayList<File> list() {
        ArrayList<File> contents = new ArrayList<>();

        if (currentPath.isDirectory()) {
            for (String filename : currentPath.list()) {
                contents.add(new File(filename));
            }
        }

        return contents;
    }

    /**
     * Checks whether the current path is the root path
     *
     * @return True if yes
     */
    private boolean currentIsRoot() {
        return currentPath.getAbsolutePath().equals(rootPath.getAbsolutePath());
    }
}
