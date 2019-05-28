package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;

public class Profile {
    private long id;
    private String name;
    private String date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DbTableProfile.FIELD_NAME, name);
        values.put(DbTableProfile.FIELD_DATE, date);
        return values;
    }

    public static Profile fromCursor(Cursor cursor) {
        long id = cursor.getLong(
                cursor.getColumnIndex(DbTableProfile._ID)
        );

        String name = cursor.getString(
                cursor.getColumnIndex(DbTableProfile.FIELD_NAME)
        );

        String date = cursor.getString(
                cursor.getColumnIndex(DbTableProfile.FIELD_DATE)
        );

        Profile profile = new Profile();

        profile.setId(id);
        profile.setName(name);
        profile.setDate(date);
        return profile;
    }
}