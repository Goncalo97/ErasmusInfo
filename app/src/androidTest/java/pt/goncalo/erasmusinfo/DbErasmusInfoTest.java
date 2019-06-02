package pt.goncalo.erasmusinfo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

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

        // PROFILE
        DbTableProfile tableProfile = new DbTableProfile(db);

        // Test Read Profile (cRud)
        Cursor cursorProfile = getProfile(tableProfile);
        assertEquals(0, cursorProfile.getCount());

        // Test Create/Read Profile (CRud)
        String name = "John Doe";
        String date = "19/05/1994";

        long idProfile1 = createProfile(tableProfile, name, date);
        cursorProfile = getProfile(tableProfile);
        assertEquals(1, cursorProfile.getCount());

        Profile profile = getProfileWithID(cursorProfile, idProfile1);
        assertEquals(name, profile.getName());
        assertEquals(date, profile.getDate());

        name = "John Wayne";
        date = "15/03/1999";

        long idProfile2 = createProfile(tableProfile, name, date);
        cursorProfile = getProfile(tableProfile);
        assertEquals(2, cursorProfile.getCount());

        profile = getProfileWithID(cursorProfile, idProfile2);
        assertEquals(name, profile.getName());
        assertEquals(date, profile.getDate());

        // Test Update/Read (cRUd)
        name = "Luke Bart";
        date = "23/10/1996";

        profile.setName(name);
        profile.setDate(date);

        int alteredRecords = tableProfile.update(profile.getContentValues(), DbTableProfile._ID + "=?", new String[]{String.valueOf(idProfile1)});

        assertEquals(1, alteredRecords);

        cursorProfile = getProfile(tableProfile);
        profile = getProfileWithID(cursorProfile, idProfile1);

        assertEquals(name, profile.getName());

        // Test Create/Delete/Read Profile (CRuD)
        long id = createProfile(tableProfile, "John Doe Test", "16/05/1998");
        cursorProfile = getProfile(tableProfile);
        assertEquals(3, cursorProfile.getCount());

        tableProfile.delete(DbTableProfile._ID + "=?", new String[]{String.valueOf(id)});
        cursorProfile = getProfile(tableProfile);
        assertEquals(2, cursorProfile.getCount());

        getProfileWithID(cursorProfile, idProfile1);
        getProfileWithID(cursorProfile, idProfile2);

        // CONTACT
        DbTableContact tableContact = new DbTableContact(db);

        // Test Read Contact (cRud)
        Cursor cursorContact = getContact(tableContact);
        assertEquals(0, cursorContact.getCount());

        // Test Create/Read Contact (CRud)
        name = "John Doe";
        String number = "987654321";

        long  idContact1 = createContact(tableContact, name, number, idProfile1);
        cursorContact = getContact(tableContact);
        assertEquals(1, cursorContact.getCount());

        Contact contact = getContactWithID(cursorContact, idContact1);
        assertEquals(name, contact.getName());
        assertEquals(number, contact.getNumber());
        assertEquals(idProfile1, contact.getIdProfile());

        name = "John Doe 2";
        number = "322178283";

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
        number = "999113456";

        contact.setName(name);
        contact.setNumber(number);
        contact.setIdProfile(idProfile2);

        tableContact.update(contact.getContentValues(), DbTableContact._ID + "=?", new String[]{String.valueOf(id)});

        cursorContact = getContact(tableContact);

        contact = getContactWithID(cursorContact, id);
        assertEquals(name, contact.getName());
        assertEquals(number, contact.getNumber());
        assertEquals(idProfile2, contact.getIdProfile());

        // Teste Read/Delete Contact (cRuD)
        tableContact.delete(DbTableContact._ID + "=?", new String[]{String.valueOf(id)});
        cursorContact = getContact(tableContact);
        assertEquals(2, cursorContact.getCount());

        // COLLEGE
        DbTableCollege tableCollege= new DbTableCollege(db);

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

        College college = getCollegeWithID(cursorCollege, idCollege1);
        assertEquals(name, college.getName());
        assertEquals(country, college.getCountry());
        assertEquals(location, college.getLocation());
        assertEquals(idProfile1, college.getIdProfile());

        name = "English College 2";
        country = "Englad";
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

        tableCollege.update(college.getContentValues(), DbTableCollege._ID + "=?", new String[]{String.valueOf(id)});

        cursorCollege = getCollege(tableCollege);

        college = getCollegeWithID(cursorCollege, id);
        assertEquals(name, college.getName());
        assertEquals(country, college.getCountry());
        assertEquals(location, college.getLocation());
        assertEquals(idProfile2, college.getIdProfile());

        // Teste Read/Delete College (cRuD)
        tableCollege.delete(DbTableCollege._ID + "=?", new String[]{String.valueOf(id)});
        cursorCollege = getCollege(tableCollege);
        assertEquals(2, cursorCollege.getCount());

        // SUBJECT
        DbTableSubject tableSubject = new DbTableSubject(db);

        // Test Read Subjects (cRud)
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

        Subject subject = getSubjectWithID(cursorSubject, idSubject1);
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

        tableSubject.update(subject.getContentValues(), DbTableSubject._ID + "=?", new String[]{String.valueOf(id)});

        cursorSubject = getSubject(tableSubject);

        subject = getSubjectWithID(cursorSubject, id);
        assertEquals(code, subject.getCode());
        assertEquals(name, subject.getName());
        assertEquals(ects, subject.getECTS());
        assertEquals(equalSubject, subject.getEqualSubject());
        assertEquals(score, subject.getScore());
        assertEquals(idCollege2, subject.getIdCollege());

        // Teste Read/Delete Subject (cRuD)
        tableSubject.delete(DbTableSubject._ID + "=?", new String[]{String.valueOf(id)});
        cursorSubject = getSubject(tableSubject);
        assertEquals(2, cursorSubject.getCount());

    }

    // PROFILE
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

    // COLLEGE
    private long createCollege(DbTableCollege tableCollege, String name, String country, String location, long idProfile) {
        College college = new College();
        college.setName(name);
        college.setCountry(country);
        college.setLocation(location);
        college.setIdProfile(idProfile);
        long id = tableCollege.insert(college.getContentValues());
        assertNotEquals(-1, id);
        return id;
    }

    private Cursor getCollege(DbTableCollege tableCollege) {
        return tableCollege.query(DbTableCollege.ALL_COLUMNS, null, null, null, null, null);
    }

    private College getCollegeWithID(Cursor cursor, long id) {
        College college = null;
        while (cursor.moveToNext()) {
            college = College.fromCursor(cursor);
            if (college.getId() == id) {
                break;
            }
        }
        assertNotNull(college);
        return college;
    }

    // SUBJECT
    private long createSubject(DbTableSubject tableSubject, String code, String name, int ects, String equalSubject, String score, long idCollege) {
        Subject subject = new Subject();
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

    private Cursor getSubject(DbTableSubject tableSubject) {
        return tableSubject.query(DbTableSubject.ALL_COLUMNS, null, null, null, null, null);
    }

    private Subject getSubjectWithID(Cursor cursor, long id) {
        Subject subject = null;
        while (cursor.moveToNext()) {
            subject = Subject.fromCursor(cursor);
            if (subject.getId() == id) {
                break;
            }
        }
        assertNotNull(subject);
        return subject;
    }
}