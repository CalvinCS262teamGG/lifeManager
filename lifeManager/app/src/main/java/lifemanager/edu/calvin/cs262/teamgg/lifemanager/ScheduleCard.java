package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

public class ScheduleCard {

    private String title, category, description, date, time, startTime, endTime, label, note;

    private int totalHr, totalMin;

    /**
     * Constructor for Schedule card class
     * @param title == title of card
     * @param category == category of the card
     * @param description == A short description if necessary
     * @param date == Full format of date of card
     * @param time == full format of time of card
     * @param startTime ==  short format of start time in 24hr time
     * @param endTime == short format of end time in 24hr time
     * @param label == label of card if necessary
     * @param note == notes if necessary
     * @param totalHr == total hours spent on activity
     * @param totalMin == total minutes spent on activity
     */
    public ScheduleCard(String title, String category, String description, String date, String time, String startTime, String endTime, String label, String note, int totalHr, int totalMin) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
        this.label = label;
        this.note = note;
        this.totalHr = totalHr;
        this.totalMin = totalMin;
    }

    /**
     * These are all of our getters and setters
     */
    public String getCardTitle() {
        return title;
    }

    public String getCardCategory() {
        return category;
    }

    public String getCardDescription() {
        return description;
    }

    public String getCardDate() {
        return date;
    }

    public String getCardTime() {
        return time;
    }

    public String getCardStartTime() {
        return startTime;
    }

    public String getCardEndTime() {
        return endTime;
    }

    public String getCardLabel() {
        return label;
    }

    public String getCardNote() { return note; }

    public int getCardTotalHr() { return totalHr; }

    public int getCardTotalMin() { return totalMin; }

    public void setCardTitle(String title) { this.title = title; }

    public void setCardCategory(String category) { this.category = category;}

    public void setCardDescription(String description) { this.description = description; }

    public void setCardDate(String date) { this.date = date; }

    public void setCardTime(String time) { this.time = time; }

    public void setCardStartTime(String startTime) { this.startTime = startTime; }

    public void setCardEndTime(String endTime) { this.endTime = endTime; }

    public void setCardLabel(String label) { this.label = label; }

    public void setCardNote(String note) { this.note = note; }

    public void setCardTotalHr(int totalHr) { this.totalHr = totalHr; }

    public void setCardTotalMin(int totalMin) { this.totalMin = totalMin; }
}
