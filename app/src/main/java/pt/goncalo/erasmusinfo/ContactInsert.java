package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContactInsert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_insert);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void contactSave(View view) {

        EditText contactInsertName = (EditText) findViewById(R.id.editTextContactName);
        EditText contactInsertNumber = (EditText) findViewById(R.id.editTextContactNumber);

        if (contactInsertName.getText().toString().trim().length() == 0) {
            contactInsertName.setError(getString(R.string.contact_insert_textInputEditText_error_name));
            contactInsertName.requestFocus();
            return;
        }

        if (contactInsertNumber.getText().toString().trim().length() == 0) {
            contactInsertNumber.setError(getString(R.string.contact_insert_textInputEditText_error_number));
            contactInsertNumber.requestFocus();
            return;
        }
        Toast.makeText(this, "{" + getString(R.string.contact_insert_toast_saved) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void contactCancel(View view) {

        Toast.makeText(this, "{" + getString(R.string.contact_insert_toast_canceled) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

}