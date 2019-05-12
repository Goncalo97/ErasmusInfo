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
        Profile profile = new Profile();
        profile.setName("John Doe");
        profile.setAge("12/05/2019 10:10:10");
        long idJohn = tableProfile.insert(profile.getContentValues());
        assertNotEquals(-1, idJohn);
        profile = new Profile();
        profile.setName("John Doe 2");
        profile.setAge("12/05/2019 20:20:20");
        long idJohn2 = tableProfile.insert(profile.getContentValues());
        assertNotEquals(-1, idJohn2);
    }
}
