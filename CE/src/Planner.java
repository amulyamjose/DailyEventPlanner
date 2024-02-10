import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Represents a planner with multiple events
 * 
 * @author Mei Etchinson
 * @author Amanda Currie
 * @author Skyler Dunn
 * @author Amulya Jose
 */

public class Planner {
    
    /** Max number of events */
    public static final int MAX = 100;

    /** Field for planner */
    private Event[] planner;

    /** Field for next index */
    private int next;

    /**
     * Constructor for planner
     */
    public Planner() {
        planner = new Event[MAX];
        this.next = 0;
    }

    /**
     * Gets event at specific index in planner
     * @param index location of given event
     * @return event at index
     */
    public Event getEvent(int index) {
        return planner[index];
    }

    /**
     * Method to sort the planner by date and time
     */
    public void sort() {
        int i = 1;
        while(planner[i] != null && planner[i - 1] != null) {
            Event compA = planner[i];
            Event compB = planner[i - 1];
            int aYear = compA.getYear();
            int bYear = compB.getYear();
            if (bYear > aYear) {
                planner[i] = compB;
                planner[i - 1] = compA;
                i = 0;
            } else if (bYear == aYear) {
                int aMonth = compA.getMonth();
                int bMonth = compB.getMonth();
                if (bMonth > aMonth) {
                    planner[i] = compB;
                    planner[i - 1] = compA;
                    i = 0;
                } else if (bMonth == aMonth) {
                    int aDay = compA.getDay();
                    int bDay = compB.getDay();
                    if (bDay > aDay) {
                        planner[i] = compB;
                        planner[i - 1] = compA;
                        i = 0;
                    } else if (bDay == aDay) {
                        int aTime = compA.getStartTime();
                        int bTime = compB.getStartTime();
                        if (bTime > aTime) {
                            planner[i] = compB;
                            planner[i - 1] = compA;
                            i = 0;
                        }
                    }
                }
            }
            i++;
        }

    }

    /**
     * Testing method
     * @param index location of event in planner
     * @param a name of given event
     */
    public void setEvent(int index, Event a) {
        planner[index] = a;
    }

