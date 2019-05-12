package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;

public class MainContact {

    private long id;
    private String name;
    private String number;

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

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(BdTableContact.FIELD_NAME, name);
        values.put(BdTableContact.FIELD_NUMBER, number);
        return values;
    }

    public static MainContact fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableContact._ID));
        String name = cursor.getString(cursor.getColumnIndex(BdTableContact.FIELD_NAME));
        String number = cursor.getString(cursor.getColumnIndex(BdTableContact.FIELD_NUMBER));

        MainContact contact = new MainContact();
        contact.setId(id);
        contact.setName(name);
        contact.setNumber(number);

        return contact;
    }

    /*
    public MainContact() {

    }

    public MainContact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    private String name;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "MainContact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
    */
}
