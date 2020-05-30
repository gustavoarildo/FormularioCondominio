package com.example.formulariocondominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper  extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "responsavel_table";
    private static final String COL1 = "id";
    private static final String COL2 = "nome";
    private static final String COL3 = "telefone";
    private static final String COL4 = "valorMensalidade";
    private static final String COL5 =  "debitoTotal";


    public DatabaseHelper(Context context) {

        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT, "+
                COL3 +" TEXT, "+
                COL4 +" TEXT, "+
                COL5 +" TEXT "+ ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean addData(String item, String item2, String item3, String item4){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, item2);
        contentValues.put(COL4, item3);
        contentValues.put(COL5, item4);

        Log.d(TAG, "addData: Adding " + item +"and " + item2 +"and " + item3+ "and " + item4+ " to " + TABLE_NAME);
        Long result = db.insert(TABLE_NAME,null,contentValues);
        //if data as inserted incorrectly it will return -1
        if(result == -1){
            return  false;
        } else {
            return  true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME," ID " + " =" + id, null);

        if(result == -1){
            return  false;
        } else {
            return  true;
        }
    }

    public boolean alteraData(String id, String newEntry, String newEntry2, String newEntry3, String newEntry4){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, newEntry);
        contentValues.put(COL3, newEntry2);
        contentValues.put(COL4, newEntry3);
        contentValues.put(COL5, newEntry4);

        int result = db.update(TABLE_NAME,contentValues," ID " + " =" + id, null);

        if(result == -1){
            return  false;
        } else {
            return  true;
        }
    }

}
