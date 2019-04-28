package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContactEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void contactSave(View view) {

        EditText contactEditName = (EditText) findViewById(R.id.editTextContactName);
        EditText contactEditNumber = (EditText) findViewById(R.id.editTextContactNumber);

        if (contactEditName.getText().toString().trim().length() == 0) {
            contactEditName.setError(getString(R.string.contact_edit_textInputEditText_error_name));
            contactEditName.requestFocus();
            return;
        }

        if (contactEditNumber.getText().toString().trim().length() == 0) {
            contactEditNumber.setError(getString(R.string.contact_edit_textInputEditText_error_number));
            contactEditNumber.requestFocus();
            return;
        }
        Toast.makeText(this, "{" + getString(R.string.contact_edit_toast_saved) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void contactCancel(View view) {

        Toast.makeText(this, "{" + getString(R.string.contact_edit_toast_canceled) + "}", Toast.LENGTH_SHORT).show();
        finish();
    }

}
