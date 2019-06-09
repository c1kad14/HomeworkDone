package done.homework.com.homeworkdone.Models;

public class Homework {

    private String id;
    private String text;
    private String date;
    private String sentBy;

    public Homework() {
    }

    public Homework(String text, String date, String sentBy) {
        this.text = text;
        this.date = date;
        this.sentBy = sentBy;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
