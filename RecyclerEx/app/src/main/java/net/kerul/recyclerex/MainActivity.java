package net.kerul.recyclerex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private final LinkedList<String> mWordList = new LinkedList<>();
    private final LinkedList<String> wlistdb = new LinkedList<>();
    //db
    private DatabaseHelper dbhelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hardcodeddata();
        sqlitedata();

        //display the words
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, wlistdb);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void hardcodeddata(){
        //populate data to LinkedList
        mWordList.add("Learning Android Studio 4");
        mWordList.add("Layout XML");
        mWordList.add("Java Programming");
        mWordList.add("OOP");
    }
    private void sqlitedata(){
        //extract data from db
        dbhelper = new DatabaseHelper(this);
        db = dbhelper.getWritableDatabase();
        String sqlselect = "SELECT * FROM "+dbhelper.TABLENAME+";";
        //hold result set
        Cursor cursor = db.rawQuery(sqlselect,null);
        if(cursor.moveToFirst()){
            do{
                wlistdb.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        else{
            Toast.makeText(this,"No record found",Toast.LENGTH_SHORT).show();
        }
    }

    public void callAddActivity(View view){
        Intent i=new Intent(this,AddrecordActivity.class);
        startActivity(i);
    }
    public void refreshbutton(View view){
        this.recreate();
    }

}