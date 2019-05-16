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

        id = createContact(tableContact, name, number);
        cursorContact = getContact(tableContact);
        assertEquals(1, cursorContact.getCount());

        MainContact contact = getContactWithID(cursorContact, id);
        assertEquals(name, contact.getName());
        assertEquals(number, contact.getNumber());



        /*
        // Teste create/read livros (CRud)
        String titulo = "Dom Quixote";
        int pagina = 23;

        id = criaLivro(tabelaLivros, titulo, pagina ,idRomance);
        cursorLivros = getLivros(tabelaLivros);
        assertEquals(1, cursorLivros.getCount());

        Livro livro = getLivroComID(cursorLivros, id);
        assertEquals(titulo, livro.getTitulo());
        assertEquals(pagina, livro.getPagina());
        assertEquals(idRomance, livro.getCategoria());

        titulo = "Fahrenheit 451";
        pagina = 1;

        id = criaLivro(tabelaLivros, titulo, pagina ,idSuspense);
        cursorLivros = getLivros(tabelaLivros);
        assertEquals(2, cursorLivros.getCount());

        livro = getLivroComID(cursorLivros, id);
        assertEquals(titulo, livro.getTitulo());
        assertEquals(pagina, livro.getPagina());
        assertEquals(idSuspense, livro.getCategoria());

        id = criaLivro(tabelaLivros, "Teste", 1, idRomance);
        cursorLivros = getLivros(tabelaLivros);
        assertEquals(3, cursorLivros.getCount());

         */

        /*
        BdTableCollege tableCollege = new BdTableCollege(db);
        MainCollege college = new MainCollege();
        college.setName("French College");
        college.setCountry("France");
        college.setLocation("SouthWest");
        long idCollege1 = tableCollege.insert(college.getContentValues());
        assertNotEquals(-1, idCollege1);
        */

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
