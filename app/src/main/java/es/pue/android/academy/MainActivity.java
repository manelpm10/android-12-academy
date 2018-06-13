package es.pue.android.academy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import es.pue.android.academy.adapters.StudentAdapter;
import es.pue.android.academy.database.StudentSqliteHelper;
import es.pue.android.academy.model.Student;

public class MainActivity extends AppCompatActivity {

    // Ouch! All in view pattern.
    private List<Student> students;
    private ListView lvStudents;
    private StudentAdapter studentAdapter;
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new StudentSqliteHelper(this, Constants.DB_NAME, null, 1);
        db = dbHelper.getWritableDatabase();

        students = new ArrayList<>();
        getAllStudents();
        studentAdapter = new StudentAdapter(this, R.layout.student_item, students);

        lvStudents = findViewById(R.id.lvStudents);
        lvStudents.setAdapter(studentAdapter);

    }

    private void getAllStudents() {
        Cursor cursor = db.rawQuery("SELECT * FROM students", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("name"));

                students.add(new Student(name, age));
                cursor.moveToNext();
            }
        }
        cursor.close();
    }
}
