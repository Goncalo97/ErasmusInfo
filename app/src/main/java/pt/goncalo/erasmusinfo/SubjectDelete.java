package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class SubjectDelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_delete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void subjectDelete(View view) {

        Toast.makeText(this,
                "{" + getString(R.string.subject_delete_toast_deleted) + "}",
                Toast.LENGTH_SHORT).show();
        finish();

    }

    public void subjectCancel(View view) {

        Toast.makeText(this,
                "{" + getString(R.string.subject_delete_toast_canceled) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
