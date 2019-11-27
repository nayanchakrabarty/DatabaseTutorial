package com.example.databasetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "AlarmHolder";
    private static final String TABLENAME = "AlarmTable";
    private static final String DBVERSION = "1.0";
    private static final String ID = "ID";
    private static final String TODO = "TODO";
    private static final String TIME = "TIME";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE =
                "CREATE TABLE " + TABLENAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TODO + " TEXT," + TIME + " TEXT)";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    boolean dataInsurt(String todo,String time){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TODO,todo);
        contentValues.put(TIME,time);
        long value = database.insert(TABLENAME,null,contentValues);
        database.close();

        boolean isinsurted;
        isinsurted = value != -1;
        return isinsurted;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLENAME;

        Cursor c = sqLiteDatabase.rawQuery(selectQuery,null);

        //sqLiteDatabase.close();
        return c;

    }


    public void deleteData(String todo,String time){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();


        sqLiteDatabase.delete(TABLENAME,"TODO=? and TIME=?",new String[]{todo,time});

        //String deleteadata=" DELETE FROM "+TABLENAME+" where "+TODO+"='"+todo+"' and "+TIME+" ='"+time+"'";

       //sqLiteDatabase.rawQuery(deleteadata,null);

    }




}
