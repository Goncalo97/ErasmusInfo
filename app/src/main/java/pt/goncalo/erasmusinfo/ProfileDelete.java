package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ProfileDelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getString of Resource @string/profile_edit
        Toast.makeText(this, "{" + getString(R.string.profile_edit) + "}", Toast.LENGTH_LONG).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void profileDelete(View view) {

        // getString of Resource @string/saved
        Toast.makeText(this, "{" + getString(R.string.delete) + "}", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void cancelProfile(View view) {

        // getString of Resource @string/saved
        Toast.makeText(this, "{" + getString(R.string.canceled) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

}



