package pt.goncalo.erasmusinfo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

public class DbTableContact implements BaseColumns {
    public static final String TABLE_NAME = "contact";
    public static final String ALIAS_PROFILE_NAME = "name_profile";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_NUMBER = "number";
    public static final String FIELD_ID_PROFILE = "idProfile";
    public static final String FIELD_NAME_PROFILE = DbTableProfile.TABLE_NAME + "." + DbTableProfile.FIELD_NAME + " AS " + ALIAS_PROFILE_NAME; // tabela de perfis (só de leitura)

    private SQLiteDatabase db;

    public static final String[] ALL_COLUMNS = new String[] { TABLE_NAME + "." + _ID, TABLE_NAME + "." + FIELD_NAME, FIELD_NUMBER, FIELD_ID_PROFILE, FIELD_NAME_PROFILE };

    public DbTableContact(SQLiteDatabase db) {
        this.db = db;
    }

    public void create() {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        FIELD_NAME + " TEXT NOT NULL," +
                        FIELD_NUMBER + " TEXT NOT NULL," +
                        FIELD_ID_PROFILE + " INTEGER NOT NULL," +
                        "FOREIGN KEY (" + FIELD_ID_PROFILE + ") REFERENCES " + DbTableProfile.TABLE_NAME + "(" + DbTableProfile._ID + ")" +
                        ")"
        );
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        String columnsSelect = TextUtils.join(",", columns);
        // return db.query(TABLE_NAME, columns, selection, selectionArgs, groupBy, having, orderBy);
        String sql = "SELECT " + columnsSelect + " FROM " +
                TABLE_NAME + " INNER JOIN " + DbTableProfile.TABLE_NAME + " WHERE " + TABLE_NAME + "." + FIELD_ID_PROFILE + "=" + DbTableProfile.TABLE_NAME+ "." + DbTableProfile._ID
                ;

        if (selection != null) {
            sql += " AND " + selection;
        }

        Log.d("Table Contact", "query: " + sql);

        return db.rawQuery(sql, selectionArgs);
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
