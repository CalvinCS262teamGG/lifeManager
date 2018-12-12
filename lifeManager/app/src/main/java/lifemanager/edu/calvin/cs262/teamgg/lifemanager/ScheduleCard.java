package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

public class ScheduleCard {

    private String title, category, description, date, time, startTime, endTime, label, note;

    private int totalHr, totalMin;

    public ScheduleCard(String title, String category, String description, String date, String time, String startTime, String endTime, String label, String note, int totalHr, int totalMin) {
//        this.id = id;
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

    public ScheduleCard () {
        this.title = "Card Title";
        this.category = "Category";
        this.description = "Description";
        this.date = "January 01";
        this.time = "00:00 - 00:00";
        this.startTime = "0000";
        this.endTime = "0000"; // 24hr base
        this.label = "Label";
        this.note = "Note";
        this.totalHr = 0;
        this.totalMin = 0;
    }

    public String getCardTitle() { return title; }

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

    public void setCardDescription(String description) {
        this.description = description;
    }

    public void setCardDate(String date) { this.date = date; }

    public void setCardTime(String time) { this.time = time; }

    public void setCardStartTime(String startTime) { this.startTime = startTime; }

    public void setCardEndTime(String endTime) { this.endTime = endTime; }

    public void setCardLabel(String label) { this.label = label; }

    public void setCardNote(String note) { this.note = note; }

    public void setCardTotalHr(int totalHr) { this.totalHr = totalHr; }

    public void setCardTotalMin(int totalMin) { this.totalMin = totalMin; }
}
