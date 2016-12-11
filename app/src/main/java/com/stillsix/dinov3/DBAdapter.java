package com.stillsix.dinov3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sean.gallagher on 12/4/16.
 */
public class DBAdapter {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PRONOUNCE = "pronounce";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DIET = "diet";
    public static final String KEY_ERA = "era";
    public static final String KEY_ELEMENT = "element";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_IMAGE = "image";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "dinos2";
    private static final String DATABASE_TABLE = "dino_details";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table dino_details (_id integer primary key autoincrement, "
                    + "title text not null, "
                    + "pronounce text not null, "
                    + "description text not null, "
                    + "diet text not null, "
                    + "era text not null, "
                    + "element text not null, "
                    + "location text not null, "
                    + "image text not null);";

    private static final String QUERY_GETALL = "SELECT * FROM " + DATABASE_NAME;

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS dino_details");
            onCreate(db);
        }
    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
    }

    public long insertDino(String title, String pronounce, String description, String diet, String era, String element, String location, String image) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_PRONOUNCE, pronounce);
        initialValues.put(KEY_DESCRIPTION, description);
        initialValues.put(KEY_DIET, diet);
        initialValues.put(KEY_ERA, era);
        initialValues.put(KEY_ELEMENT, element);
        initialValues.put(KEY_LOCATION, location);
        initialValues.put(KEY_IMAGE, image);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public Cursor getAllDinos() {
        //return db.rawQuery("SELECT * FROM dinos", new String[] {"Title", "Image"});
        //return db.execSQL(QUERY_GETALL);
        return db.query(DATABASE_TABLE, new String[]{
                KEY_ROWID,
                KEY_TITLE,
                KEY_PRONOUNCE,
                KEY_DESCRIPTION,
                KEY_DIET,
                KEY_ELEMENT,
                KEY_ERA,
                KEY_LOCATION,
                KEY_IMAGE}, null, null, null, null, null);

    }

    public Cursor getThisDino(String dino) {
        //return db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_PRONOUNCE, KEY_DESCRIPTION}, "title=?", String dino, KEY_ROWID, null, null, null);
        return db.rawQuery("SELECT * FROM dino_details WHERE title=\'" + dino + "\'", null);
        //return db.execSQL(QUERY_GETALL);
        /*return db.query(DATABASE_TABLE, new String[]{
                KEY_ROWID,
                KEY_TITLE,
                KEY_PRONOUNCE,
                KEY_DESCRIPTION,
                KEY_DIET,
                KEY_ELEMENT,
                KEY_ERA,
                KEY_LOCATION,
                KEY_IMAGE}, null, null, null, null, null);*/
    }
    public Cursor getDinoIcons(String dino) {
        //return db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_TITLE, KEY_PRONOUNCE, KEY_DESCRIPTION}, "title=?", String dino, KEY_ROWID, null, null, null);
        return db.rawQuery("SELECT diet,element,era FROM dino_details WHERE title=\'" + dino + "\'", null);
    }
    public Cursor getTheseElementDinos(String element) {
        return db.rawQuery("SELECT * FROM dino_details WHERE element=\'" + element + "\'", null);

    }
    public Cursor getTheseDietDinos(String diet) {
        return db.rawQuery("SELECT * FROM dino_details WHERE diet=\'" + diet + "\'", null);

    }
}

