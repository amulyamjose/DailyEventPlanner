# DailyEventPlanner
- A daily planner that allows users to import events, edit the planner, and output events using Java in a group project
- Applied programming concepts such as object oriented programming, File I/O, and various testing strategies

Link to Report: https://docs.google.com/document/d/1y7BGy3d5Z1L61oXHXbMUx7_4jFOFtgcpWS8T7zbhmuo/edit?usp=sharing


User Instructions:

1. First compile the program in your terminal window by entering: $ javac -d bin -cp bin src/Interface.java
2. To run the program in your terminal window enter: $ java -cp bin Interface
3. If you would like to import an existing file of events enter 'y', otherwise enter 'n'. Input File Event Format: Month,Day,Year,Start time,End time,Description of Event
4. If the file is valid, the menu of options will be displayed. Each option can be entered as a lowercase or uppercase letter. (Ex. 'A' or 'a')
5. Please read the specific instructions for each option type below

Add/Delete

- Month must be entered as an integer 0-12
- Day must be entered as an integer value for a day that exists in the given month
- Year may be entered as any positive integer
- Start time and end time must be entered as integers from 0-23, however end time must be greater than start time
- If an invalid value is entered, the user will be continuously reprompted until a valid value is given.
- For add, if the event overlaps in date and time with a preexisting event, it will not be added and "Conflict found." will be outputted.
- For delete, the values entered must exactly match an existing event or "Event not found." will be outputted and no changes will be made.

Quit

- The user must enter a name for the output file that will be created when the program closes
- If the file exists, the user will be offered to overwrite the existing file
- If an invalid directory is entered, "Cannot create output file" wil be outputted and the program will exit
