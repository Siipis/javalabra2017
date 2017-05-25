package fi.siipis.linkednotes.core;

import java.io.File;

/**
 *
 * @author Amalia Surakka
 */
public class FileHandler {
    
    /**
     * Returns the file content
     * 
     * @param path File path
     * @return File contents
     */
    public String readFile(String path) {
        // TODO: coming soon
        return "";
    }
    
    /**
     * Stores a string in a file
     * 
     * @param path File path
     * @param content File contents
     * @return False on error
     */
    public boolean writeFile(String path, String content) {
        // TODO: coming soon
        return false;
    }
    
    /**
     * Create an empty file
     * 
     * @param path File path
     * @return False on error
     */
    public boolean createFile(String path) {
        // TODO: coming soon
        return false;
    }
    
    /**
     * Remove a file
     * 
     * @param path File path
     * @return False on error
     */
    public boolean deleteFile(String path) {
        // TODO: coming soon
        return false;
    }
    
    /**
     * Rename a file
     * 
     * @param path File path
     * @param newPath New file path
     * @return False on error
     */
    public boolean renameFile(String path, String newPath) {
        // TODO: coming soon
        return false;
    }
    
    /**
     * Check whether given path is an existing file
     * 
     * @param path File path
     * @return True if path exists and is a file
     */
    public boolean isFile(String path) {
        File file = new File(path);
        
        return file.isFile();
    }
    
    /**
     * Create an empty directory
     * 
     * @param path File path
     * @return False on error
     */
    public boolean createDirectory(String path) {
        // TODO: coming soon
        return false;
    }
    
    /**
     * Remove a directory and all its contents
     * 
     * @param path File path
     * @return False on error
     */
    public boolean deleteDirectory(String path) {
        // TODO: coming soon
        return false;
    }
    
    /**
     * Rename a directory
     * 
     * @param path File path
     * @param newPath New file path
     * @return False on error
     */
    public boolean renameDirectory(String path, String newPath) {
        // TODO: coming soon
        return false;
    }
    
    /**
     * Check whether given path is an existing directory
     * 
     * @param path File path
     * @return True if path exists and is a directory
     */
    public boolean isDirectory(String path) {
        File file = new File(path);
        
        return file.isDirectory();
    }
}
