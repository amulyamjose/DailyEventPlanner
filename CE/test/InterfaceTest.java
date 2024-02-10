import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.nio.file.*;
import java.nio.file.Files;

/**
 * Tests Interface Method
 * @author Skyler Dunn
 */
public class InterfaceTest {

    /** Planner for testing */
    private Planner planner;

    /**
     * Creates planner for testing before each test
     */
    @BeforeEach
    public void setUp() {
        planner = new Planner();
    }

    /**
     * Test for outputEvents method
     */
    @Test
    public void testOutputEvents() throws FileNotFoundException {
        Event eventA = new Event(5, 11, 2005, 0, 23, "Skyler's Birthday");
        Event eventB = new Event(1, 5, 2004, 0, 23, "Amulya's Birthday");
        Event eventC = new Event(4, 24, 2023, 0, 23, "LDOC");

        String expected = "test-files/exp_eventsTesting.txt";
        String actual = "test-files/eventsTesting.txt";

        planner.setEvent(0, eventA);
        planner.setEvent(1, eventB);
        planner.setEvent(2, eventC);
        planner.sort();

        Path path = Path.of(actual);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // Nothing needs to be done
            e.printStackTrace();
        }
        PrintWriter out = new PrintWriter(new FileOutputStream(actual));
        Interface.outputEvents(out, planner);
        out.close();
        assertFilesEqual(expected, actual, "outputEvents");
    }

    /**
     * Tests whether files contain the same contents
     * Method from teaching staff tests for Project 3
     * 
     * @param pathToExpected path to file with expected contents
     * @param pathToActual path to file with actual content
     * @param message message for test
     * @throws FileNotFoundException if Scanner cannot be constructed with file
     */
    private void assertFilesEqual(String pathToExpected, String pathToActual, String message)
            throws FileNotFoundException {
        try (Scanner expected = new Scanner(new FileInputStream(pathToExpected));
                Scanner actual = new Scanner(new FileInputStream(pathToActual));) {
            while (expected.hasNextLine()) {
                if (!actual.hasNextLine()) { // checks that actual has line as well
                    fail(message + ": Actual missing line(s)");
                } else { // both have another line
                    assertEquals(expected.nextLine(), actual.nextLine(),
                            message + ": Checking line equality");
                }
            }

            if (actual.hasNextLine()) {
                fail(message + ": Actual has extra line(s)");
            }
        }
    }

}