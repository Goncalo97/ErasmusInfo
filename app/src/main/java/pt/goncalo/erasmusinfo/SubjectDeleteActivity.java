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

public class SubjectDeleteActivity extends AppCompatActivity {
    private Uri subjectDeleteAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_delete);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textViewCode = (TextView) findViewById(R.id.textViewSubjectCode);
        TextView textViewName = (TextView) findViewById(R.id.textViewSubjectName);
        TextView textViewCollege = (TextView) findViewById(R.id.textViewCollege);
        TextView textViewECTs = (TextView) findViewById(R.id.textViewSubjectECTs);
        TextView textViewEqualSubject = (TextView) findViewById(R.id.textViewSubjectDiscEqual);
        TextView textViewScore = (TextView) findViewById(R.id.textViewSubjectScore);

        Intent intent = getIntent();

        long idSubject = intent.getLongExtra(SubjectActivity.ID_SUBJECT, -1);

        if (idSubject == -1) {
            Toast.makeText(this, "Error: It was not possible to read the Subject.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        subjectDeleteAddress = Uri.withAppendedPath(ErasmusInfoContentProvider.SUBJECT_ADDRESS, String.valueOf(idSubject));

        Cursor cursor = getContentResolver().query(subjectDeleteAddress, DbTableSubject.ALL_COLUMNS, null, null, null);

        if (!cursor.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to delete the Subject.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Subject subject = Subject.fromCursor(cursor);

        textViewCode.setText(subject.getCode());
        textViewName.setText(subject.getName());
        textViewECTs.setText(String.valueOf(subject.getECTS()));
        Log.i("SUBJECT", ""+subject.getEqualSubject());
        textViewEqualSubject.setText(subject.getEqualSubject());
        textViewScore.setText(subject.getScore());

        Uri uri = Uri.withAppendedPath(ErasmusInfoContentProvider.COLLEGE_ADDRESS, String.valueOf(subject.getIdCollege()));
        Cursor cursorP = getContentResolver().query(uri, DbTableCollege.ALL_COLUMNS, null, null, null);

        Log.i("CURSORP", ""+cursorP);
        if (!cursorP.moveToNext()) {
            Toast.makeText(this, "Error: It was not possible to load the College.", Toast.LENGTH_LONG).show();
            //finish();
            //return;

        }

        College college = College.fromCursor(cursorP);
        textViewCollege.setText(String.valueOf(college.getName()));
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
        int erasedCollege = getContentResolver().delete(subjectDeleteAddress, null, null);
        if (erasedCollege == 1) {
            Toast.makeText(this, "Subject deleted with success!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Error: It was not possible to delete the subject!", Toast.LENGTH_LONG).show();
        }
    }

}
