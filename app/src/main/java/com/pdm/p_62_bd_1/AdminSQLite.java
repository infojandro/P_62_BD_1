package com.pdm.p_62_bd_1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLite extends SQLiteOpenHelper {

    private static AdminSQLite sInstance;

    static synchronized AdminSQLite getInstance(Context context, String name, CursorFactory factory, int version) {
        if (sInstance == null) {
            sInstance = new AdminSQLite(context.getApplicationContext(),name,factory,version);
        }
        return sInstance;
    }

    private AdminSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE agenda (_id INTEGER primary key AUTOINCREMENT, nombre TEXT, email TEXT, tfno TEXT, fecha TIMESTAMP DEFAULT CURRENT_DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE agenda RENAME TO agenda_vieja");
        db.execSQL("CREATE TABLE agenda (_id INTEGER primary key AUTOINCREMENT, nombre TEXT, email TEXT, tfno TEXT, fecha TIMESTAMP DEFAULT CURRENT_DATE)");
    }

}
