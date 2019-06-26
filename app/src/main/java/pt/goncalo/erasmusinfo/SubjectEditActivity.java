package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SubjectEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    
    public void subjectSave(View view) {
        EditText subjectEditCode = findViewById(R.id.editTextSubjectCode);
        EditText subjectEditName = findViewById(R.id.editTextSubjectName);
        EditText subjectEditECTs = findViewById(R.id.editTextSubjectECTs);
        EditText subjectEditDiscEqual = findViewById(R.id.editTextSubjectDiscEqual);
        EditText subjectEditScore = findViewById(R.id.editTextSubjectScore);

        if (subjectEditCode.getText().toString().trim().length() == 0) {
            subjectEditCode.setError(getString(R.string.subject_edit_textInputEditText_error_code));
            subjectEditCode.requestFocus();
            return;
        }
        else if (subjectEditName.getText().toString().trim().length() == 0) {
            subjectEditName.setError(getString(R.string.subject_edit_textInputEditText_error_name));
            subjectEditName.requestFocus();
            return;
        }
        else if (subjectEditECTs.getText().toString().trim().length() == 0) {
            subjectEditECTs.setError(getString(R.string.subject_edit_textInputEditText_error_ects));
            subjectEditECTs.requestFocus();
            return;
        }
        else if (subjectEditDiscEqual.getText().toString().trim().length() == 0) {
            subjectEditDiscEqual.setError(getString(R.string.subject_edit_textInputEditText_error_discEqual));
            subjectEditDiscEqual.requestFocus();
            return;
        }
        else if (subjectEditScore.getText().toString().trim().length() == 0) {
            subjectEditScore.setError(getString(R.string.subject_edit_textInputEditText_error_score));
            subjectEditScore.requestFocus();
            return;
        }

        Toast.makeText(this,
                "{" + getString(R.string.subject_edit_toast_saved) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    public void subjectCancel(View view) {
        Toast.makeText(this,
                "{" + getString(R.string.subject_edit_toast_canceled) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
