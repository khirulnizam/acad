package net.kerul.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton fbtnAdd;
    //SQLiteDatabase sqLiteDatabase = SQLiteOpenHelper.getWritableDatabase();
    private DatabaseHelper database_helper;
    private SQLiteDatabase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GUI
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fbtnAdd = (FloatingActionButton) findViewById(R.id.fbtnAdd);
        //database ignite
        database_helper = new DatabaseHelper(this);


    }

    //display notes functions
    //display notes list
    ArrayList<NoteModel> arrayList;

    public void displayNotes() {
        arrayList = new ArrayList<>(database_helper.getNotes());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NotesAdapter adapter = new NotesAdapter(getApplicationContext(), this, arrayList);
        recyclerView.setAdapter(adapter);
    }

    public void gotoAddPage(View view){
        Intent gotoadd = new Intent(this, AddRecord.class);
        startActivity(gotoadd);
    }
}