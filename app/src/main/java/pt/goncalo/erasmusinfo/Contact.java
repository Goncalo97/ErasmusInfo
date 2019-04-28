package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toast.makeText(this,
                "{"+ getString(R.string.contact)+"}",
                Toast.LENGTH_SHORT).show();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void openContactView(View view) {

        Intent intent = new Intent(this, ContactView.class);
        startActivity(intent);
    }

    public void openContactInsert(View view) {

        Intent intent = new Intent(this, ContactInsert.class);
        startActivity(intent);
    }

    public void openContactEdit(View view) {

        Intent intent = new Intent(this, ContactEdit.class);
        startActivity(intent);
    }

    public void openContactDelete(View view) {

        Intent intent = new Intent(this, ContactDelete.class);
        startActivity(intent);
    }
}
