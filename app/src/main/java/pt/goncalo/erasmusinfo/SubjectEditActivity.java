package pt.goncalo.erasmusinfo;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class SubjectEditActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int ID_CURSOR_LOADER_COLLEGE = 0;

    private EditText editTextCode;
    private EditText editTextName;
    private Spinner spinnerCollege;
    private EditText editTextECTs;
    private EditText editTextDiscEqual;
    private EditText editTextScore;

    private Subject subject = null;

    private boolean collegeLoaded = false;
    private boolean collegeRefreshed = false;

    private Uri subjectEditAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editTextCode = (EditText) findViewById(R.id.editTextSubjectCode);
        editTextName = (EditText) findViewById(R.id.editTextSubjectName);
        spinnerCollege = (Spinner) findViewById(R.id.spinnerSubject);
        editTextECTs = (EditText) findViewById(R.id.editTextSubjectECTs);
        editTextDiscEqual = (EditText) findViewById(R.id.editTextSubjectDiscEqual);
        editTextScore = (EditText) findViewById(R.id.editTextSubjectScore);
        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_COLLEGE, null, this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        long idSubject = intent.getLongExtra(SubjectActivity.ID_SUBJECT, -1);

        if (idSubject == -1) {
            Toast.makeText(this, "Error: It was not possible to read the Subject.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        subjectEditAddress = Uri.withAppendedPath(ErasmusInfoContentProvider.SUBJECT_ADDRESS, String.valueOf(idSubject));
        Log.i("WHAT", ""+subjectEditAddress);
        Cursor cursor = getContentResolver().query(subjectEditAddress, DbTableSubject.ALL_COLUMNS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to read the Subject.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        subject = Subject.fromCursor(cursor);

        editTextCode.setText(subject.getCode());
        editTextName.setText(subject.getName());
        editTextECTs.setText(String.valueOf(subject.getECTS()));
        editTextDiscEqual.setText(subject.getEqualSubject());
        editTextScore.setText(subject.getScore());
        refreshCollegeSelected();
    }

    private void refreshCollegeSelected() {
        if (!collegeLoaded) return;
        if (collegeRefreshed) return;

        for (int i = 0; i < spinnerCollege.getCount(); i++) {
            if (spinnerCollege.getItemIdAtPosition(i) == subject.getIdCollege()) {
                spinnerCollege.setSelection(i);
                break;
            }
        }

        collegeRefreshed = true;
    }

    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSOR_LOADER_COLLEGE, null, this);

        super.onResume();
    }

    private void showCollegeSpinner(Cursor cursorCollege) {
        SimpleCursorAdapter adapterCollege = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursorCollege,
                new String[]{DbTableCollege.FIELD_NAME},
                new int[]{android.R.id.text1}
        );
        Log.i("ADAP", ""+adapterCollege);
        spinnerCollege.setAdapter(adapterCollege);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_save) {
            save();
            return true;
        } else if (id == R.id.action_cancel) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void save() {

        String code = editTextCode.getText().toString();

        if (code.trim().isEmpty()) {
            editTextCode.setError("Please insert a code!");
            return;
        }

        String name = editTextName.getText().toString();

        if (name.trim().isEmpty()) {
            editTextName.setError("Please insert a name!");
            return;
        }

        int ects;

        String strECTs = editTextECTs.getText().toString();

        if (strECTs.trim().isEmpty()) {
            editTextECTs.setError("Please type the ECTs.");
            return;
        } else if (Integer.parseInt(strECTs) > 30 || Integer.parseInt(strECTs) <= 0) {
            editTextECTs.setError("Please type under 30 ECTs, and above 0.");
            return;
        }

        try {
            ects = Integer.parseInt(strECTs);
        } catch (NumberFormatException e) {
            editTextECTs.setError("Invalid ECTs.");
            return;
        }

        String equalSubject = editTextDiscEqual.getText().toString();

        if (equalSubject.trim().isEmpty()) {
            editTextDiscEqual.setError("Please insert a equal subject!");
            return;
        }

        String score = editTextScore.getText().toString();

        if (score.trim().isEmpty()) {
            editTextScore.setError("Please insert a score!");
            return;
        }

        long idCollege = spinnerCollege.getSelectedItemId();

        Log.i("TAG", "" + idCollege);
        // guardar os dados
        subject.setCode(code);
        subject.setIdCollege(idCollege);
        subject.setName(name);
        subject.setEcts(ects);
        subject.setEqualSubject(equalSubject);
        subject.setScore(score);

        try {
            getContentResolver().update(subjectEditAddress, subject.getContentValues(), null, null);

            Toast.makeText(this, "Subject saved with success!", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Snackbar.make(
                    editTextName,
                    "Error while saving!",
                    Snackbar.LENGTH_LONG)
                    .show();

            e.printStackTrace();
        }
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        androidx.loader.content.CursorLoader cursorLoader = new androidx.loader.content.CursorLoader(this, ErasmusInfoContentProvider.COLLEGE_ADDRESS, DbTableCollege.ALL_COLUMNS, null, null, DbTableCollege.FIELD_NAME
        );

        return cursorLoader;
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     *
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data   The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        showCollegeSpinner(data);
        collegeLoaded = true;
        refreshCollegeSelected();
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        collegeLoaded = false;
        collegeRefreshed= false;
        showCollegeSpinner(null);
    }
}
