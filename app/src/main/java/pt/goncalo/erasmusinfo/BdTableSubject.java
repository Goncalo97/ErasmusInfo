package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTableSubject implements BaseColumns {
    public static final String TABLE_NAME = "contact";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_ECTS = "ects";
    public static final String FIELD_DISC_EQUAL = "disc_equal";
    public static final String FIELD_SCORE = "score";
    private SQLiteDatabase db;

    public static final String[] ALL_COLUMNS = new String[] {_ID, FIELD_CODE, FIELD_NAME, FIELD };

    public BdTableSubject(SQLiteDatabase db) {
        this.db = db;
    }

    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FIELD_NAME + " TEXT NOT NULL," +
                        FIELD_COUNTRY + " TEXT NOT NULL," +
                        FIELD_LOCATION + " TEXT NOT NULL" +
                        ")"
        );
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy,
                        String having, String orderBy) {
        return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public long insert(ContentValues values) {
        return db.insert(TABLE_NAME, null, values);
    }

    public int 	update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }
}