package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContactDeleteActivity extends AppCompatActivity {
    private Uri contactDeleteAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_delete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewName = (TextView) findViewById(R.id.textViewContactName);
        TextView textViewProfile = (TextView) findViewById(R.id.textViewProfile);
        TextView textViewNumber = (TextView) findViewById(R.id.textViewContactNumber);

        Intent intent = getIntent();

        long idContact = intent.getLongExtra(ContactActivity.ID_CONTACT, -1);

        if (idContact == -1) {
            Toast.makeText(this, "Error: It was not possible to read the Contact.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        contactDeleteAddress = Uri.withAppendedPath(ErasmusInfoContentProvider.CONTACT_ADDRESS, String.valueOf(idContact));

        Cursor cursor = getContentResolver().query(contactDeleteAddress, DbTableContact.ALL_COLUMNS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to delete the Contact.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Contact contact = Contact.fromCursor(cursor);

        textViewName.setText(contact.getName());
        textViewNumber.setText(contact.getNumber());
        Uri uri = Uri.withAppendedPath(ErasmusInfoContentProvider.PROFILE_ADDRESS, String.valueOf(contact.getIdProfile()));
        Cursor cursorP = getContentResolver().query(uri, DbTableProfile.ALL_COLUMNS, null, null, null);

        Log.i("CURSORP", ""+cursorP);
        if (!cursorP.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to load the Profile.", Toast.LENGTH_LONG).show();
            //finish();
            //return;


        }


        Profile profile = Profile.fromCursor(cursorP);
        textViewProfile.setText(String.valueOf(profile.getName()));

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
        int erasedProfile = getContentResolver().delete(contactDeleteAddress, null, null);
        if (erasedProfile == 1) {
            Toast.makeText(this, "Contact deleted with success!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Error: It was not possible to delete the contact!", Toast.LENGTH_LONG).show();
        }
    }

    public void contactDelete(View view) {

        Toast.makeText(this,
                "{" + getString(R.string.contact_delete_toast_deleted) + "}",
                Toast.LENGTH_SHORT).show();
        finish();

    }

    public void contactCancel(View view) {

        Toast.makeText(this,
                "{" + getString(R.string.contact_delete_toast_canceled) + "}",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
