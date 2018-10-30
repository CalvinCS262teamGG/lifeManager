package lifemanager.edu.calvin.cs262.teamgg.lifemanager;

public class ScheduleCard {

    private String id, title, category, description, date, time, label, note;

    public ScheduleCard(String id, String title, String category, String description, String date, String time, String label, String note) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.date = date;
        this.time = time;
        this.label = label;
        this.note = note;
    }

    public ScheduleCard () {
        this.id = "ID";
        this.title = "Card Title";
        this.category = "Category";
        this.description = "Description";
        this.date = "January 01";
        this.time = "00:00";
        this.label = "Label";
        this.note = "Note";
    }
    public String getCardId() { return id; }

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

    public String getCardLabel() {
        return label;
    }

    public String getCardNote() {
        return note;
    }

}
