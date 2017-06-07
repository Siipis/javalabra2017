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
            
    @Before
    public void setUp() {
        navigator = new Navigator();
                
        navigator.setRootPath(Utils.testRootPath);
    }
    
    @Test
    public void rootPathExists() {
        File file = new File(navigator.getRootPath());
        
        assertTrue(file.exists());
    }
    
    @Test
    public void currentPathExists() {
        String string = navigator.getCurrentPath();
        
        assertTrue(new File(string).exists());
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
        
        assertEquals(Utils.testRootPath, navigator.getCurrentPath());
    }

    @Test
    public void navigateNowhere() {
        navigator.open("foo");
        
        navigator.open("..");

        assertEquals(Utils.testRootPath, navigator.getCurrentPath());
    }
    
    @Test
    public void navigateOutsideRoot() {
        navigator.open("..");

        assertEquals(Utils.testRootPath, navigator.getCurrentPath());
    }
        
    @Test
    public void pathHasContents() {
        navigator.open("icecream");
        
        ArrayList<String> contents = navigator.list();
        
        assertTrue(contents.size() > 0);
    }
    
    @Test
    public void pathHasNoContents() {
        navigator.open("icecream/chocolate.txt");
        
        ArrayList<String> contents = navigator.list();
        
        assertTrue(contents.isEmpty());        
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
