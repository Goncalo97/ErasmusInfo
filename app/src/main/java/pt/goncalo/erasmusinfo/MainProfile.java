package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;

public class MainProfile {


    private long id;
    private String name;
    private String age;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(BdTableProfile.FIELD_NAME, name);
        values.put(BdTableProfile.FIELD_AGE, age);
        return values;
    }

    public static MainProfile fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableProfile._ID));
        String name = cursor.getString(cursor.getColumnIndex(BdTableProfile.FIELD_NAME));
        String age = cursor.getString(cursor.getColumnIndex(BdTableProfile.FIELD_AGE));

        MainProfile profile = new MainProfile();
        profile.setId(id);
        profile.setName(name);
        profile.setAge(age);

        return profile;
    }

    /*
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
    */
}
