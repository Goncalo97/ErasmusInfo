package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
// TODO: ELIMIONAR IMPLEMENTADO
public class ProfileDeleteActivity extends AppCompatActivity {

    private Uri profileDeleteAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_delete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textViewName = (TextView) findViewById(R.id.textViewProfileName);
        TextView textViewDate = (TextView) findViewById(R.id.textViewProfileDate);

        Intent intent = getIntent();

        long idProfile = intent.getLongExtra(ProfileActivity.ID_PROFILE, -1);

        if (idProfile == -1) {
            Toast.makeText(this, "Error: It was not possible to read the Profile.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        profileDeleteAddress = Uri.withAppendedPath(ErasmusInfoContentProvider.PROFILE_ADDRESS, String.valueOf(idProfile));

        Cursor cursor = getContentResolver().query(profileDeleteAddress, DbTableProfile.ALL_COLUMNS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to delete the Profile.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Profile profile = Profile.fromCursor(cursor);

        textViewName.setText(profile.getName());
        textViewDate.setText(String.valueOf(profile.getDate()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_delete) {
            delete();
            return true;
        } else if (id == R.id.action_cancel) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void delete() {
        // todo: perguntar ao utilizador se tem a certeza
        int erasedProfile = getContentResolver().delete(profileDeleteAddress, null, null);
        if (erasedProfile == 1) {
            Toast.makeText(this, "Profile deleted with success!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Error: It was not possible to delete the profile!", Toast.LENGTH_LONG).show();
        }
    }

    public void profileDelete(View view) {
        Toast.makeText(this,
                "{" + getString(R.string.profile_delete_toast_deleted) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancelProfile(View view) {
        Toast.makeText(this,
                "{" + getString(R.string.profile_delete_toast_canceled) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }

}