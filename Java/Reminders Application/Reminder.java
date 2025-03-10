import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Reminder {

    private final int id;
    private static int idCounter = 1000;
    private String description;
    private LocalDateTime date;
    private final String recurrence;

    Reminder(String description, LocalDateTime date, String recurrence){
        // id is 4 digits
        this.id = idCounter ++; // reminder id
        this.description = description;
        this.date = date;
        this.recurrence = recurrence;
    }

    // getter methods
    public LocalDateTime getDateTime(){
        return date;
    }
    public String getWeekOfDay(){
        /*LocalDateTime stores days in the format yyyy-MM-dd-HH-mm, and we want dd as a String(i.e Tuesday)*/
        // TUESDAY -> Tuesday
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
    public int getId(){
        return id;
    }
    public int getDayOfMonth(){
        return date.getDayOfMonth();
    }
    public String getMonth(){
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }
    public int getYear(){
        return date.getYear();
    }
    public String getDescription(){
        return description;
    }
    public String getRecurrence(){
        return recurrence;
    }

    // setter methods
    public void changeDescription(String newDescription){
        this.description = newDescription;
    }
    public void changeDate(LocalDateTime newDate){
        this.date = newDate;
    }
    public void viewReminder(){
        System.out.printf("%n%d. %s, %s %d, %d: %s (repeat : %s)%n", id, getWeekOfDay(), getMonth(), getDayOfMonth(), getYear(), getDescription(), getRecurrence());
    }


}
