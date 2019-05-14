package pt.goncalo.erasmusinfo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BdErasmusInfoTest {

    @Before
    public void eraseDataBase() {
        getAppContext().deleteDatabase(BdErasmusInfoOpenHelper.DATABASE_NAME);
    }

    @Test
    public void createDataBaseErasmusInfo() {
        // Context of the app under test.
        Context appContext = getAppContext();

        BdErasmusInfoOpenHelper openHelper = new BdErasmusInfoOpenHelper(appContext);
        SQLiteDatabase bd = openHelper.getReadableDatabase();

        assertTrue(bd.isOpen());

        assertEquals("pt.goncalo.erasmusinfo", appContext.getPackageName());
    }

    private Context getAppContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testCRUD() {
        BdErasmusInfoOpenHelper openHelper = new BdErasmusInfoOpenHelper(getAppContext());
        SQLiteDatabase db = openHelper.getWritableDatabase();

        BdTableProfile tableProfile = new BdTableProfile(db);
        MainProfile profile = new MainProfile();
        profile.setName("John Doe");
        profile.setAge("12/05/2019 10:10:10");
        long idProfile1 = tableProfile.insert(profile.getContentValues());
        assertNotEquals(-1, idProfile1);
        profile = new MainProfile();
        profile.setName("John Doe 2");
        profile.setAge("12/05/2019 20:20:20");
        long idProfile2 = tableProfile.insert(profile.getContentValues());
        assertNotEquals(-1, idProfile2);

        BdTableContact tableContact = new BdTableContact(db);
        MainContact contact = new MainContact();
        contact.setName("John Doe");
        contact.setNumber("987654321");
        long idContact1 = tableContact.insert(contact.getContentValues());
        assertNotEquals(-1, idContact1);

        BdTableCollege tableCollege = new BdTableCollege(db);
        MainCollege college = new MainCollege();
        college.setName("French College");
        college.setCountry("France");
        college.setLocation("SouthWest");
        long idCollege1 = tableCollege.insert(college.getContentValues());
        assertNotEquals(-1, idCollege1);

        BdTableSubject tableSubject = new BdTableSubject(db);
        MainSubject subject = new MainSubject();
        subject.setCode("UINF8800");
        subject.setName("Advanced Programming");
        subject.setEcts(6);
        subject.setEqualSubject("Programação Avançada");
        subject.setScore("B");
        long idSubject1 = tableSubject.insert(subject.getContentValues());
        assertNotEquals(-1, idSubject1);
    }

    // PROFILE

    private long createProfile(BdTableProfile tableProfile, String name, String dateOfBirth) {
        MainProfile profile = new MainProfile();
        profile.setName(name);
        profile.setAge(dateOfBirth);
        long id = tableProfile.insert(profile.getContentValues());
        assertNotEquals(-1, id);
        return id;
    }

    private Cursor getProfile(BdTableProfile tableProfile) {
        return tableProfile.query(BdTableProfile.ALL_COLUMNS, null, null, null, null, null);
    }

    private MainProfile getProfileWithID(Cursor cursor, long id) {
        MainProfile profile = null;

        while (cursor.moveToNext()) {
            profile = MainProfile.fromCursor(cursor);
            if (profile.getId() == id) {
                break;
            }
        }

        assertNotNull(profile);

        return profile;
    }

    // CONTACT

    private long createContact(BdTableContact tableContact, String name, String number) {
        MainContact contact = new MainContact();
        contact.setName(name);
        contact.setNumber(number);
        long id = tableContact.insert(contact.getContentValues());
        assertNotEquals(-1, id);
        return id;
    }

    private Cursor getContact(BdTableContact tableContact) {
        return tableContact.query(BdTableContact.ALL_COLUMNS, null, null, null, null, null);
    }

    private MainContact getContactWithID(Cursor cursor, long id) {
        MainContact contact = null;

        while (cursor.moveToNext()) {
            contact = MainContact.fromCursor(cursor);
            if (contact.getId() == id) {
                break;
            }
        }

        assertNotNull(contact);

        return contact;
    }

    // COLLEGE

    private long createCollege(BdTableCollege tableCollege, String name, String country, String location) {
        MainCollege college = new MainCollege();
        college.setName(name);
        college.setCountry(country);
        college.setLocation(location);
        long id = tableCollege.insert(college.getContentValues());
        assertNotEquals(-1, id);
        return id;
    }

    private Cursor getCollege(BdTableCollege tableCollege) {
        return tableCollege.query(BdTableCollege.ALL_COLUMNS, null, null, null, null, null);
    }

    private MainCollege getCollegeWithID(Cursor cursor, long id) {
        MainCollege college = null;

        while (cursor.moveToNext()) {
            college = MainCollege.fromCursor(cursor);
            if (college.getId() == id) {
                break;
            }
        }

        assertNotNull(college);

        return college;
    }


    // SUBJECT

    private long createSubject(BdTableSubject tableSubject, String code, String name, int ects, String equalSubject, String score) {
        MainSubject subject = new MainSubject();
        subject.setCode(code);
        subject.setName(name);
        subject.setEcts(ects);
        subject.setEqualSubject(equalSubject);
        subject.setScore(score);
        long id = tableSubject.insert(subject.getContentValues());
        assertNotEquals(-1, id);
        return id;
    }

    private Cursor getSubject(BdTableSubject tableSubject) {
        return tableSubject.query(BdTableSubject.ALL_COLUMNS, null, null, null, null, null);
    }

    private MainSubject getSubjectWithID(Cursor cursor, long id) {
        MainSubject subject = null;

        while (cursor.moveToNext()) {
            subject = MainSubject.fromCursor(cursor);
            if (subject.getId() == id) {
                break;
            }
        }

        assertNotNull(subject);

        return subject;
    }

}
