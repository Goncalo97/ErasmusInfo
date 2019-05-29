package pt.goncalo.erasmusinfo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class DbErasmusInfoTest {
    @Before
    public void deleteDataBase() {
        getAppContext().deleteDatabase(DbErasmusInfoOpenHelper.DATA_BASE_NAME);
    }

    @Test
    public void createDbProfile() {
        // Context of the app under test.
        Context appContext = getAppContext();

        DbErasmusInfoOpenHelper openHelper = new DbErasmusInfoOpenHelper(appContext);

        SQLiteDatabase db = openHelper.getReadableDatabase();

        assertTrue(db.isOpen());
    }

    private Context getAppContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testCRUD() {
        DbErasmusInfoOpenHelper openHelper = new DbErasmusInfoOpenHelper(getAppContext());
        SQLiteDatabase db = openHelper.getWritableDatabase();

        DbTableProfile tableProfile = new DbTableProfile(db);

        // Test Read Profile (cRud)
        Cursor cursorProfile = getProfile(tableProfile);
        assertEquals(0, cursorProfile.getCount());

        // Test Create/Read Profile (CRud)
        String name = "John Doe";
        String date = "19/05/1994";

        long id = createProfile(tableProfile, name, date);
        cursorProfile = getProfile(tableProfile);
        assertEquals(1, cursorProfile.getCount());

        Profile profile = getProfileWithID(cursorProfile, id);
        assertEquals(name, profile.getName());
        assertEquals(date, profile.getDate());

        name = "Nobody";
        date = "15/03/1999";

        id = createProfile(tableProfile, name, date);
        cursorProfile = getProfile(tableProfile);
        assertEquals(2, cursorProfile.getCount());

        profile = getProfileWithID(cursorProfile, id);
        assertEquals(name, profile.getName());
        assertEquals(date, profile.getDate());

        id = createProfile(tableProfile, "Teste", "01/01/2001");
        cursorProfile = getProfile(tableProfile);
        assertEquals(3, cursorProfile.getCount());

        // Test Read/Update Profile (cRUd)
        profile = getProfileWithID(cursorProfile, id);
        name = "Nameless";
        date = "20/09/1987";

        profile.setName(name);
        profile.setDate(date);

        tableProfile.update(profile.getContentValues(), DbTableProfile._ID + "=?", new String[]{String.valueOf(id)});

        cursorProfile = getProfile(tableProfile);

        profile = getProfileWithID(cursorProfile, id);
        assertEquals(name, profile.getName());
        assertEquals(date, profile.getDate());

        // Teste Read/Delete Profile (cRuD)
        tableProfile.delete(DbTableProfile._ID + "=?", new String[]{String.valueOf(id)});
        cursorProfile = getProfile(tableProfile);
        assertEquals(2, cursorProfile.getCount());

        DbTableContact tableContact = new DbTableContact(db);

        // Test Read Contact (cRud)
        Cursor cursorContact = getContact(tableContact);
        assertEquals(0, cursorContact.getCount());

        // Test Create/Read Contact (CRud)
        name = "John Doe";
        String number = "12333333";

        id = createContact(tableContact, name, number, 1);
        cursorContact = getContact(tableContact);
        assertEquals(1, cursorContact.getCount());

        Contact contact = getContactWithID(cursorContact, id);
        assertEquals(name, contact.getName());
        assertEquals(number, contact.getNumber());
    }

    private long createProfile(DbTableProfile tableProfile, String name, String date) {
        Profile profile = new Profile();

        profile.setName(name);
        profile.setDate(date);

        long id = tableProfile.insert(profile.getContentValues());
        assertNotEquals(-1, id);

        return id;
    }

    private Cursor getProfile(DbTableProfile tableProfile) {
        return tableProfile.query(DbTableProfile.ALL_COLUMNS, null, null, null, null, null);
    }

    private Profile getProfileWithID(Cursor cursor, long id) {
        Profile profile = null;

        while (cursor.moveToNext()) {
            profile = Profile.fromCursor(cursor);

            if (profile.getId() == id) {
                break;
            }
        }

        assertNotNull(profile);

        return profile;
    }

    // CONTACT
    private long createContact(DbTableContact tableContact, String name, String number, long idProfile) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setNumber(number);
        contact.setIdProfile(idProfile);
        long id = tableContact.insert(contact.getContentValues());
        assertNotEquals(-1, id);
        return id;
    }

    private Cursor getContact(DbTableContact tableContact) {
        return tableContact.query(DbTableContact.ALL_COLUMNS, null, null, null, null, null);
    }

    private Contact getContactWithID(Cursor cursor, long id) {
        Contact contact = null;
        while (cursor.moveToNext()) {
            contact = Contact.fromCursor(cursor);
            if (contact.getId() == id) {
                break;
            }
        }
        assertNotNull(contact);
        return contact;
    }
}