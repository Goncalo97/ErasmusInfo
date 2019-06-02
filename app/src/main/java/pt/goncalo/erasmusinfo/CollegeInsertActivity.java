package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CollegeInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_insert);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    public void collegeSave(View view) {
        EditText collegeInsertName = findViewById(R.id.editTextCollegeName);
        EditText collegeInsertCountry = findViewById(R.id.editTextCollegeCountry);
        EditText collegeInsertLocation = findViewById(R.id.editTextCollegeLocation);
        if (collegeInsertName.getText().toString().trim().length() == 0) {
            collegeInsertName.setError(getString(R.string.college_edit_textInputEditText_error_name));
            collegeInsertName.requestFocus();
            return;
        }
        else if (collegeInsertCountry.getText().toString().trim().length() == 0) {
            collegeInsertCountry.setError(getString(R.string.college_edit_textInputEditText_error_country));
            collegeInsertCountry.requestFocus();
            return;
        }
        else if (collegeInsertLocation.getText().toString().trim().length() == 0) {
            collegeInsertLocation.setError(getString(R.string.college_edit_textInputEditText_error_location));
            collegeInsertLocation.requestFocus();
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
