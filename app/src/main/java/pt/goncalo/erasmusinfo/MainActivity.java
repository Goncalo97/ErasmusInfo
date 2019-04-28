package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

// TODO: Verificar se as strings correspondem
// TODO: Refatorar IDs
// TODO: Melhorar o funcionamento da função 'datePickerDialog'
// TODO: Criar College
// TODO: Criar Subject
// TODO: Gets & Sets
// TODO: Melhorar o design

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void openProfile(View view) {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }


    public void openContact(View view) {
        Intent intent = new Intent(this, Contact.class);
        startActivity(intent);
    }

    public void openCollege(View view) {
        Intent intent = new Intent(this, College.class);
        startActivity(intent);
    }

    public void openClass(View view) {
        Intent intent = new Intent(this, Subject.class);
        startActivity(intent);
    }
}
