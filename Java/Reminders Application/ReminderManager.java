import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;

public class ReminderManager {

    private final Map<Integer, Reminder> reminderMap;

    ReminderManager(){
        this.reminderMap = new HashMap<>();

    }

    public void addReminder(Reminder r){
        reminderMap.put(r.getId(), r);
    }

    public void viewAllReminders(){
        /* Display all the reminders the user has added. Show the date, message, and its recurrence*/
        if(!reminderMap.isEmpty()){
            System.out.println("Reminders: ");
            for(Map.Entry<Integer, Reminder> map : reminderMap.entrySet()){ // iterate through hashmap
                Reminder r = map.getValue();
                r.viewReminder();
            }
        }
        else{
            System.out.println("\nYou have no reminders set.\n");
        }

    }

    public void removeReminder(int id){
        reminderMap.remove(id); // removes r from hashmap
    }

    public void editReminder(int id, String newDescription, LocalDateTime newDate){
        reminderMap.get(id).changeDescription(newDescription);
        reminderMap.get(id).changeDate(newDate);
    }

    public void viewUpcomingReminders(){
        /* A reminder is considered upcoming if the event occurs in 1 week or less*/
        LocalDateTime now = LocalDateTime.now();

        for(Map.Entry<Integer, Reminder> map : reminderMap.entrySet()){
            Reminder r = map.getValue();
            int rWeekOfYear = r.getDateTime().get(WeekFields.of(Locale.getDefault()).weekOfYear()); // get week of the year
            int nowWeekOfYear = now.get(WeekFields.of(Locale.getDefault()).weekOfYear());

            // ensure the years are the same(bc week n can be in any given year)
            if(r.getYear() == now.getYear()){
                if((rWeekOfYear - nowWeekOfYear <= 1) && (rWeekOfYear - nowWeekOfYear >= 0)){
                    r.viewReminder();
                }
            }
        }
    }

    public void viewPastReminders(){
        // display reminders that have passed

        LocalDateTime now = LocalDateTime.now();
        for(Map.Entry<Integer, Reminder> map : reminderMap.entrySet()){
            Reminder r = map.getValue();

            if(r.getDateTime().isBefore(now)){
                r.viewReminder();
            }
        }
    }

    public void viewTodaysReminders(){
        // display today's reminders
        LocalDateTime now = LocalDateTime.now();

        boolean noneToday = true;
            for(Map.Entry<Integer, Reminder> map : reminderMap.entrySet()){
                Reminder r = map.getValue();

                if(r.getDateTime().getDayOfYear() == now.getDayOfYear()){
                    r.viewReminder();
                    noneToday = false;
                }
            }
            if(noneToday){
                System.out.println("\nYou have no reminders for today.\n");
        }

    }

    public Reminder getReminder(int id){
        return reminderMap.get(id);
    }

}
