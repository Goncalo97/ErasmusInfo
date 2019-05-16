package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;

public class MainContact {

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
        values.put(BdTableContact.FIELD_NAME, name);
        values.put(BdTableContact.FIELD_NUMBER, number);
        values.put(BdTableContact.FIELD_ID_PROFILE, idProfile);
        return values;
    }

    public static MainContact fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableContact._ID));
        String name = cursor.getString(cursor.getColumnIndex(BdTableContact.FIELD_NAME));
        String number = cursor.getString(cursor.getColumnIndex(BdTableContact.FIELD_NUMBER));
        long idProfile = cursor.getLong(cursor.getColumnIndex(BdTableContact.FIELD_ID_PROFILE));

        MainContact contact = new MainContact();
        contact.setId(id);
        contact.setName(name);
        contact.setNumber(number);
        contact.setIdProfile(idProfile);

        return contact;
    }
}
