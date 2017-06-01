package fi.siipis.linkednotes.core;

import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Amalia Surakka
 */
public class FileHandler {
    
    private Navigator navigator;
    
    public FileHandler(Navigator navigator) {
        this.navigator = navigator;
    }
    
    /**
     * Returns the navigator instance
     * 
     * @return 
     */
    public Navigator navigator() {
        return navigator;
    }

    /**
     * Returns the file content
     *
     * @param path File path
     * @return File contents
     */
    public String readFile(String path) {
        path = getFullPath(path);

        File file = new File(path);

        if (file.isFile()) {
            try {
                return FileUtils.readFileToString(file, "utf-8");            
            } catch (Exception e) {
                System.out.println(e.toString());
                
                return null;
            }
        }
        
        return null;
    }

    /**
     * Stores a string in a file
     *
     * @param path File path
     * @param content File contents
     * @return boolean
     */
    public boolean writeFile(String path, String content) {
        path = getFullPath(path);

        try {
            File file = new File(path);

            FileUtils.writeStringToFile(file, content, "utf-8");

            if (fileIsEmpty(path) && content.length() > 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            
            return false;
        }
    }

    /**
     * Create an empty file
     *
     * @param path File path
     * @return boolean
     */
    public boolean createFile(String path) {
        path = getFullPath(path);

        try {
            File file = new File(path);

            file.getParentFile().mkdirs();

            file.createNewFile();

            return true;
        } catch (Exception e) {
            System.out.println(e.toString());

            return false;
        }
    }

    /**
     * Remove a file
     *
     * @param path File path
     * @return False on error
     */
    public boolean deleteFile(String path) {
        path = getFullPath(path);
        
        File file = new File(path);
        
        if (!file.exists()) {
            System.out.println("Coult not find file to delete: " + path);
            
            return false;
        }
        
        file.delete();
        
        return !file.exists();
    }

    /**
     * Rename a file
     *
     * @param path File path
     * @param newPath New file path
     * @return False on error
     */
    public boolean renameFile(String path, String newPath) {
        path = getFullPath(path);
        newPath = getFullPath(newPath);
        
        File file = new File(path);
        File newFile = new File(newPath);
        
        if (!file.exists()) {
            System.out.println("Can't rename non-existing file: " + path);
            
            return false;
        }

        if (newFile.exists()) {
            System.out.println("File already exists: " + newPath);
            
            return false;
        }
        
        file.renameTo(newFile);

        return newFile.exists();
    }

    /**
     * Check whether given path is an existing file
     *
     * @param path File path
     * @return True if path exists and is a file
     */
    public boolean isFile(String path) {
        path = getFullPath(path);

        File file = new File(path);

        return file.isFile();
    }
    
    /**
     * Looks for a file in the directory
     * 
     * @param path
     * @return 
     */
    public File findFile(String path) {
        path = getFullPath(path);
        
        File file = new File(path);
        
        if (!file.exists()) {
            return null;
        }
        
        return file;
    }
    
    /**
     * Return true if a file exists
     * 
     * @param path
     * @return 
     */
    public boolean fileExists(String path) {
        path = getFullPath(path);

        File file = new File(path);

        return file.exists();        
    }

    /**
     * Return true if a file is empty
     *
     * @param path
     * @return
     */
    public boolean fileIsEmpty(String path) {
        path = getFullPath(path);

        File file = new File(path);

        return file.length() == 0;
    }

    /**
     * Create an empty directory
     *
     * @param path File path
     * @return False on error
     */
    public boolean createDirectory(String path) {
        path = getFullPath(path);
        
        File file = new File(path);
        
        if (file.exists()) {
            System.out.println("Directory already exists: " + path);

            return false;
        }
        
        file.mkdirs();
        
        file.mkdir();

        return file.exists() && file.isDirectory();
    }

    /**
     * Remove a directory and all its contents
     *
     * @param path File path
     * @return False on error
     */
    public boolean deleteDirectory(String path) {
        path = getFullPath(path);
        
        File file = new File(path);
        
        if (!file.exists()) {
            return false;
        }
        
        if (!file.isDirectory()) {
            return false;
        }
        
        try {
            FileUtils.deleteDirectory(file);            
        } catch (Exception e) {
            System.out.println(e.toString());
            
            return false;
        }

        return !file.exists();
    }

    /**
     * Rename a directory
     *
     * @param path File path
     * @param newPath New file path
     * @return False on error
     */
    public boolean renameDirectory(String path, String newPath) {
        path = getFullPath(path);
        newPath = getFullPath(newPath);
        
        File file = new File(path);
        File newFile = new File(newPath);

        if (!file.isDirectory()) {
            System.out.println("Path is not a directory: " + path);
            
            return false;
        }

        if (!file.exists()) {
            System.out.println("Can't rename non-existing directory: " + path);
            
            return false;
        }

        if (newFile.exists()) {
            System.out.println("Directory already exists: " + newPath);
            
            return false;
        }
        
        file.renameTo(newFile);

        return newFile.exists();
    }

    /**
     * Check whether given path is an existing directory
     *
     * @param path File path
     * @return True if path exists and is a directory
     */
    public boolean isDirectory(String path) {
        path = getFullPath(path);

        File file = new File(path);

        return file.isDirectory();
    }
    
    /**
     * Get the full path relative to the current folder
     * 
     * @param path
     * @return
     */
    private String getFullPath(String path) {
        if (path.startsWith("/")) {
            return Utils.normalisePath(path);            
        }
        
        return Utils.normalisePath(navigator.getCurrentPath() + File.separator + path);
    }
}
