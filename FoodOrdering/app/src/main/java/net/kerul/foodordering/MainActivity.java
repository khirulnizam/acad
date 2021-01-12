package net.kerul.foodordering;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), Order.class);
                startActivity(intent);

            }
        });
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
            Toast.makeText(this,"Settings menu",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.action_info){
            Uri webpage = Uri.parse("https://itrain.com.my");
            Intent intent = new Intent(Intent.ACTION_VIEW,webpage);
            startActivity(intent);//show the webpage
            return true;
        }
        else if(id == R.id.action_dialog){
            //
            onClickShowAlert();
            return true;
        }
        else if(id == R.id.action_date){
            //Uri webpage = Uri.parse("https://itrain.com.my");
            Intent intent = new Intent(this,DatePickerActivity.class);
            startActivity(intent);//show the webpage
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onClickShowAlert(){
        AlertDialog.Builder myalert = new AlertDialog.Builder(MainActivity.this);
        myalert.setTitle("Alert Dialog");//title
        //message body
        myalert.setMessage("Click OK to execute, Cancel to abort");
        myalert.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //temporary
                Toast.makeText(getApplicationContext(),"OK pressed", Toast.LENGTH_SHORT).show();
            }
        });
        myalert.setNegativeButton("Cancel Task", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //temporary
                Toast.makeText(getApplicationContext(),"Cancel pressed", Toast.LENGTH_SHORT).show();
            }
        });
        myalert.show();
    }
}