import java.util.*;
import java.io.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.nio.file.*;

/**
 * Represents the user interface that has access to the planner
 * 
 * @author Mei Etchinson
 * @author Amanda Currie
 * @author Skyler Dunn
 * @author Amulya Jose
 */
public class Interface {
    
    /** Field for planner */
    private Planner planner;

    /**
     * The method that is executed when the program is run
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to your daily planner!");
        Scanner in = new Scanner(System.in);
        Planner planner = new Planner();
        String inputAns = "";
        char ans = '\0';
        do {
            System.out.print("Do you have an input file? (y or n) ");
            inputAns = in.next();
            ans = inputAns.charAt(0);
        } while (ans != 'y' && ans != 'n');
        if (inputAns.charAt(0) == 'y') {
            Scanner input = null;
            while (input == null) {
                System.out.print("Input file: ");
                String inFile = in.next();
                try {
                    input = new Scanner (new FileInputStream(inFile));
                    planner.addInputEvent(input);
                } catch (FileNotFoundException e) {
                    System.out.print("Unable to access input file: " + inFile + "\n");
                }
            }

        } else if (inputAns.charAt(0) == 'n') {
           // no input
        }

        String menuAns = "";
        while (!menuAns.equals("Q")) {
            planner.sort();
            System.out.println("\nMenu:\n");
            System.out.println("V - View events");
            System.out.println("A - Add event");
            System.out.println("D - Delete event");
            System.out.println("Q - Quit\n");
            System.out.print("Option: ");
            menuAns = in.next();
            menuAns = menuAns.toUpperCase();
            if (menuAns.equals("V")) {
                System.out.print("Would you like to view all events or a range? (a or r) ");
                String store = in.next();
                while (!store.equals("a") && !store.equals("r")) {
                    System.out.print("Invalid value. Please try again: ");
                    store = in.next();
                }
                if (store.equals("a")) {
                    System.out.println("\nYour Current Events: ");
                    System.out.println(planner.toString());
                } else if (store.equals ("r")) {
                    System.out.println("Start date below: ");
                
                    System.out.print("Month: ");
                    int eventMonth = 0;
                    while (eventMonth == 0) {
                        try {
                            eventMonth = in.nextInt();
                            while (eventMonth > Event.DEC || eventMonth < 1) {
                                System.out.print("Invalid month, please try again: ");
                                eventMonth = in.nextInt();
                            }
                        } catch (InputMismatchException e) {
                            in.next();
                            System.out.print("Invalid month, please try again: ");
                        }
                    }
                    System.out.print("Day: ");
                    int eventDay = 0;
                    while (eventDay == 0) {
                        try {
                            eventDay = in.nextInt();
                            if (eventMonth == Event.JAN || eventMonth == Event.MAR || 
                                    eventMonth == Event.MAY || eventMonth == Event.JUL || 
                                    eventMonth == Event.AUG || eventMonth == Event.OCT || 
                                    eventMonth == Event.DEC) {
                                while (eventDay > Event.BIGM || eventDay < 1) {
                                    System.out.print("Invalid day, please try again: ");
                                    eventDay = in.nextInt();
                                }
                            }
                            if (eventMonth == Event.APR || eventMonth == Event.JUN || 
                                    eventMonth == Event.SEP || eventMonth == Event.NOV) {
                                while (eventDay > Event.SMALLM || eventDay < 1) {
                                    System.out.print("Invalid day, please try again: ");
                                    eventDay = in.nextInt();
                                }
                            }
                            if (eventMonth == Event.FEB) {
                                while (eventDay > Event.FEBM || eventDay < 0) {
                                    System.out.print("Invalid day, please try again: ");
                                    eventDay = in.nextInt();
                                }
                            }
                        } catch (InputMismatchException e) {
                            in.next();
                            System.out.print("Invalid day, please try again: ");
                        }
                    }
                    
                    System.out.print("Year: ");
                    int eventYear = -1;
                    while (eventYear == -1){
                        try {
                            eventYear = in.nextInt();
                            while (eventYear < 0) {
                                System.out.print("Invalid year, please try again: ");
                                eventYear = in.nextInt();
                            }
                        } catch (InputMismatchException e) {
                            in.next();
                            System.out.print("Invalid year, please try again: ");
                        }
                    }
                    // border between start and end
                    System.out.println("End date below: ");
                    
                    System.out.print("Month: ");
                    int endEventMonth = 0;
                    while (endEventMonth == 0) {
                        try {
                            endEventMonth = in.nextInt();
                            while (endEventMonth > Event.DEC || endEventMonth < 1) {
                                System.out.print("Invalid month, please try again: ");
                                endEventMonth = in.nextInt();
                            }
                        } catch (InputMismatchException e) {
                            in.next();
                            System.out.print("Invalid month, please try again: ");
                        }
                    }
                    System.out.print("Day: ");
                    int endEventDay = 0;
                    while (endEventDay == 0) {
                        try {
                            endEventDay = in.nextInt();
                            if (endEventMonth == Event.JAN || endEventMonth == Event.MAR || 
                                    endEventMonth == Event.MAY || endEventMonth == Event.JUL || 
                                    endEventMonth == Event.AUG || endEventMonth == Event.OCT || 
                                    endEventMonth == Event.DEC) {
                                while (endEventDay > Event.BIGM || endEventDay < 1) {
                                    System.out.print("Invalid day, please try again: ");
                                    endEventDay = in.nextInt();
                                }
                            }
                            if (endEventMonth == Event.APR || endEventMonth == Event.JUN || 
                                    endEventMonth == Event.SEP || endEventMonth == Event.NOV) {
                                while (endEventDay > Event.SMALLM || endEventDay < 1) {
                                    System.out.print("Invalid day, please try again: ");
                                    endEventDay = in.nextInt();
                                }
                            }
                            if (endEventMonth == Event.FEB) {
                                while (endEventDay > Event.FEBM || endEventDay < 0) {
                                    System.out.print("Invalid day, please try again: ");
                                    endEventDay = in.nextInt();
                                }
                            }
                        } catch (InputMismatchException e) {
                            in.next();
                            System.out.print("Invalid day, please try again: ");
                        }
                    }
                    
                    System.out.print("Year: ");
                    int endEventYear = -1;
                    while (endEventYear == -1){
                        try {
                            endEventYear = in.nextInt();
                            while (endEventYear < 0) {
                                System.out.print("Invalid year, please try again: ");
                                endEventYear = in.nextInt();
                            }
                        } catch (InputMismatchException e) {
                            in.next();
                            System.out.print("Invalid year, please try again: ");
                        }
                    }
                    String startDateStr = eventMonth + "\t" + eventDay + "\t" + eventYear;
                    String endDateStr = endEventMonth + "\t" + endEventDay + "\t" + endEventYear;
                    System.out.println(planner.toStringRange(startDateStr, endDateStr));
                }
                

            } else if (menuAns.equals("A")) {
                System.out.println("\n" + planner.addEvent(in));
            } else if (menuAns.equals("D")) {
                System.out.println("\n" + planner.removeEvent(in));
            } else if (menuAns.equals("Q")) {
                System.out.print("Output file name: ");
                String outputFile = in.next();
            
                // if output file already exists
                Path path = Path.of(outputFile);
                if (Files.exists(path)) {
                    System.out.print(outputFile + " exists - OK to overwrite(y,n)?: ");
                    String response = in.next();
                    if ((response.charAt(0) != 'y') && (response.charAt(0) != 'Y')) {
                        System.out.println("File not output");
                        System.exit(1);
                    }
                }
                // if FileNotFoundException happens when opening output file
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(outputFile);
                } catch (FileNotFoundException e) {
                    System.out.print("Cannot create output file");
                    System.exit(1);
                }
                PrintWriter output = new PrintWriter(outputStream);
                outputEvents(output, planner);
                System.exit(1);
            } else {
                System.out.println("\nInvalid option");
            }
        } 
        in.close();
    }

    /**
     * Outputs the information for each event to the file in CSV format
     * @param out PrinWriter to output data to a file
     * @param planner arrays of Events
     * @throws IllegalArgumentException with message "Null file" if out is null
     * @throws IllegalArgumentException with message "Null array" if planner array is null
     */
    public static void outputEvents(PrintWriter out, Planner planner) {
        if (out == null) {
            throw new IllegalArgumentException("Null file");
        }

        if (planner == null) {
            throw new IllegalArgumentException("Null array");
        }

        int index = 0;
        Event eventA = null;
        while (planner.getEvent(index) != null) {
            eventA = planner.getEvent(index);
            out.print(eventA.getMonth() + "," + eventA.getDay() + "," + eventA.getYear() + 
                          "," + eventA.getStartTime() + "," + eventA.getEndTime() + "," + 
                          eventA.getDescription() + "\n");
            index++;
        }

        out.close();
    
    }
}
