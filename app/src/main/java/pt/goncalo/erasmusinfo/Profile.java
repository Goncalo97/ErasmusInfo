package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /* showMessagePerfil(); */
        Toast.makeText(this, "{"+ getString(R.string.profile)+"}", Toast.LENGTH_SHORT).show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /*
    private void showMessagePerfil() {

        Intent intent = getIntent();
        String message = intent.getStringExtra(AppConsts.MESSAGE_PERFIL);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    */

    public void openProfileView(View view) {

        Intent intent = new Intent(this, ProfileView.class);
        startActivity(intent);
    }

    public void openProfileInsert(View view) {

        Intent intent = new Intent(this, ProfileInsert.class);
        startActivity(intent);
    }

    public void openProfileEdit(View view) {

        Intent intent = new Intent(this, ProfileEdit.class);
        startActivity(intent);
    }

    public void openProfileDelete(View view) {

        Intent intent = new Intent(this, ProfileDelete.class);
        startActivity(intent);
    }

}
