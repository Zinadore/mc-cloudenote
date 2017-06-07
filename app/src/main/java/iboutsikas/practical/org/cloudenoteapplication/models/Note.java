package iboutsikas.practical.org.cloudenoteapplication.models;

import java.util.Date;

/**
 * Created by Zinadore on 6/2/2017.
 */

public class Note {
    private int id;
    private String content;
    private Date date;

    public Note(String content) {
        this.content = content;
    }

    public Note(String content, Date date) {
        this.content = content;
        this.date = date;
    }

    public Note(int id, String content, Date date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public int ID () {
        return this.id;
    }

    public String Content() {
        return  this.content;
    }

    public Date Date() {
        return this.date;
    }
}
