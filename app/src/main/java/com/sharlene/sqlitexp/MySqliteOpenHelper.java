package com.sharlene.sqlitexp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="RIT.db";
    public static int DATABASE_VERSION= 1;

    public MySqliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USER( EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(sqLiteDatabase);
    }

    public void insertRecord(String Email, String Password){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL",Email);
        contentValues.put("PASSWORD",Password);
        sqLiteDatabase.insert("USER",null,contentValues);
    }

    public Cursor selectRecord(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM USER;",null);
        return cursor;
    }

    public void updateRecord(String Email, String password){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL",Email);
        contentValues.put("PASSWORD",password);
        sqLiteDatabase.update("USER",contentValues,"Email=?",new String[]{Email});
    }
    public void deleteRecord(String email){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("USER","Email= ?",new String[]{email});
    }
}
