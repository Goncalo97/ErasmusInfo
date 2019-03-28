package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getString of Resource @string/edit_profile
        Toast.makeText(this, "{"+getString(R.string.edit_profile)+"}", Toast.LENGTH_LONG).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void saveProfile (View view) {

        // get ID
        EditText editProfileName = (EditText) findViewById(R.id.editTextViewProfileName);
        EditText editProfileAge  = (EditText) findViewById(R.id.editTextViewProfileAge);

        // get View data
        String stringProfileName = editProfileName.getText().toString();
        String stringProfileAge = editProfileAge.getText().toString();

        if (stringProfileName.trim().length() == 0) {

            // err Attribution
            editProfileName.setError(getString(R.string.required_profile_name));

            // focus on field
            editProfileName.requestFocus();
            return;
        }

        if (stringProfileAge.trim().length() == 0) {

            // err Attribution
            editProfileAge.setError(getString(R.string.required_profile_age));

            // focus on field
            editProfileAge.requestFocus();
            return;
        }

        finish();

        // getString of Resource @string/saved
        Toast.makeText(this, "{"+getString(R.string.saved)+"}", Toast.LENGTH_SHORT).show();
    }

    public void cancelProfile (View view) {

        // getString of Resource @string/saved
        Toast.makeText(this, "{"+getString(R.string.canceled)+"}", Toast.LENGTH_SHORT).show();
        finish();
    }

}
