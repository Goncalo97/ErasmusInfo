package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;

public class Contact {

    private long id;
    private String name;
    private String number;
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
    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public long getIdProfile() { return idProfile; }
    public void setIdProfile(long idProfile) { this.idProfile = idProfile; }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DbTableContact.FIELD_NAME, name);
        values.put(DbTableContact.FIELD_NUMBER, number);
        values.put(DbTableContact.FIELD_ID_PROFILE, idProfile);
        return values;
    }

    public static Contact fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(DbTableContact._ID));
        String name = cursor.getString(cursor.getColumnIndex(DbTableContact.FIELD_NAME));
        String number = cursor.getString(cursor.getColumnIndex(DbTableContact.FIELD_NUMBER));
        long idProfile = cursor.getLong(cursor.getColumnIndex(DbTableContact.FIELD_ID_PROFILE));

        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setNumber(number);
        contact.setIdProfile(idProfile);

        return contact;
    }
}
