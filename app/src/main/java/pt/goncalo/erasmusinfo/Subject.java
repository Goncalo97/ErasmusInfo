package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class Subject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this,
                "{"+ getString(R.string.subject)+"}",
                Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openSubjectInsert(View view) {

        Intent intent = new Intent(this, SubjectInsert.class);
        startActivity(intent);
    }

    public void openSubjectEdit(View view) {

        Intent intent = new Intent(this, SubjectEdit.class);
        startActivity(intent);
    }

    public void openSubjectDelete(View view) {

        Intent intent = new Intent(this, SubjectDelete.class);
        startActivity(intent);
    }
}
