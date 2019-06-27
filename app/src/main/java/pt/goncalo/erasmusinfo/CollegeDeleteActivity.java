package pt.goncalo.erasmusinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CollegeDeleteActivity extends AppCompatActivity {
    private Uri collegeDeleteAddress;
    //TODO por getSupportLoader ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_delete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        TextView textViewName = (TextView) findViewById(R.id.textViewCollegeName);
        TextView textViewProfile = (TextView) findViewById(R.id.textViewProfile);
        TextView textViewCountry = (TextView) findViewById(R.id.textViewCollegeCountry);
        TextView textViewLocation = (TextView) findViewById(R.id.textViewCollegeLocation);

        Intent intent = getIntent();

        long idCollege = intent.getLongExtra(CollegeActivity.ID_COLLEGE, -1);

            if (idCollege == -1) {
            Toast.makeText(this, "Error: It was not possible to read the College.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        collegeDeleteAddress = Uri.withAppendedPath(ErasmusInfoContentProvider.COLLEGE_ADDRESS, String.valueOf(idCollege));

        Cursor cursor = getContentResolver().query(collegeDeleteAddress, DbTableCollege.ALL_COLUMNS, null, null, null);

            if (!cursor.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to delete the College.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        College college = College.fromCursor(cursor);

            textViewName.setText(college.getName());
            textViewCountry.setText(college.getCountry());
        textViewLocation.setText(college.getLocation());
        Uri uri = Uri.withAppendedPath(ErasmusInfoContentProvider.PROFILE_ADDRESS, String.valueOf(college.getIdProfile()));
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
            AskDelete();
            //delete();
            return true;
        } else if (id == R.id.action_cancel) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void delete() {
        // todo: perguntar ao utilizador se tem a certeza

        int erasedProfile = getContentResolver().delete(collegeDeleteAddress, null, null);
        if (erasedProfile == 1) {
            Toast.makeText(this, "College deleted with success!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Error: It was not possible to delete the college!", Toast.LENGTH_LONG).show();
        }
    }


    private void AskDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("DELETE");
        alertDialogBuilder.setMessage("Are you sure you want to delete this record?");

        alertDialogBuilder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                    }
                }
        );

        alertDialogBuilder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;//finish();
                    }
                }
        );

        alertDialogBuilder.show();
    }
}