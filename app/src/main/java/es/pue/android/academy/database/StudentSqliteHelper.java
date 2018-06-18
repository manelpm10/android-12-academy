package es.pue.android.academy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import es.pue.android.academy.Constants;

public class StudentSqliteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE "+ Constants.TABLE_STUDENT+" (" +
            "    ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "    name TEXT NOT NULL," +
            "    age INT" +
            ")";
    String sqlDrop = "DROP TABLE IF EXISTS "+Constants.TABLE_STUDENT;

    public StudentSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.sqlCreate);
    }

    /**
     * In this part you can delete and create again or do the changes field by field (usually better).
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.sqlDrop);
        this.onCreate(db);
    }
}
