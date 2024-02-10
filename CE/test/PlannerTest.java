import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Tests planner class
 * @author Skyler Dunn
 */
public class PlannerTest {

    /** Planner for testing */
    private Planner planner;

    /** Event A for testing */
    private Event eventA;

    /** Event B for testing */
    private Event eventB;

    /** Event C for testing */
    private Event eventC;

    /** Event 1 for testing */
    private Event event1;

    /** Event 2 for testing */
    private Event event2;

    /** Event 3 for testing */
    private Event event3;

    /** Thanksgiving event for testing */
    private Event thanksEvent;

    /** Amulya's birthday party event for testing */
    private Event amuBirthday;

    /** Mei's birthday party event for testing */
    private Event meiBirthday;

    /** Skyler's birthday party event for testing */
    private Event skyBirthday;

    /** Amanda's birthday party event for testing */
    private Event amaBirthday;

    /** Conflict event for testing */
    private Event conflictEvent;

    /** Updated planner for testing */
    private Planner updatedPlanner;

    /**
     * Creates Planner for testing
     */
    @BeforeEach
    public void setUp() {
        planner = new Planner();
        eventA = new Event(5, 11, 2005, 0, 23, "Skyler's Birthday");
        eventB = new Event(6, 6, 2004, 0, 23, "Anna's Birthday");
        eventC = new Event(8, 5, 2003, 0, 23, "Jess's Birthday");
        event1 = new Event(4, 5, 2023, 12, 14, "Lunch with Beth");
        event2 = new Event(4, 5, 2023, 19, 20, "Dinner with Mom");
        event3 = new Event(4, 5, 2023, 16, 17, "Shopping at mall");
        thanksEvent = new Event(11, 25, 2013, 18, 21, "Thanksgiving Dinner");
        amuBirthday = new Event(1, 5, 2023, 11, 15, "Amulya's birthday party");
        meiBirthday = new Event(8, 21, 2023, 11, 15, "Mei's birthday party");
        skyBirthday = new Event(5, 11, 2023, 11, 15, "Skyler's birthday party");
        amaBirthday = new Event(1, 21, 2023, 11, 15, "Amanda's birthday party");
        conflictEvent = new Event(4, 5, 2023, 13, 17, "Crochet club meeting");
        updatedPlanner = new Planner();
    }

    /**
     * Tests sort method
     */
    @Test
    public void testSort() {
        planner.setEvent(0, eventA);
        planner.setEvent(1, eventC);
        planner.setEvent(2, eventB);
        planner.sort();
        Event testA = planner.getEvent(0);
        String strA = testA.toStringDate();
        assertEquals("8\t5\t2003", strA);
        Event testB = planner.getEvent(1);
        String strB = testB.toStringDate();
        assertEquals("6\t6\t2004", strB);
        Event testC = planner.getEvent(2);
        String strC = testC.toStringDate();
        assertEquals("5\t11\t2005", strC);
    }

    /**
     * Tests sort method - same day events
     */
    @Test
    public void testSortSameDay() {
        planner.setEvent(0, event1);
        planner.setEvent(1, event2);
        planner.setEvent(2, event3);
        planner.sort();
        Event test1 = planner.getEvent(0);
        String str1 = test1.toStringEvent();
        assertEquals("12 - 14: Lunch with Beth", str1);
        Event test2 = planner.getEvent(1);
        String str2 = test2.toStringEvent();
        assertEquals("16 - 17: Shopping at mall", str2);
        Event test3 = planner.getEvent(2);
        String str3 = test3.toStringEvent();
        assertEquals("19 - 20: Dinner with Mom", str3);
    }

