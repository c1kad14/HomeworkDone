package done.homework.com.homeworkdone.Models;

public class Homework {

    private String id;
    private String text;
    private String subject;
    private String year;
    private String imageUrl;

    public Homework() {
    }

    public Homework(String text, String name, String imageUrl) {
        this.text = text;
        this.subject = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
