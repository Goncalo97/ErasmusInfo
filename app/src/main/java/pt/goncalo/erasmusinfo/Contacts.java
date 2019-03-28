package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showMessageContactos();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showMessageContactos() {

        Intent intent = getIntent();
        String message = intent.getStringExtra(AppConsts.MESSAGE_CONTACTOS);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
