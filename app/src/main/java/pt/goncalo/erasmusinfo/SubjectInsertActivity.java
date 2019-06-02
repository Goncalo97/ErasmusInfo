package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SubjectInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_insert);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void subjectSave(View view) {
        EditText subjectInsertCode = findViewById(R.id.editTextSubjectCode);
        EditText subjectInsertName = findViewById(R.id.editTextSubjectName);
        EditText subjectInsertECTs = findViewById(R.id.editTextSubjectECTs);
        EditText subjectInsertDiscEqual = findViewById(R.id.editTextSubjectDiscEqual);
        EditText subjectInsertScore = findViewById(R.id.editTextSubjectScore);

        if (subjectInsertCode.getText().toString().trim().length() == 0) {
            subjectInsertCode.setError(getString(R.string.subject_edit_textInputEditText_error_code));
            subjectInsertCode.requestFocus();
            return;
        }
        else if (subjectInsertName.getText().toString().trim().length() == 0) {
            subjectInsertName.setError(getString(R.string.subject_edit_textInputEditText_error_name));
            subjectInsertName.requestFocus();
            return;
        }
        else if (subjectInsertECTs.getText().toString().trim().length() == 0) {
            subjectInsertECTs.setError(getString(R.string.subject_edit_textInputEditText_error_ects));
            subjectInsertECTs.requestFocus();
            return;
        }
        else if (subjectInsertDiscEqual.getText().toString().trim().length() == 0) {
            subjectInsertDiscEqual.setError(getString(R.string.subject_edit_textInputEditText_error_discEqual));
            subjectInsertDiscEqual.requestFocus();
            return;
        }
        else if (subjectInsertScore.getText().toString().trim().length() == 0) {
            subjectInsertScore.setError(getString(R.string.subject_edit_textInputEditText_error_score));
            subjectInsertScore.requestFocus();
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
