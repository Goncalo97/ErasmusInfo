package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class College extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this,
                "{"+ getString(R.string.college)+"}",
                Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openCollegeInsert(View view) {

        Intent intent = new Intent(this, CollegeInsert.class);
        startActivity(intent);
    }

    public void openCollegeEdit(View view) {

        Intent intent = new Intent(this, CollegeEdit.class);
        startActivity(intent);
    }

    public void openCollegeDelete(View view) {

        Intent intent = new Intent(this, CollegeDelete.class);
        startActivity(intent);
    }
}
