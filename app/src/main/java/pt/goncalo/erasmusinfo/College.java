package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;

public class College extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showMessageUniversidade();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showMessageUniversidade() {

        Intent intent = getIntent();
        String message = intent.getStringExtra(AppConsts.MESSAGE_UNIVERSIDADE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
