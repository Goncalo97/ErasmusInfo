package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CollegeEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    public void collegeSave(View view) {
        EditText collegeEditName = findViewById(R.id.editTextCollegeName);
        EditText collegeEditCountry = findViewById(R.id.editTextCollegeCountry);
        EditText collegeEditLocation = findViewById(R.id.editTextCollegeLocation);

        if (collegeEditName.getText().toString().trim().length() == 0) {
            collegeEditName.setError(getString(R.string.college_edit_textInputEditText_error_name));
            collegeEditName.requestFocus();
            return;
        }
        else if (collegeEditCountry.getText().toString().trim().length() == 0) {
            collegeEditCountry.setError(getString(R.string.college_edit_textInputEditText_error_country));
            collegeEditCountry.requestFocus();
            return;
        }
        else if (collegeEditLocation.getText().toString().trim().length() == 0) {
            collegeEditLocation.setError(getString(R.string.college_edit_textInputEditText_error_location));
            collegeEditLocation.requestFocus();
            return;
        }
        Toast.makeText(this,
                "{" + getString(R.string.college_edit_toast_saved) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    public void collegeCancel(View view) {
        Toast.makeText(this,
                "{" + getString(R.string.college_edit_toast_canceled) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
