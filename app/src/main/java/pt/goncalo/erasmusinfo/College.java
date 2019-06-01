package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;

public class College {

    private long id;
    private String name;
    private String country;
    private String location;
    private long idProfile;

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

    public long getIdProfile() { return idProfile; }
    public void setIdProfile(long idProfile) { this.idProfile = idProfile; }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DbTableCollege.FIELD_NAME, name);
        values.put(DbTableCollege.FIELD_COUNTRY, country);
        values.put(DbTableCollege.FIELD_LOCATION, location);
        values.put(DbTableCollege.FIELD_ID_PROFILE, idProfile);
        return values;
    }

    public static College fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(DbTableCollege._ID));
        String name = cursor.getString(cursor.getColumnIndex(DbTableCollege.FIELD_NAME));
        String country = cursor.getString(cursor.getColumnIndex(DbTableCollege.FIELD_COUNTRY));
        String location = cursor.getString(cursor.getColumnIndex(DbTableCollege.FIELD_LOCATION));
        long idProfile = cursor.getLong(cursor.getColumnIndex(DbTableCollege.FIELD_ID_PROFILE));

        College college = new College();
        college.setId(id);
        college.setName(name);
        college.setCountry(country);
        college.setLocation(location);
        college.setIdProfile(idProfile);

        return college;
    }
}


