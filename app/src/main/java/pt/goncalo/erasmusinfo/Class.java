package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;

public class Class extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showMessageDisciplinas();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showMessageDisciplinas() {

        Intent intent = getIntent();
        String message = intent.getStringExtra(AppConsts.MESSAGE_DISCIPLINAS);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
