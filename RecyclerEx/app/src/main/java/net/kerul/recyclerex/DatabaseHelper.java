package net.kerul.recyclerex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME="keepword.db";
    public static final int DATABASEVERSION=1;
    public static final String TABLENAME="tbl_words";
    //constructor
    public DatabaseHelper (Context context){
        super (context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //the database definition
        String sqlcreatetable="CREATE TABLE "+TABLENAME+" (english TEXT, france TEXT);";
        db.execSQL(sqlcreatetable);
        //dummy data
        db.execSQL("INSERT INTO "+TABLENAME+" (english, france) VALUES('good morning','bonjour')");
        db.execSQL("INSERT INTO "+TABLENAME+" (english, france) " +
                "VALUES('good afternoon','bonne apres-midi')");
        db.execSQL("INSERT INTO "+TABLENAME+" (english, france) " +
                "VALUES('good night','bonne nuit')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
        onCreate(db);
    }
}
