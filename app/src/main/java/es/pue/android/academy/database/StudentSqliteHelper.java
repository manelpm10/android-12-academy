package es.pue.android.academy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentSqliteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE students (" +
            "    ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "    name TEXT NOT NULL," +
            "    age INT" +
            ")";
    String sqlDrop = "DROP TABLE IF EXISTS students";

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
