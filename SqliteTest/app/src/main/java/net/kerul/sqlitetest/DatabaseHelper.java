package net.kerul.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "keepnotes";
    //database version
    public static final int DATABASE_VERSION = 5;
    public static final String TABLE_NAME = "tbl_notes";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        //creating table
        query = "CREATE TABLE " + TABLE_NAME + " (Title TEXT, Description TEXT);";
        db.execSQL(query);
        Log.d("createquery",query);

        //add dummy data
        db.execSQL("INSERT INTO tbl_notes (Title,Description)"+
                " VALUES('Chapter01', 'Basic Android Studio');");
        db.execSQL("INSERT INTO tbl_notes (Title,Description)"+
                " VALUES('Chapter02', 'GUI widgets in XML');");
        //this.addNotes("Chapter01","Basic Android Studio");
        //this.addNotes("Chapter02","GUI widgets in XML");
        //this.addNotes("Chapter03","Java programming");
        //this.addNotes("Chapter04","Adding buttons");
        //this.addNotes("Chapter05","Adding edittext");
        //this.addNotes("Chapter06","Adding layout");
    }

    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //add the new note
    public void addNotes(String title, String des) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Title", title);
        values.put("Description", des);

        //inserting new row
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        //close database connection
        sqLiteDatabase.close();
    }

    //get the all notes
    public ArrayList<NoteModel> getNotes() {
        ArrayList<NoteModel> arrayList = new ArrayList<>();

        //select all records
        // select all query
        String select_query= "SELECT * FROM " + TABLE_NAME +";";

        SQLiteDatabase db = this .getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setTitle(cursor.getString(0));
                noteModel.setDes(cursor.getString(1));
                Log.e(cursor.getString(0),cursor.getString(1));
                arrayList.add(noteModel);
            }while (cursor.moveToNext());
        }

        //last return
        return arrayList;
    }

}
