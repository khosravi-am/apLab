package khosro.model;

import java.io.Serializable;

/**
 * note contains date, title and context.
 */
public class Note implements Serializable {

    private String title;
    private String content;
    private String date;

    /**
     * make a new note by given parameters
     * @param title title of note
     * @param content context of note
     * @param date date of modifying
     */
    public Note(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }


    /**
     * uses title, contexts and data to make a string.
     * @return string made by field of this class.
     */
    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

