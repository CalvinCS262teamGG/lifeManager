package teamgg.cs262.calvin.edu.lifemanager;

public class ScheduleCard {
    String category, description, time;

    public ScheduleCard(String category, String description, String time) {
        this.category = category;
        this.description = description;
        this.time = time;
    }

    public String getCardCategory() {
        return category;
    }

    public String getCardDescription() {
        return description;
    }

    public String getCardTime() {
        return time;
    }
}
