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

public class ProfileInsertActivity extends AppCompatActivity {

    EditText editProfileAge;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_insert);
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
                new DatePickerDialog(ProfileInsertActivity.this, date,
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
                new DatePickerDialog(ProfileInsertActivity.this, date,
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
}