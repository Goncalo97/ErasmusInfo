package pt.goncalo.erasmusinfo;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class ProfileInsert extends AppCompatActivity {

    EditText editProfileAge;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_insert);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getString of Resource @string/profile_insert_button
        Toast.makeText(this, "{" + getString(R.string.profile_insert_button) + "}", Toast.LENGTH_LONG).show();

        editProfileAge = (EditText) findViewById(R.id.editTextViewProfileAge);
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
                new DatePickerDialog(ProfileInsert.this, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,
                new Locale("pt", "PT"));

        editProfileAge.setText(sdf.format(myCalendar.getTime()));
    }

    public void saveProfile(View view) {

        // get ID
        EditText editProfileName = (EditText) findViewById(R.id.editTextViewProfileName);

        // get View data
        String stringProfileName = editProfileName.getText().toString();
        String stringProfileAge = editProfileAge.getText().toString();

        if (stringProfileName.trim().length() == 0) {

            // err Attribution
            editProfileName.setError(getString(R.string.profile_edit_error_name));

            // focus on field
            editProfileName.requestFocus();
            return;
        }

        if (stringProfileAge.trim().length() == 0) {

            // err Attribution
            editProfileAge.setError(getString(R.string.profile_edit_error_birthday));

            // focus on field
            editProfileAge.requestFocus();
            if(editProfileAge.hasFocus())
                new DatePickerDialog(ProfileInsert.this, date,
                        myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            return;
        }

        // getString of Resource @string/profile_edit_toast_saved
        Toast.makeText(this, "{" + getString(R.string.profile_edit_toast_saved) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelProfile(View view) {

        // getString of Resource @string/profile_edit_toast_saved
        Toast.makeText(this, "{" + getString(R.string.profile_edit_toast_canceled) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

}