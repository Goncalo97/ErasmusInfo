package pt.goncalo.erasmusinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        getAppContext().deleteDatabase(BdErasmusInfoOpenHelper.NOME_BASE_DADOS);
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
}
