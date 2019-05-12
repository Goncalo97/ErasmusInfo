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

    public static Profile fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableProfile._ID));
        String name = cursor.getString(cursor.getColumnIndex(BdTableProfile.FIELD_NAME));
        String age = cursor.getString(cursor.getColumnIndex(BdTableProfile.FIELD_AGE));

        Profile profile = new Profile();
        profile.setId(id);
        profile.setName(name);
        profile.setAge(age);

        return profile;
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
