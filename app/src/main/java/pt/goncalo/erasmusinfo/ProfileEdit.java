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

public class ProfileEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getString of Resource @string/edit_profile
        Toast.makeText(this, "{" + getString(R.string.edit_profile) + "}", Toast.LENGTH_LONG).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    // get ID
    EditText editProfileName = (EditText) findViewById(R.id.editTextViewProfileName);
    EditText editProfileAge = (EditText) findViewById(R.id.editTextViewProfileAge);

    public void saveProfile(View view) {


        // get View data
        String stringProfileName = editProfileName.getText().toString();
        String stringProfileAge = editProfileAge.getText().toString();

        if (stringProfileName.trim().length() == 0) {

            // err Attribution
            editProfileName.setError(getString(R.string.required_profile_name));

            // focus on field
            editProfileName.requestFocus();
            return;
        }

        if (stringProfileAge.trim().length() == 0 ||
                Integer.parseInt(stringProfileAge) < 0 ||
                Integer.parseInt(stringProfileAge) > 130) {

            // err Attribution
            editProfileAge.setError(getString(R.string.required_profile_age));

            // focus on field
            editProfileAge.requestFocus();
            return;
        }

        finish();

        // getString of Resource @string/saved
        Toast.makeText(this, "{" + getString(R.string.saved) + "}", Toast.LENGTH_SHORT).show();

    }

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

        editProfileAge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v){
            // TODO Auto-generated method stub
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "PT"));

        editProfileAge.setText(sdf.format(myCalendar.getTime()));
    }

    public void cancelProfile(View view) {

        // getString of Resource @string/saved
        Toast.makeText(this, "{" + getString(R.string.canceled) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

}


