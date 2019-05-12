package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class College extends AppCompatActivity {

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

    public static College fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableCollege._ID));
        String name = cursor.getString(cursor.getColumnIndex(BdTableCollege.FIELD_NAME));
        String country = cursor.getString(cursor.getColumnIndex(BdTableCollege.FIELD_COUNTRY));
        String location = cursor.getString(cursor.getColumnIndex(BdTableCollege.FIELD_LOCATION));

        College college = new College();
        college.setId(id);
        college.setName(name);
        college.setCountry(country);
        college.setCountry(location);

        return college;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this,
                "{"+ getString(R.string.college)+"}",
                Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openCollegeInsert(View view) {

        Intent intent = new Intent(this, CollegeInsert.class);
        startActivity(intent);
    }

    public void openCollegeEdit(View view) {

        Intent intent = new Intent(this, CollegeEdit.class);
        startActivity(intent);
    }

    public void openCollegeDelete(View view) {

        Intent intent = new Intent(this, CollegeDelete.class);
        startActivity(intent);
    }
}
