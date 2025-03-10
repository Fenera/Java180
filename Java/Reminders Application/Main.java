import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){

        System.out.println("Welcome to Reminder Manager. Please follow the prompts.");

        ReminderManager manager = new ReminderManager();

        boolean run = true;

        while(run){
            System.out.print("------------------------------------------------------------------------------");
            System.out.printf("%nWould you like to: %n(a) %s %n(b) %s %n(c) %s %n(d) %s %n(e) %s %n(f) %s %n(g) %s %n%s %n%s",
                    "Add a reminder",
                    "View today's reminders",
                    "View all reminders",
                    "Remove a reminder",
                    "Edit a reminder",
                    "View upcoming reminders",
                    "View reminders that have passed",
                    "Type -1 or quit to terminate the program",
                    "Please enter the letter that corresponds to the response: ");

            String response = input.nextLine();

            switch(response.toLowerCase()){

                case "a":
                    System.out.print("\nEnter a description for your reminder: ");
                    String description = input.nextLine();

                    System.out.print("\nEnter a date and time for your reminder(i.e 2025-07-23 3:00 PM): ");
                    String dateTime = input.nextLine();

                    System.out.print("\nEnter the recurrence of the event(Daily, Weekly, Monthly...): ");
                    String recurrence = input.nextLine();

                    // add reminder object to the manager
                    Reminder r = new Reminder(description, convertLDT(dateTime), recurrence);
                    manager.addReminder(r);
                    break;

                case "b":
                    manager.viewTodaysReminders();
                    break;

                case "c":
                    manager.viewAllReminders();
                    break;

                case "d":
                    System.out.print("\nEnter the id of the reminder you'd like to remove: ");
                    int removeID = input.nextInt();

                    manager.removeReminder(removeID);
                    break;

                case "e": // edit a reminder
                    System.out.println("Would you like to edit the (a) description, (b) date or (c) both: ");
                    String choiceEdit = input.nextLine();

                    System.out.print("\nEnter the id of the reminder you'd like to edit: ");
                    int editID = input.nextInt();

                    switch(choiceEdit.toLowerCase()){
                        case "a": // edit only the description
                            System.out.print("\nEnter the new description: ");
                            String newDesc = input.nextLine();

                            manager.editReminder(editID, newDesc, manager.getReminder(editID).getDateTime());
                            break;

                        case "b": // edit only the date
                            System.out.print("\nEnter the new date(i.e 2025-07-23 3:00 PM): ");
                            String newDate = input.nextLine();

                            manager.editReminder(editID, manager.getReminder(editID).getDescription(), convertLDT(newDate));
                            break;

                        case "c": // edit both the description and the date
                            System.out.print("\nEnter the new description: ");
                            String newDesc2 = input.nextLine();

                            System.out.print("\nEnter the new date(i.e 2025-07-23 3:00 PM): ");
                            String newDate2 = input.nextLine();

                            manager.editReminder(editID, newDesc2, convertLDT(newDate2));
                            break;
                    }
                    break;

                case "f":
                    manager.viewUpcomingReminders();
                    break;
                case "g":
                    manager.viewPastReminders();
                    break;
                case "-1":
                case "quit":
                    run = false;
                    break;

            }
        }
    }

    public static LocalDateTime convertLDT(String date){
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");

        try{
            return LocalDateTime.parse(date, parser); // returns the users date as LocalDateTime object
        }
        catch (DateTimeParseException e){
            System.err.println("Please enter the correct date format: " + e.getMessage());
            return null;
        }
    }
}