    /**
     * Adds new event to planner
     * @param scan scanner for user input
     * @return String with successful addition
     */
    public String addEvent(Scanner scan) {
        scan.useDelimiter("\n");

        System.out.print("Month: ");
        int eventMonth = 0;
        while (eventMonth == 0) {
            try {
                eventMonth = scan.nextInt();
                while (eventMonth > Event.DEC || eventMonth < 1) {
                    System.out.print("Invalid month, please try again: ");
                    eventMonth = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid month, please try again: ");
            }
        }
        System.out.print("Day: ");
        int eventDay = 0;
        while (eventDay == 0) {
            try {
                eventDay = scan.nextInt();
                if (eventMonth == Event.JAN || eventMonth == Event.MAR || 
                    eventMonth == Event.MAY || eventMonth == Event.JUL || eventMonth == Event.AUG 
                    || eventMonth == Event.OCT || eventMonth == Event.DEC) {
                    while (eventDay > Event.BIGM || eventDay < 1) {
                        System.out.print("Invalid day, please try again: ");
                        eventDay = scan.nextInt();
                    }
                }
                if (eventMonth == Event.APR || eventMonth == Event.JUN || eventMonth == Event.SEP 
                    || eventMonth == Event.NOV) {
                    while (eventDay > Event.SMALLM || eventDay < 1) {
                        System.out.print("Invalid day, please try again: ");
                        eventDay = scan.nextInt();
                    }
                }
                if (eventMonth == Event.FEB) {
                    while (eventDay > Event.FEBM || eventDay < 0) {
                        System.out.print("Invalid day, please try again: ");
                        eventDay = scan.nextInt();
                    }
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid day, please try again: ");
            }
        }
        
        System.out.print("Year: ");
        int eventYear = -1;
        while (eventYear == -1){
            try {
                eventYear = scan.nextInt();
                while (eventYear < 0) {
                    System.out.print("Invalid year, please try again: ");
                    eventYear = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid year, please try again: ");
            }
        }
        
        System.out.print("Start time: ");
        int eventStart = -1;
        while (eventStart == -1) {
            try {
                eventStart = scan.nextInt();
                while (eventStart < 0 || eventStart > Event.MT) {
                    System.out.print("Invalid start time, please try again: ");
                    eventStart = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid start time, please try again: ");
            }
        }

        System.out.print("End time: ");
        int eventEnd = -1;
        while (eventEnd == -1) {
            try {
                eventEnd = scan.nextInt();
                while (eventEnd < 0 || eventEnd > Event.MT || eventEnd <= eventStart) {
                    System.out.print("Invalid end time, please try again: ");
                    eventEnd = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid end time, please try again: ");
            }
        }

        System.out.print("Description: ");
        String eventDesc = scan.next();
        
        Event added = new Event(eventMonth, eventDay, eventYear, eventStart, eventEnd, eventDesc);
        if (conflict(added)) {
            return "Conflict found.";
        }
        planner[next] = added;
        next++;
        return ("Successful addition");
    }

    /**
     * Add events from input file *Call Conflict Method*
     * @param scan scanner to read input file
     */
    public void addInputEvent(Scanner scan) {
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            Scanner in = new Scanner(line);
            in.useDelimiter(",");
            try {
                int month = in.nextInt();
                int day = in.nextInt();
                int year = in.nextInt();
                int start = in.nextInt();
                int end = in.nextInt();
                String desc = in.next();
                try {
                    Event added = new Event(month, day, year, start, end, desc);
                    if (!conflict(added)) {
                        planner[next] = added;
                        next++;
                    }
                } catch (IllegalArgumentException e) {
                    continue;
                }
            } catch (NumberFormatException e) {
                continue;
            }
            in.close();
        }
    }

    /**
     * Method to check for event conflicts
     * @param added event to be checked for conflicts
     * @return true if conflict with another event is found, otherwise false
     */
    public boolean conflict(Event added) {
        int index = 0;
        String eventDate = added.toStringDate();
        int eventStart = added.getStartTime();
        int eventEnd = added.getEndTime();
        while (planner[index] != null) {
            String plan = planner[index].toStringDate();
            if (plan.equals(eventDate)) {
                int planStart = planner[index].getStartTime();
                int planEnd = planner[index].getEndTime();
                if (planStart != 0 && planEnd != Event.MT) {
                    if (eventStart <= planEnd && eventStart >= planStart) {
                        return true;
                    } 
                    if (eventEnd >= planStart && eventEnd <= planEnd) {
                        return true;
                    }
                    if (eventStart <= planStart && eventEnd >= planEnd) {
                        return true;
                    }
                }
            }
            index++;
        }
        return false;
    }

     /**
      * Removes an event from planner array
      * @param scan Scanner for input 
      * @return success message if removal is successful
      */
    public String removeEvent(Scanner scan) {
        scan.useDelimiter("\n");

        System.out.print("Month: ");
        int eventMonth = 0;
        while (eventMonth == 0) {
            try {
                eventMonth = scan.nextInt();
                while (eventMonth > Event.DEC || eventMonth < 1) {
                    System.out.print("Invalid month, please try again: ");
                    eventMonth = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid month, please try again: ");
            }
        }
        System.out.print("Day: ");
        int eventDay = 0;
        while (eventDay == 0) {
            try {
                eventDay = scan.nextInt();
                if (eventMonth == Event.JAN || eventMonth == Event.MAR || eventMonth == Event.MAY 
                    || eventMonth == Event.JUL || eventMonth == Event.AUG || 
                    eventMonth == Event.OCT || eventMonth == Event.DEC) {
                    while (eventDay > Event.BIGM || eventDay < 1) {
                        System.out.print("Invalid day, please try again: ");
                        eventDay = scan.nextInt();
                    }
                }
                if (eventMonth == Event.APR || eventMonth == Event.JUN || eventMonth == Event.SEP 
                    || eventMonth == Event.NOV) {
                    while (eventDay > Event.SMALLM || eventDay < 1) {
                        System.out.print("Invalid day, please try again: ");
                        eventDay = scan.nextInt();
                    }
                }
                if (eventMonth == Event.FEB) {
                    while (eventDay > Event.FEBM || eventDay < 0) {
                        System.out.print("Invalid day, please try again: ");
                        eventDay = scan.nextInt();
                    }
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid day, please try again: ");
            }
        }
        
        System.out.print("Year: ");
        int eventYear = -1;
        while (eventYear == -1){
            try {
                eventYear = scan.nextInt();
                while (eventYear < 0) {
                    System.out.print("Invalid year, please try again: ");
                    eventYear = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid year, please try again: ");
            }
        }
        
        System.out.print("Start time: ");
        int eventStart = -1;
        while (eventStart == -1) {
            try {
                eventStart = scan.nextInt();
                while (eventStart < 0 || eventStart > Event.MT) {
                    System.out.print("Invalid start time, please try again: ");
                    eventStart = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid start time, please try again: ");
            }
        }

        System.out.print("End time: ");
        int eventEnd = -1;
        while (eventEnd == -1) {
            try {
                eventEnd = scan.nextInt();
                while (eventEnd < 0 || eventEnd > Event.MT || eventEnd <= eventStart) {
                    System.out.print("Invalid end time, please try again: ");
                    eventEnd = scan.nextInt();
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.print("Invalid end time, please try again: ");
            }
        }

        System.out.print("Description: ");
        String eventDesc = scan.next();

        Event eventRemove = new Event(eventMonth, eventDay, eventYear, 
                eventStart, eventEnd, eventDesc);
        
        int index = -1;
        for (int i = 0; i < planner.length; i++){
            if (eventRemove.equals(planner[i])) {
                index = i;
            } 
        }
        if (index == -1){
            return ("Event not found.");
        }
        this.next -= 1;
        planner[index] = null;
        return ("Successful removal");

    }

    /**
     * Method to return planner events as string
     * @return String of events in planner
     */
    public String toString() {
        String str = "";
        String date = "";
        for (int i = 0; i < planner.length; i++) {
            if (planner[i] != null) {
                if (!planner[i].toStringDate().equals(date)) {
                    date = planner[i].toStringDate();
                    str += "\n" + date + "\n";
                }
                str += planner[i].toStringEvent() + "\n";
            }
        }
        return str;
    }

    /**
     * Method to return range of events in String
     * @param startDate String of start date
     * @param endDate String of end date
     * @return String of event for range
     */
    public String toStringRange(String startDate, String endDate) {
        int index = 0;
        int startIndex = 0;
        int endIndex = 0;
        boolean startFound = false;
        String str = "";
        String date = "";
        while(planner[index] != null) {
            if (planner[index].toStringDate().equals(startDate) && !startFound) {
                startFound = true;
                startIndex = index;
            } else if (planner[index].toStringDate().equals(endDate)) {
                endIndex = index;
            }
            index++;
        }
        for (int i = startIndex; i <= endIndex; i++) {
            if (!planner[i].toStringDate().equals(date)) {
                date = planner[i].toStringDate();
                str += "\n" + date + "\n";
            }
            str += planner[i].toStringEvent() + "\n";
        }
        return str;
    }
}