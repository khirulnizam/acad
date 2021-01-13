package net.kerul.recyclerex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddrecordActivity extends AppCompatActivity {
    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    EditText txtenglish, txtfrance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrecord);
        //gui binding
        txtenglish=(EditText)findViewById(R.id.txtenglish);
        txtfrance=(EditText)findViewById(R.id.txtfrance);
    }

    public void savenewordsfunction(View view){
        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();
        //preparing the data
        ContentValues val = new ContentValues();
        val.put("english",txtenglish.getText().toString());
        val.put("france",txtfrance.getText().toString());
        //push data to sqlite
        if(db.insert(dbhelper.TABLENAME,null,val)==-1){
            Toast.makeText(this,"Cannot save record",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Record save",Toast.LENGTH_SHORT).show();
        }

    }
}