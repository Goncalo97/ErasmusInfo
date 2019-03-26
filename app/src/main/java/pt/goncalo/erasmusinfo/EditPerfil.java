package pt.goncalo.erasmusinfo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class EditPerfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // getString of Resource @string/edit_profile
        Toast.makeText(this, "{"+getString(R.string.edit_profile)+"}", Toast.LENGTH_LONG).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