    /**
     * Tests addEvent method - successful
     */
    @Test
    public void testAddEvent() {
        String simulatedFile = "4,5,2023,12,14,Lunch with Beth\n" +
                               "4,5,2023,19,20,Dinner with Mom\n" +
                               "4,5,2023,16,17,Shopping at mall";
        Scanner scan = new Scanner(simulatedFile);
        planner.addInputEvent(scan);
        planner.sort();
        String input = "11\n25\n2013\n18\n21\nThanksgiving Dinner\n";
        Scanner scnr =  new Scanner(input); // simulate keyboard input
        updatedPlanner.setEvent(0, event1);
        updatedPlanner.setEvent(1, event3);
        updatedPlanner.setEvent(2, event2);
        updatedPlanner.setEvent(3, thanksEvent);
        String actual = planner.addEvent(scnr);

        assertEquals("Successful addition", actual, "planner.addEvent(scan)");

        assertEquals(updatedPlanner.toString(), planner.toString(), 
                    "comparing the expected and actual planners");
    }

    /**
     * Tests addInputEvent method
     */
    @Test
    public void testAddInputEvent() {
        String simulatedFile = "1,5,2023,11,15,Amulya's birthday party\n" +
                               "8,21,2023,11,15,Mei's birthday party\n" +
                               "5,11,2023,11,15,Skyler's birthday party\n" +
                               "1,21,2023,11,15,Amanda's birthday party";
        Scanner scan = new Scanner(simulatedFile);
        planner.addInputEvent(scan);
        updatedPlanner.setEvent(0, amuBirthday);
        updatedPlanner.setEvent(1, meiBirthday);
        updatedPlanner.setEvent(2, skyBirthday);
        updatedPlanner.setEvent(3, amaBirthday);

        assertEquals(updatedPlanner.toString(), planner.toString(), 
                    "comparing the expected and actual planners");
    }

    /**
     * Tests conflict method
     */
    @Test
    public void testConflict() {
        planner.setEvent(0, event1);
        planner.setEvent(1, event2);
        planner.setEvent(2, event3);
        assertTrue(planner.conflict(conflictEvent));
    }

    /**
     * Tests removeEvent method
     */
    @Test
    public void testRemoveEvent() {
        String simulatedFile = "4,5,2023,12,14,Lunch with Beth\n" +
                               "4,5,2023,19,20,Dinner with Mom\n" +
                               "4,5,2023,16,17,Shopping at mall\n" +
                               "11,25,2013,18,21,Thanksgiving Dinner\n";
        Scanner scan = new Scanner(simulatedFile);
        planner.addInputEvent(scan);
        planner.sort();
        String input = "11\n25\n2013\n18\n21\nThanksgiving Dinner\n";
        Scanner scnr =  new Scanner(input); // simulate keyboard input
        updatedPlanner.setEvent(0, event1);
        updatedPlanner.setEvent(1, event3);
        updatedPlanner.setEvent(2, event2);
        String actual = planner.removeEvent(scnr);

        assertEquals("Successful removal", actual, "planner.removeEvent(scan);");

        assertEquals(updatedPlanner.toString(), planner.toString(), 
                    "comparing the expected and actual planners");
    }

    /**
     * Tests toString method
     */
    @Test
    public void testToString() {
        planner.setEvent(0, event1);
        planner.setEvent(1, event2);
        planner.setEvent(2, event3);
        planner.sort();
        String expected = "\n4\t5\t2023\n" +
                          "12 - 14: Lunch with Beth\n" +
                          "16 - 17: Shopping at mall\n" +
                          "19 - 20: Dinner with Mom\n";
        String actual = planner.toString();
        assertEquals(expected, actual, "Planner toString");
    }

    /**
     * Tests toStringRange method
     */
    @Test
    public void testToStringRange() {
        planner.setEvent(0, eventA);
        planner.setEvent(1, eventB);
        planner.setEvent(2, eventC);
        planner.setEvent(3, event1);
        planner.sort();
        String expected = "\n8\t5\t2003\n" +
                            "All Day: Jess's Birthday\n" +
                            "\n6\t6\t2004\n" +
                            "All Day: Anna's Birthday\n" +
                            "\n5\t11\t2005\n" +
                            "All Day: Skyler's Birthday\n";
        String actual = planner.toStringRange("8\t5\t2003", "5\t11\t2005");
        assertEquals(expected, actual, "Planner toStringRange");
    }

    

}