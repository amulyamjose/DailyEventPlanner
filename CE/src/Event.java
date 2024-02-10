/**
 * Represents a singular event
 * 
 * @author Mei Etchinson
 * @author Amanda Currie
 * @author Skyler Dunn
 * @author Amulya Jose
 */
public class Event {

    /** Field for month */
    private int month;
    
    /** Field for day */
    private int day;

    /** Field for year */
    private int year;
    
    /** Field for Start Time */
    private int startTime;

    /** Field for End Time */
    private int endTime;

    /** Field for description */
    private String desc;
    
    /** Month of January */
    public static final int JAN = 1;
    
    /** Month of February */
    public static final int FEB = 2;
    
    /** Month of March */
    public static final int MAR = 3;
    
    /** Month of April */
    public static final int APR = 4;

    /** Month of May */
    public static final int MAY = 5;

    /** Month of June */
    public static final int JUN = 6;
    
    /** Month of July */
    public static final int JUL = 7;
    
    /** Month of August */
    public static final int AUG = 8;
    
    /** Month of September */
    public static final int SEP = 9;

    /** Month of October */
    public static final int OCT = 10;

    /** Month of November */
    public static final int NOV = 11;

    /** Month of December */
    public static final int DEC = 12;

    /** Largest amount of days in a month */ 
    public static final int BIGM = 31;

    /** Smallest amount of days in a month */ 
    public static final int SMALLM = 30;

    /** Days in February */ 
    public static final int FEBM = 28;

    /** Hours in a day */
    public static final int DAY = 24;

    /** Military time */
    public static final int MT = 23;


    /**
     * Constructor for event
     * @param month for event
     * @param day for event
     * @param year for event
     * @param startTime for event
     * @param endTime for event
     * @param desc for event
     * @throws IllegalArgumentException with invalid values
     */
    public Event(int month, int day, int year, int startTime, int endTime, String desc) {
        if (month > DEC || month < 1) {
            throw new IllegalArgumentException("Invalid month");
        }
        if (month == JAN || month == MAR || month == MAY || month == JUL || month == AUG || 
                month == OCT || month == DEC) {
            if (day > BIGM || day < 1) {
                throw new IllegalArgumentException("Invalid day");
            }
        }
        if (month == APR || month == JUN || month == SEP || month == NOV) {
            if (day > SMALLM || day < 1) {
                throw new IllegalArgumentException("Invalid day");
            }
        }
        if (month == FEB) {
            if (day > FEBM || day < 0) {
                throw new IllegalArgumentException("Invalid day");
            }
        }
        if (year < 0) {
            throw new IllegalArgumentException("Invalid year");
        }
        
        if (startTime < 0 || startTime > MT){
            throw new IllegalArgumentException("Invalid start time");
        }
        if (endTime < 0 || endTime > MT){
            throw new IllegalArgumentException("Invalid end time");
        }
        if ((startTime > endTime) || (startTime == endTime)) {
            throw new IllegalArgumentException("Invalid range of time");
        }
        setMonth(month);
        setDay(day);
        setYear(year);
        setStartTime(startTime);
        setEndTime(endTime);
        setDescription(desc);
      
    }

    /**
     * Method that retrieves the month the event takes place
     * 
     * @return the month the event takes place
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Method that sets the month the event takes place
     * 
     * @param month the given value for month
     */
    public void setMonth(int month) {
        this.month = month;
    }
     
    /**
     * Method that retrieves the day that the event takes place
     * 
     * @return the day the event takes place
     */
    public int getDay() {
        return this.day;
    }
      
    /**
     * Method that sets the day the event takes place
     * 
     * @param day the given value for day
     */
    public void setDay(int day) {
        this.day = day;
    }
       
    /**
     * Method that retrieves the year that the event takes place
     * 
     * @return the year the event takes place
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Method that sets the year the event takes place
     * 
     * @param year the given value for year
     */
    public void setYear(int year) {
        this.year = year;
    }
        
    /**
     * Method that retrieves the time that the event starts
     * 
     * @return the time the event starts
     */
    public int getStartTime() {
        return this.startTime;
    }

    /**
     * Method that sets the time the event starts
     * 
     * @param start the given value for the start time
     */
    public void setStartTime(int start) {
        this.startTime = start;
    }
    
    /**
     * Method that retrieves the time that the event ends
     * 
     * @return the time the event ends
     */
    public int getEndTime() {
        return this.endTime;
    }

    /**
     * Method that sets the time the event ends
     * 
     * @param end the given value for the end time
     */
    public void setEndTime(int end) {
        this.endTime = end;
    }
          
    /**
     * Method to set description
     * 
     * @return String with description
     */
    public String getDescription() {
        return this.desc; 
    }

    /**
     * Method that sets the description of the event
     * 
     * @param description String for description
     */
    public void setDescription(String description) {
        this.desc = description;    
    }

    /**
     * Converts month, day, and year to a string
     * 
     * @return Date of event in a formatted string
     */
    public String toStringDate() {
        return (month + "\t" + day + "\t" + year);
    }
     
    /**
     * Method to format event to String
     * 
     * @return formatted String for event
     */
    public String toStringEvent() {
        String str = "";
        if (startTime == 0 && endTime == MT) {
            str = "All Day: " + desc;
        } else {
            str = startTime + " - " + endTime + ": " + desc;
        }
        return str;
    }

    /**
     * Returns if this Event and o are equal
     * 
     * @param o object to be compared
     * @return true if objects are equal, else false
     */
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event b = (Event) o;
            return month == b.getMonth() && day == b.getDay() && year == b.getYear() 
                && desc.equals(b.getDescription()) && startTime == b.startTime &&
                endTime == b.endTime;
        } else {
            return false;
        }
    }
}