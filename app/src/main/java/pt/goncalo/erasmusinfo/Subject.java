package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class Subject extends AppCompatActivity {

    private long id;
    private String code;
    private String name;
    private int ects;
    private String equal_subject;
    private String score;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public int getECTS() {
        return ects;
    }
    public String getEqualSubject() {
        return equal_subject;
    }
    public String getScore() {
        return score;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEcts(int ects) {
        this.ects = ects;
    }
    public void setEqualSubject(String equal_subject) {
        this.equal_subject = equal_subject;
    }
    public void setScore(String score) {
        this.score = score;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(BdTableSubject.FIELD_CODE, code);
        values.put(BdTableSubject.FIELD_NAME, name);
        values.put(BdTableSubject.FIELD_ECTS, ects);
        values.put(BdTableSubject.FIELD_EQUAL_SUBJECT, equal_subject);
        values.put(BdTableSubject.FIELD_SCORE, score);
        return values;
    }

    public static Subject fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableSubject._ID));
        String code = cursor.getString(cursor.getColumnIndex(BdTableSubject.FIELD_CODE));
        String name = cursor.getString(cursor.getColumnIndex(BdTableSubject.FIELD_NAME));
        int ects = cursor.getInt(cursor.getColumnIndex(BdTableSubject.FIELD_ECTS));
        String equal_subject = cursor.getString(cursor.getColumnIndex(BdTableSubject.FIELD_EQUAL_SUBJECT));
        String score = cursor.getString(cursor.getColumnIndex(BdTableSubject.FIELD_SCORE));

        Subject subject = new Subject();
        subject.setId(id);
        subject.setCode(code);
        subject.setName(name);
        subject.setEcts(ects);
        subject.setEqualSubject(equal_subject);
        subject.setScore(score);

        return subject;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this,
                "{"+ getString(R.string.subject)+"}",
                Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openSubjectInsert(View view) {

        Intent intent = new Intent(this, SubjectInsert.class);
        startActivity(intent);
    }

    public void openSubjectEdit(View view) {

        Intent intent = new Intent(this, SubjectEdit.class);
        startActivity(intent);
    }

    public void openSubjectDelete(View view) {

        Intent intent = new Intent(this, SubjectDelete.class);
        startActivity(intent);
    }
}
