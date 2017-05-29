/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.siipis.linkednotes.core;

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ami
 */
public class NavigatorTest {
    
    private Navigator navigator;
    
    private String rootPath;
        
    @Before
    public void setUp() {
        navigator = new Navigator();
        
        rootPath = "test" + File.separator + "files";
        
        navigator.setRootPath(rootPath);
    }
    
    @Test
    public void rootPathExists() {
        File file = new File(navigator.getRootPath());
        
        assertTrue(file.exists());
    }
    
    @Test
    public void pathExists() {
        File file = navigator.open("fruit");
        
        assertTrue(file.exists());
    }
        
    @Test
    public void navigateToRoot() {
        navigator.open("icecream");
        
        navigator.open("..");
        
        assertEquals(navigator.getCurrentPath(), rootPath);
    }

    @Test
    public void navigateNowhere() {
        navigator.open("foo");
        
        navigator.open("..");

        assertEquals(navigator.getCurrentPath(), rootPath);
    }
    
    @Test
    public void navigateOutsideRoot() {
        navigator.open("..");

        assertEquals(navigator.getCurrentPath(), rootPath);
    }
    
    @Test
    public void pathHasContents() {
        navigator.open("icecream");
        
        ArrayList<File> contents = navigator.list();
        
        assertTrue(contents.size() == 2);
    }
    
    @Test
    public void pathHasNoContents() {
        navigator.open("icecream/chocolate.txt");
        
        ArrayList<File> contents = navigator.list();
        
        assertTrue(contents.size() == 0);        
    }
    
    @Test
    public void catchErrorOnNoDirectory() {
        navigator.open("foo");
        
        try {
            navigator.list(); 
            
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
    
    @Test
    public void parentPathExists() {
        navigator.open("icecream");
        
        File file = new File(navigator.parentPath());
        
        assertTrue(file.exists());
    }
    
    @Test
    public void rootPathHasNoParent() {
        File file = new File(navigator.parentPath());
        
        assertFalse(file.exists());        
    }
}
