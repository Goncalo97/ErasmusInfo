package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;


public class ProfileDelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_delete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getString of Resource @string/profile_delete_button
        Toast.makeText(this, "{" + getString(R.string.profile_delete_button) + "}", Toast.LENGTH_LONG).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void profileDelete(View view) {

        // getString of Resource @string/profile_edit_toast_saved
        Toast.makeText(this, "{" + getString(R.string.profile_delete_toast_deleted) + "}", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void cancelProfile(View view) {

        // getString of Resource @string/profile_edit_toast_saved
        // Toast.makeText(this, "{" + getString(R.string.profile_edit_toast_canceled) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

}