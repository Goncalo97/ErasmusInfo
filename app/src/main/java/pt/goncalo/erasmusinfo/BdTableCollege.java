package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTableCollege implements BaseColumns {
    public static final String TABLE_NAME = "college";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_COUNTRY = "country";
    public static final String FIELD_LOCATION = "location";
    public static final String FIELD_ID_PROFILE = "idProfile";
    private SQLiteDatabase db;

    public static final String[] ALL_COLUMNS = new String[] { _ID, FIELD_NAME, FIELD_COUNTRY, FIELD_LOCATION, FIELD_ID_PROFILE };

    public BdTableCollege(SQLiteDatabase db) {
        this.db = db;
    }

    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FIELD_NAME + " TEXT NOT NULL," +
                        FIELD_COUNTRY + " TEXT NOT NULL," +
                        FIELD_LOCATION + " TEXT NOT NULL," +
                        FIELD_ID_PROFILE + " INTEGER NOT NULL," +
                        "FOREIGN KEY (" + FIELD_ID_PROFILE + ") REFERENCES " + BdTableProfile.TABLE_NAME + "(" + BdTableProfile._ID + ")" +
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