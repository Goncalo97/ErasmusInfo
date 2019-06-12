package pt.goncalo.erasmusinfo;

import android.app.DatePickerDialog;
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

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProfileEditActivity extends AppCompatActivity {

    private static final int ID_CURSOR_LOADER_PROFILE = 0;

    private EditText editTextName;
    private EditText editTextDate;

    private Profile profile = null;

    private Uri profileEditAddress;

    EditText editProfileAge;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editProfileAge = findViewById(R.id.editTextViewProfileAge);
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editProfileAge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(ProfileEditActivity.this, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editTextName = (EditText) findViewById(R.id.editTextViewProfileName);
        editTextDate = (EditText) findViewById(R.id.editTextViewProfileAge);

        Intent intent = getIntent();
        long idProfile = intent.getLongExtra(ProfileActivity.ID_PROFILE, -1);

        if (idProfile == -1) {
            Toast.makeText(this, "Error: It was not possible to read the Profile.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        profileEditAddress = Uri.withAppendedPath(ErasmusInfoContentProvider.PROFILE_ADDRESS, String.valueOf(idProfile));

        Cursor cursor = getContentResolver().query(profileEditAddress, DbTableProfile.ALL_COLUMNS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to read the Profile.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        profile = Profile.fromCursor(cursor);

        editTextName.setText(profile.getName());
        editTextDate.setText(String.valueOf(profile.getDate()));

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,
                new Locale("pt", "PT"));
        editProfileAge.setText(sdf.format(myCalendar.getTime()));
        editProfileAge.setError(null);
    }

    public void saveProfile(View view) {
        EditText editProfileName = findViewById(R.id.editTextViewProfileName);
        String stringProfileName = editProfileName.getText().toString();
        String stringProfileAge = editProfileAge.getText().toString();
        if (stringProfileName.trim().length() == 0) {
            editProfileName.setError(getString(R.string.profile_insert_textInputEditText_error_name));
            editProfileName.requestFocus();
            return;
        }
        if (stringProfileAge.trim().length() == 0) {
            editProfileAge.setError(getString(R.string.profile_edit_textInputEditText_error_birthDate));
            editProfileAge.requestFocus();
            if(editProfileAge.hasFocus())
                new DatePickerDialog(ProfileEditActivity.this, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            return;
        }
        Toast.makeText(this, "{" + getString(R.string.profile_insert_toast_saved) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelProfile(View view) {
        Toast.makeText(this, "{" + getString(R.string.profile_insert_toast_canceled) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            editTextName.setError("Please type a name.");
            return;
        }

        String date = editTextDate.getText().toString();

        if (name.trim().isEmpty()) {
            editTextDate.setError("Please insert a date.");
            return;
        }

        // save the data

        profile.setName(name);
        profile.setDate(date);

        try {
            getContentResolver().update(profileEditAddress, profile.getContentValues(), null, null);

            Toast.makeText(this, "Saved with success!", Toast.LENGTH_SHORT).show();
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
}