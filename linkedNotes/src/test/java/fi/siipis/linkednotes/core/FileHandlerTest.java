package fi.siipis.linkednotes.core;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Amalia Surakka
 */
public class FileHandlerTest {

    private FileHandler fileHandler;

    private static String emptyFile = "temp/empty.txt";

    private static boolean deleteTempDirectory = true;

    @Before
    public void setUp() {
        this.fileHandler = FileHandler.getInstance();
        
        Navigator.getInstance().setRootPath(Utils.testRootPath);
    }

    @BeforeClass
    public static void createEmptyFile() {
        try {
            emptyTempDirectory();

            File file = new File(getEmptyFilePath());

            file.getParentFile().mkdirs();

            file.createNewFile();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @AfterClass
    public static void tearDown() {
        if (!deleteTempDirectory) {
            return;
        }

        emptyTempDirectory();
    }

    private static String getEmptyFilePath() {
        return Utils.normalisePath(Utils.testRootPath + File.separator + emptyFile);
    }

    private static void emptyTempDirectory() {
        try {
            File file = new File(getEmptyFilePath());

            if (file.exists()) {
                File tempDirectory = file.getParentFile();

                FileUtils.deleteDirectory(tempDirectory);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void canFindFiles() {
        File file = fileHandler.findFile("icecream/caramel.txt");

        assertNotNull(file);
        assertTrue(file.isFile());
    }

    @Test
    public void returnsNullOnFileNotFound() {
        File file = fileHandler.findFile("foo/bar.txt");

        assertSame(file, null);
    }

    @Test
    public void canReadFiles() {
        try {
            String contents = fileHandler.readFile("icecream/cherry.txt");

            assertEquals("Do you like cherry?", contents);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            assertTrue(false);
        }
    }

    @Test
    public void canWriteExistingFiles() {
        try {
            String contents = "Hello world!";

            fileHandler.writeFile(emptyFile, contents);

            File file = fileHandler.findFile(emptyFile);

            assertTrue(file.length() > 0);
        } catch (Exception e) {
            System.out.println(e.toString());

            assertTrue(false);
        }
    }

    @Test
    public void canRenameExistingFiles() {
        assertTrue(fileHandler.createFile("temp/rename.txt"));

        assertTrue(fileHandler.fileExists("temp/rename.txt"));

        assertTrue(fileHandler.renameFile("temp/rename.txt", "temp/delete.txt"));

        assertSame(fileHandler.findFile("temp/rename.txt"), null);
        assertNotSame(fileHandler.findFile("temp/delete.txt"), null);
    }

    @Test
    public void canDeleteExistingFiles() {
        String fileName = "temp/eatMe.txt";

        assertTrue(fileHandler.createFile(fileName));

        assertTrue(fileHandler.fileExists(fileName));

        assertTrue(fileHandler.deleteFile(fileName));

        assertFalse(fileHandler.fileExists(fileName));
    }

    @Test
    public void canCreateDirectory() {
        String dirName = "temp/foo";

        assertTrue(fileHandler.createDirectory(dirName));

        assertTrue(fileHandler.fileExists(dirName));
    }

    @Test
    public void canCreateDirectories() {
        String dirName = "temp/bar/baz";

        assertTrue(fileHandler.createDirectory(dirName));

        assertTrue(fileHandler.fileExists(dirName));
    }

    @Test
    public void returnFalseOnExistingDirectory() {
        assertFalse(fileHandler.createDirectory("icecream"));
    }

    @Test
    public void canDeleteDirectory() {
        assertTrue(fileHandler.createDirectory("temp/baz"));

        assertTrue(fileHandler.deleteDirectory("temp/baz"));

        assertFalse(fileHandler.fileExists("temp/baz"));
    }
    
    @Test
    public void canRenameDirectory() {
        assertTrue(fileHandler.createDirectory("temp/rename"));
        
        assertTrue(fileHandler.renameDirectory("temp/rename", "temp/foo/newname"));
    }
    
    @Test
    public void cannotRenameNonExistingDirectory() {
        assertFalse(fileHandler.renameDirectory("temp/noname", "temp/newname"));
    }
}
