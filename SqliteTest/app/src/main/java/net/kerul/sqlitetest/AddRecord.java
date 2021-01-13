package net.kerul.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddRecord extends AppCompatActivity {
    private EditText txttitle, txtdescription;
    DatabaseHelper database_helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        //gui
        txttitle=(EditText)findViewById(R.id.txttitle);
        txtdescription=(EditText)findViewById(R.id.txtdescription);
        //activate database
        // Gets the data file in write mode
        SQLiteDatabase db = database_helper.getWritableDatabase();
    }

    public void saveNewNote(View view){
        Log.d("try","add record");
        String title=txttitle.getText().toString();
        String description = txtdescription.getText().toString();
        database_helper.addNotes(title,description);

    }

}