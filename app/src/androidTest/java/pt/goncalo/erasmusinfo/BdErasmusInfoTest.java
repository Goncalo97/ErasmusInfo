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

        // Test Read Profile (cRud)
        Cursor cursorProfile = getProfile(tableProfile);
        assertEquals(0, cursorProfile.getCount());

        // Test Create/Read Profile (CRud)
        String name = "John Doe";
        String dateOfBirth = "12/05/2019 10:10:10";
        long idProfile1 = createProfile(tableProfile, name, dateOfBirth);
        cursorProfile = getProfile(tableProfile);
        assertEquals(1, cursorProfile.getCount());

        MainProfile profile = getProfileWithID(cursorProfile, idProfile1);

        assertEquals(name, profile.getName());
        assertEquals(dateOfBirth, profile.getAge());

        name = "John Doe 2";
        long idProfile2 = createProfile(tableProfile, name, dateOfBirth);

        cursorProfile = getProfile(tableProfile);
        assertEquals(2, cursorProfile.getCount());

        profile = getProfileWithID(cursorProfile, idProfile2);

        assertEquals(name, profile.getName());
        assertEquals(dateOfBirth, profile.getAge());

        // Test Update/Read (cRUd)
        name = "John Doe Update";
        profile.setName(name);

        int alteredRecords = tableProfile.update(profile.getContentValues(), BdTableProfile._ID + "=?", new String[]{String.valueOf(idProfile1)});

        assertEquals(1, alteredRecords);

        cursorProfile = getProfile(tableProfile);
        profile = getProfileWithID(cursorProfile, idProfile1);

        assertEquals(name, profile.getName());

        // Test Create/Delete/Read Profile (CRuD)
        long id = createProfile(tableProfile, "John Doe Test", "16/05/2019 21:09:00");
        cursorProfile = getProfile(tableProfile);
        assertEquals(3, cursorProfile.getCount());

        tableProfile.delete(BdTableProfile._ID + "=?", new String[]{String.valueOf(id)});
        cursorProfile = getProfile(tableProfile);
        assertEquals(2, cursorProfile.getCount());

        getProfileWithID(cursorProfile, idProfile1);
        getProfileWithID(cursorProfile, idProfile2);

        /*

        MainContact contact = new MainContact();
        contact.setName("John Doe");
        contact.setNumber("987654321");
        long idContact1 = tableContact.insert(contact.getContentValues());
        assertNotEquals(-1, idContact1);
        */

        BdTableContact tableContact = new BdTableContact(db);
        // Test Read Contact (cRud)
        Cursor cursorContact = getContact(tableContact);
        assertEquals(0, cursorContact.getCount());

        // Test Create/Read Contact (CRud)
        name = "John Doe";
        String number = "987654321";

       long  idContact1 = createContact(tableContact, name, number, idProfile1);
        cursorContact = getContact(tableContact);
        assertEquals(1, cursorContact.getCount());

        MainContact contact = getContactWithID(cursorContact, idContact1);
        assertEquals(name, contact.getName());
        assertEquals(number, contact.getNumber());
        assertEquals(idProfile1, contact.getIdProfile());

        name = "John Doe 2";
        number = "123456789";

        long idContact2 = createContact(tableContact, name, number, idProfile2);
        cursorContact = getContact(tableContact);
        assertEquals(2, cursorContact.getCount());

        contact = getContactWithID(cursorContact, idContact2);
        assertEquals(name, contact.getName());
        assertEquals(number, contact.getNumber());
        assertEquals(idProfile2, contact.getIdProfile());

        id = createContact(tableContact, "Teste", "111111111", idProfile1);
        cursorContact = getContact(tableContact);
        assertEquals(3, cursorContact.getCount());

        // Test Read/Update Contact (cRUd)
        contact = getContactWithID(cursorContact, id);
        name = "John Doe Update";
        number = "333333333";

        contact.setName(name);
        contact.setNumber(number);
        contact.setIdProfile(idProfile2);

        tableContact.update(contact.getContentValues(), BdTableContact._ID + "=?", new String[]{String.valueOf(id)});

        cursorContact = getContact(tableContact);

        contact = getContactWithID(cursorContact, id);
        assertEquals(name, contact.getName());
        assertEquals(number, contact.getNumber());
        assertEquals(idProfile2, contact.getIdProfile());

        // Teste Read/Delete Contact (cRuD)
        tableContact.delete(BdTableContact._ID + "=?", new String[]{String.valueOf(id)});
        cursorContact = getContact(tableContact);
        assertEquals(2, cursorContact.getCount());

        /*
        BdTableCollege tableCollege = new BdTableCollege(db);
        MainCollege college = new MainCollege();
        college.setName("French College");
        college.setCountry("France");
        college.setLocation("SouthWest");
        long idCollege1 = tableCollege.insert(college.getContentValues());
        assertNotEquals(-1, idCollege1);
        */

        BdTableCollege tableCollege = new BdTableCollege(db);
        // Test Read College (cRud)
        Cursor cursorCollege = getCollege(tableCollege);
        assertEquals(0, cursorCollege.getCount());

        // Test Create/Read College (CRud)
        name = "French College";
        String country = "France";
        String location = "SouthWest";

        long idCollege1 = createCollege(tableCollege, name, country, location, idProfile1);
        cursorCollege = getCollege(tableCollege);
        assertEquals(1, cursorCollege.getCount());

        MainCollege college = getCollegeWithID(cursorCollege, idCollege1);
        assertEquals(name, college.getName());
        assertEquals(country, college.getCountry());
        assertEquals(location, college.getLocation());
        assertEquals(idProfile1, college.getIdProfile());

        name = "French College 2";
        country = "France";
        location = "SouthWest";

        long idCollege2 = createCollege(tableCollege, name, country, location, idProfile2);
        cursorCollege = getCollege(tableCollege);
        assertEquals(2, cursorCollege.getCount());

        college = getCollegeWithID(cursorCollege, idCollege2);
        assertEquals(name, college.getName());
        assertEquals(country, college.getCountry());
        assertEquals(location, college.getLocation());
        assertEquals(idProfile2, college.getIdProfile());

        id = createCollege(tableCollege, "Test Name", "Test Country", "Test Location", idProfile1);
        cursorCollege = getCollege(tableCollege);
        assertEquals(3, cursorCollege.getCount());

        // Test Read/Update College (cRUd)
        college = getCollegeWithID(cursorCollege, id);
        name = "French Update";
        country = "France Update";
        location = "SouthWest Update";

        college.setName(name);
        college.setCountry(country);
        college.setLocation(location);
        college.setIdProfile(idProfile2);

        tableCollege.update(college.getContentValues(), BdTableCollege._ID + "=?", new String[]{String.valueOf(id)});

        cursorCollege = getCollege(tableCollege);

        college = getCollegeWithID(cursorCollege, id);
        assertEquals(name, college.getName());
        assertEquals(country, college.getCountry());
        assertEquals(location, college.getLocation());
        assertEquals(idProfile2, college.getIdProfile());

        // Teste Read/Delete College (cRuD)
        tableCollege.delete(BdTableCollege._ID + "=?", new String[]{String.valueOf(id)});
        cursorCollege = getCollege(tableCollege);
        assertEquals(2, cursorCollege.getCount());

        /*
        BdTableSubject tableSubject = new BdTableSubject(db);
        MainSubject subject = new MainSubject();
        subject.setCode("UINF8800");
        subject.setName("Advanced Programming");
        subject.setEcts(6);
        subject.setEqualSubject("Programação Avançada");
        subject.setScore("B");
        long idSubject1 = tableSubject.insert(subject.getContentValues());
        assertNotEquals(-1, idSubject1);
        */

        BdTableSubject tableSubject = new BdTableSubject(db);
        // Test Read Subject (cRud)
        Cursor cursorSubject = getSubject(tableSubject);
        assertEquals(0, cursorSubject.getCount());

        // Test Create/Read Subject (CRud)
        String code = "UINF8800";
        name = "Advanced Programming";
        int ects = 6;
        String equalSubject = "Programação Avançada";
        String score = "B";

        long idSubject1 = createSubject(tableSubject, code, name, ects, equalSubject, score, idCollege1);
        cursorSubject = getSubject(tableSubject);
        assertEquals(1, cursorSubject.getCount());

        MainSubject subject = getSubjectWithID(cursorSubject, idSubject1);
        assertEquals(code, subject.getCode());
        assertEquals(name, subject.getName());
        assertEquals(ects, subject.getECTS());
        assertEquals(equalSubject, subject.getEqualSubject());
        assertEquals(score, subject.getScore());
        assertEquals(idCollege1, subject.getIdCollege());

        code = "UINF1144";
        name = "Operating System";
        ects = 5;
        equalSubject = "Sistemas Operativos";
        score = "C";

        long idSubject2 = createSubject(tableSubject, code, name, ects, equalSubject, score, idCollege2);
        cursorSubject = getSubject(tableSubject);
        assertEquals(2, cursorSubject.getCount());

        subject = getSubjectWithID(cursorSubject, idSubject2);
        assertEquals(code, subject.getCode());
        assertEquals(name, subject.getName());
        assertEquals(ects, subject.getECTS());
        assertEquals(equalSubject, subject.getEqualSubject());
        assertEquals(score, subject.getScore());
        assertEquals(idCollege2, subject.getIdCollege());

        id = createSubject(tableSubject, "Test Code", "Test Name", 3, "Nome Teste", "A", idCollege1);
        cursorSubject = getSubject(tableSubject);
        assertEquals(3, cursorSubject.getCount());

        // Test Read/Update Subject (cRUd)
        subject = getSubjectWithID(cursorSubject, id);
        code = "UINF1144 Update";
        name = "Operating System Update";
        ects = 1;
        equalSubject = "Sistemas Operativos Update";
        score = "D";

        subject.setCode(code);
        subject.setName(name);
        subject.setEcts(ects);
        subject.setEqualSubject(equalSubject);
        subject.setScore(score);
        subject.setIdCollege(idCollege2);

        tableSubject.update(subject.getContentValues(), BdTableSubject._ID + "=?", new String[]{String.valueOf(id)});

        cursorSubject = getSubject(tableSubject);

        subject = getSubjectWithID(cursorSubject, id);
        assertEquals(code, subject.getCode());
        assertEquals(name, subject.getName());
        assertEquals(ects, subject.getECTS());
        assertEquals(equalSubject, subject.getEqualSubject());
        assertEquals(score, subject.getScore());
        assertEquals(idCollege2, subject.getIdCollege());

        // Teste Read/Delete Subject (cRuD)
        tableSubject.delete(BdTableSubject._ID + "=?", new String[]{String.valueOf(id)});
        cursorSubject = getSubject(tableSubject);
        assertEquals(2, cursorSubject.getCount());

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

    private long createContact(BdTableContact tableContact, String name, String number, long idProfile) {
        MainContact contact = new MainContact();
        contact.setName(name);
        contact.setNumber(number);
        contact.setIdProfile(idProfile);
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

    private long createCollege(BdTableCollege tableCollege, String name, String country, String location, long idProfile) {
        MainCollege college = new MainCollege();
        college.setName(name);
        college.setCountry(country);
        college.setLocation(location);
        college.setIdProfile(idProfile);
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

    private long createSubject(BdTableSubject tableSubject, String code, String name, int ects, String equalSubject, String score, long idCollege) {
        MainSubject subject = new MainSubject();
        subject.setCode(code);
        subject.setName(name);
        subject.setEcts(ects);
        subject.setEqualSubject(equalSubject);
        subject.setScore(score);
        subject.setIdCollege(idCollege);
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
