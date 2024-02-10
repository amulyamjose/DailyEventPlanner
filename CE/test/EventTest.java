import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Event class
 * @author Amanda Currie
 */
public class EventTest {

    /** Event for testing */
    private Event event;

    /** Create event */
    @BeforeEach
    public void setUp(){
        event = new Event(12,25,2023,13,16,"Party");
    }

    /**
     * Tests constants
     */
    @Test
    public void testConstants() {
        assertEquals(1, Event.JAN, "JAN" );
        assertEquals(2, Event.FEB, "FEB" );
        assertEquals(3, Event.MAR, "MAR" );
        assertEquals(4, Event.APR, "APR" );
        assertEquals(5, Event.MAY, "MAY" );
        assertEquals(6, Event.JUN, "JUN" );
        assertEquals(7, Event.JUL, "JUL" );
        assertEquals(8, Event.AUG, "AUG" );
        assertEquals(9, Event.SEP, "SEP" );
        assertEquals(10, Event.OCT, "OCT" );
        assertEquals(11, Event.NOV, "NOV" );
        assertEquals(12, Event.DEC, "DEC" );
        assertEquals(31, Event.BIGM, "BIGM" );
        assertEquals(30, Event.SMALLM, "SMALLM" );
        assertEquals(24, Event.DAY, "DAY" );
        assertEquals(23, Event.MT, "MT" );

    }

    /**
     * Tests constructor preconditions
     */
    @Test
    public void testConstructorPreConditions() {
        //invalid month values
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(13,25,2023,13,16,"Party"), "Constructor month 13");
        assertEquals("Invalid month", exception.getMessage(), "Testing month 13 message");
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(0,25,2023,13,16,"Party"), "Constructor month 0");
        assertEquals("Invalid month", exception.getMessage(), "Testing month 0 message");
        //invalid day values
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(12,0,2023,13,16,"Party"), "Constructor day 0");
        assertEquals("Invalid day", exception.getMessage(), "Testing day 0 message");
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(1,32,2023,13,16,"Party"), "Constructor day 32");
        assertEquals("Invalid day", exception.getMessage(), "Testing day 32 message");
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(4,31,2023,13,16,"Party"), "Constructor day 31");
        assertEquals("Invalid day", exception.getMessage(), "Testing day 31 message");
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(2,30,2023,13,16,"Party"), "Constructor day 30");
        assertEquals("Invalid day", exception.getMessage(), "Testing day 30 message");
        //invalid year
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(12,25,-100,13,16,"Party"), "Constructor year -100");
        assertEquals("Invalid year", exception.getMessage(), "Testing year -100 message");
        //invalid start time
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(12,25,2023,-1,16,"Party"), "Constructor start time -1");
        assertEquals("Invalid start time", exception.getMessage(), "Testing start time -1 message");
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(12,25,2023,24,16,"Party"), "Constructor start time 24");
        assertEquals("Invalid start time", exception.getMessage(), "Testing start time 24 message");
        //invalid end time
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(12,25,2023,13,-1,"Party"), "Constructor end time -1");
        assertEquals("Invalid end time", exception.getMessage(), "Testing end time -1 message");
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(12,25,2023,13,24,"Party"), "Constructor end time 24");
        assertEquals("Invalid end time", exception.getMessage(), "Testing end time 24 message");
        //invalid range of time
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(12,25,2023,13,10,"Party"), 
                    "Constructor end time before start time message");
        assertEquals("Invalid range of time", exception.getMessage(), 
                "Testing end time before start time message");
        exception = assertThrows(IllegalArgumentException.class,
            () -> new Event(12,25,2023,13,13,"Party"), 
                    "Constructor start time equals end time message");
        assertEquals("Invalid range of time", exception.getMessage(), 
                "Testing start time equals end time message");


    }

    /** 
     * Test getMonth() for event
     */
    @Test
    public void testGetMonth(){
        assertEquals(12, event.getMonth(), "Event month");
    }

    /**
     * Test getDay() for event
     */
    @Test
    public void testGetDay(){
        assertEquals(25, event.getDay(), "Event day");
    }

    /**
     * Test getYear() for event
     */
    @Test
    public void testGetYear(){
        assertEquals(2023, event.getYear(), "Event year");
    }

    /**
     * Test getStartTime() for event
     */ 
    @Test
    public void testGetStartTime(){
        assertEquals(13, event.getStartTime(), "Event start time");
    }

    /**
     * Test getEndTime() for event
     */
    @Test
    public void testGetEndTime(){
        assertEquals(16, event.getEndTime(), "Event end time");
    }

    /**
     * Test getDescription() for event
     */
    @Test
    public void testGetDescription(){
        assertEquals("Party", event.getDescription(), "Event description");
    }

    /**
     * Test toStringDate() for event
     */
    @Test
    public void testToStringDate() {
        assertEquals(12 + "\t" + 25 + "\t" + 2023, event.toStringDate(), "Event toStringDate");
    }

    /**
     * Test toStringEvent() for event
     */
    @Test
    public void testToStringEvent(){
        assertEquals(13 + " - " + 16 + ": " + "Party", event.toStringEvent(), 
                    "Event toStringEvent");
    }

    /**
     * Test equals() for event
     */
    @Test
    public void testEquals(){
        assertTrue(event.equals(event), "event equals with same instance");
        assertTrue(event.equals(new Event(12,25,2023,13,16,"Party")), 
                    "event equals with different instances");
        assertFalse(event.equals(new Event(1,25,2023,13,16,"Party")), 
                    "event equals with different month");
        assertFalse(event.equals(new Event(12,20,2023,13,16,"Party")), 
                    "event equals with different day");
        assertFalse(event.equals(new Event(12,25,2022,13,16,"Party")), 
                    "event equals with different year");
        assertFalse(event.equals(new Event(12,25,2023,7,16,"Party")), 
                    "event equals with different start time");
        assertFalse(event.equals(new Event(12,25,2023,13,14,"Party")), 
                    "event equals with different end time");
        assertFalse(event.equals(new Event(12,25,2023,13,16,"Event")), 
                    "event equals with different description");
        assertFalse(event.equals(new Event(1,20,2022,7,14,"Event")), 
                    "event equals with all different values");
        assertFalse(event.equals(null), "event equals with null object");
    }
}
