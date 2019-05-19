package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;

public class MainSubject {

    private long id;
    private String code;
    private String name;
    private int ects;
    private String equal_subject;
    private String score;
    private long idCollege;

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

    public long getIdCollege() { return idCollege; }
    public void setIdCollege(long idCollege) { this.idCollege = idCollege; }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(BdTableSubject.FIELD_CODE, code);
        values.put(BdTableSubject.FIELD_NAME, name);
        values.put(BdTableSubject.FIELD_ECTS, ects);
        values.put(BdTableSubject.FIELD_EQUAL_SUBJECT, equal_subject);
        values.put(BdTableSubject.FIELD_SCORE, score);
        values.put(BdTableSubject.FIELD_ID_COLLEGE, idCollege);
        return values;
    }

    public static MainSubject fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(BdTableSubject._ID));
        String code = cursor.getString(cursor.getColumnIndex(BdTableSubject.FIELD_CODE));
        String name = cursor.getString(cursor.getColumnIndex(BdTableSubject.FIELD_NAME));
        int ects = cursor.getInt(cursor.getColumnIndex(BdTableSubject.FIELD_ECTS));
        String equal_subject = cursor.getString(cursor.getColumnIndex(BdTableSubject.FIELD_EQUAL_SUBJECT));
        String score = cursor.getString(cursor.getColumnIndex(BdTableSubject.FIELD_SCORE));
        long idCollege = cursor.getLong(cursor.getColumnIndex(BdTableSubject.FIELD_ID_COLLEGE));

        MainSubject subject = new MainSubject();
        subject.setId(id);
        subject.setCode(code);
        subject.setName(name);
        subject.setEcts(ects);
        subject.setEqualSubject(equal_subject);
        subject.setScore(score);
        subject.setIdCollege(idCollege);

        return subject;
    }
}
