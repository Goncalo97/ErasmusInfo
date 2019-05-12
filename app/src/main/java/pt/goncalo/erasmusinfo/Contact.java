package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

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

    public static Contact fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableContact._ID));
        String name = cursor.getString(cursor.getColumnIndex(BdTableContact.FIELD_NAME));
        String number = cursor.getString(cursor.getColumnIndex(BdTableContact.FIELD_NUMBER));

        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(name);
        contact.setNumber(number);

        return contact;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this,
                "{"+ getString(R.string.contact)+"}",
                Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openContactView(View view) {

        Intent intent = new Intent(this, ContactView.class);
        startActivity(intent);
    }

    public void openContactInsert(View view) {

        Intent intent = new Intent(this, ContactInsert.class);
        startActivity(intent);
    }

    public void openContactEdit(View view) {

        Intent intent = new Intent(this, ContactEdit.class);
        startActivity(intent);
    }

    public void openContactDelete(View view) {

        Intent intent = new Intent(this, ContactDelete.class);
        startActivity(intent);
    }
}
