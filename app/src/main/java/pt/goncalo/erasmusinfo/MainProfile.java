package pt.goncalo.erasmusinfo;

import java.util.Date;

public class MainProfile {

    public MainProfile() {

    }

    public MainProfile(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    private String name;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MainProfile{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
