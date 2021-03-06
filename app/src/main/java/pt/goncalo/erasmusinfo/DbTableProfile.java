package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

public class DbTableProfile implements BaseColumns {
    public static final String TABLE_NAME = "profile";

    public static final String FIELD_NAME = "name";
    public static final String FIELD_DATE = "date";

    public static final String[] ALL_COLUMNS = new String[] { _ID, FIELD_NAME, FIELD_DATE };

    private SQLiteDatabase db;

    public DbTableProfile(SQLiteDatabase db) {
        this.db = db;
    }

    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FIELD_NAME + " TEXT NOT NULL," +
                        FIELD_DATE + " TEXT NOT NULL" +
                        ")"
        );
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        String columnsSelect = TextUtils.join(",", columns);
        Log.i("Table Profile", "Query: " + columnsSelect);
        return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public long insert(ContentValues values) {
        return db.insert(TABLE_NAME, null, values);
    }

    public int update(ContentValues values, String whereClause, String [] whereArgs) {
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }
}

