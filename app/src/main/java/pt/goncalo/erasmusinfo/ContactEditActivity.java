package pt.goncalo.erasmusinfo;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

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

import com.google.android.material.snackbar.Snackbar;

public class ContactEditActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSOR_LOADER_PROFILE = 0;

    private EditText editTextName;
    private Spinner spinnerProfile;
    private EditText editTextNumber;

    private Contact contact = null;

    private boolean profileLoaded = false;
    private boolean profileRefreshed = false;

    private Uri contactEditAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextName = (EditText) findViewById(R.id.editTextContactName);
        spinnerProfile = (Spinner) findViewById(R.id.spinnnerContact);
        editTextNumber = (EditText) findViewById(R.id.editTextContactNumber);
        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_PROFILE, null, this);
        Intent intent = getIntent();
        long idContact = intent.getLongExtra(ContactActivity.ID_CONTACT, -1);

        if (idContact == -1) {
            Toast.makeText(this, "Error: It was not possible to read the Contact.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        contactEditAddress = Uri.withAppendedPath(ErasmusInfoContentProvider.CONTACT_ADDRESS, String.valueOf(idContact));

        Cursor cursor = getContentResolver().query(contactEditAddress, DbTableContact.ALL_COLUMNS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to read the Contact.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        contact = Contact.fromCursor(cursor);

        editTextName.setText(contact.getName());
        editTextNumber.setText(String.valueOf(contact.getNumber()));

        refreshProfileSelected();
    }

    private void refreshProfileSelected() {
        if (!profileLoaded) return;
        if (profileRefreshed) return;

        for (int i = 0; i < spinnerProfile.getCount(); i++) {
            if (spinnerProfile.getItemIdAtPosition(i) == contact.getIdProfile()) {
                spinnerProfile.setSelection(i);
                break;
            }
        }

        profileRefreshed = true;
    }

    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSOR_LOADER_PROFILE, null, this);

        super.onResume();
    }

    private void showProfileSpinner(Cursor cursorProfile) {
        SimpleCursorAdapter adapterProfile = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                cursorProfile,
                new String[]{DbTableProfile.FIELD_NAME},
                new int[]{android.R.id.text1}
        );
        spinnerProfile.setAdapter(adapterProfile);
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
        String name = editTextName.getText().toString();

        if (name.trim().isEmpty()) {
            editTextName.setError("Please insert a name!");
            return;
        }

        String number = editTextNumber.getText().toString();

        if (name.trim().isEmpty()) {
            editTextNumber.setError("Please insert a number!");
            return;
        }

        long idProfile = spinnerProfile.getSelectedItemId();

        Log.i("TAG", "" + idProfile);
        // guardar os dados
        contact.setName(name);
        contact.setIdProfile(idProfile);
        contact.setNumber(number);

        try {
            getContentResolver().update(contactEditAddress, contact.getContentValues(), null, null);

            Toast.makeText(this, "Contact saved with success!", Toast.LENGTH_SHORT).show();
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


    public void contactSave(View view) {
        EditText contactEditName = findViewById(R.id.editTextContactName);
        EditText contactEditNumber = findViewById(R.id.editTextContactNumber);
        if (contactEditName.getText().toString().trim().length() == 0) {
            contactEditName.setError(getString(R.string.contact_edit_textInputEditText_error_name));
            contactEditName.requestFocus();
            return;
        }
        if (contactEditNumber.getText().toString().trim().length() == 0) {
            contactEditNumber.setError(getString(R.string.contact_edit_textInputEditText_error_number));
            contactEditNumber.requestFocus();
            return;
        }
        Toast.makeText(this,
                "{" + getString(R.string.contact_edit_toast_saved) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    public void contactCancel(View view) {
        Toast.makeText(this,
                "{" + getString(R.string.contact_edit_toast_canceled) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
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
        androidx.loader.content.CursorLoader cursorLoader = new androidx.loader.content.CursorLoader(this, ErasmusInfoContentProvider.PROFILE_ADDRESS, DbTableProfile.ALL_COLUMNS, null, null, DbTableProfile.FIELD_NAME
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
        showProfileSpinner(data);
        profileLoaded = true;
        refreshProfileSelected();
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
        profileLoaded = false;
        profileRefreshed= false;
        showProfileSpinner(null);
    }
}
