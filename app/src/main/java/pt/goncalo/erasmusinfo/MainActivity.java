package pt.goncalo.erasmusinfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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

    public void sendMessagePerfil(View view) {

        Intent intent = new Intent(this, Profile.class);
        intent.putExtra(AppConsts.MESSAGE_PERFIL, "{ Profile }");
        startActivity(intent);
        // Toast.makeText(this, "Perf", Toast.LENGTH_SHORT).show();
    }

    public void sendMessageContactos(View view) {

        Intent intent = new Intent(this, Contacts.class);
        intent.putExtra(AppConsts.MESSAGE_CONTACTOS, "{ Contacts }");
        startActivity(intent);
    }

    public void sendMessageUniversidade(View view) {

        Intent intent = new Intent(this, College.class);
        intent.putExtra(AppConsts.MESSAGE_UNIVERSIDADE, "{ College }");
        startActivity(intent);
    }

    public void sendMessageDisciplinas(View view) {

        Intent intent = new Intent(this, Class.class);
        intent.putExtra(AppConsts.MESSAGE_DISCIPLINAS, "{ Class }");
        startActivity(intent);
    }
}
