package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;

public class MainCollege {

    private long id;
    private String name;
    private String country;
    private String location;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(BdTableCollege.FIELD_NAME, name);
        values.put(BdTableCollege.FIELD_COUNTRY, country);
        values.put(BdTableCollege.FIELD_LOCATION, location);
        return values;
    }

    public static MainCollege fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableCollege._ID));
        String name = cursor.getString(cursor.getColumnIndex(BdTableCollege.FIELD_NAME));
        String country = cursor.getString(cursor.getColumnIndex(BdTableCollege.FIELD_COUNTRY));
        String location = cursor.getString(cursor.getColumnIndex(BdTableCollege.FIELD_LOCATION));

        MainCollege college = new MainCollege();
        college.setId(id);
        college.setName(name);
        college.setCountry(country);
        college.setCountry(location);

        return college;
    }

    /*
    public MainCollege() {

    }

    public MainCollege(String name, String country, String location) {
        this.name = name;
        this.country = country;
        this.location = location;
    }

    private String name;
    private String country;
    private String location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "MainCollege{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
    */
}


