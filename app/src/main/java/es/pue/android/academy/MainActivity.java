package es.pue.android.academy;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        dbHelper = new StudentSqliteHelper(this, Constants.DB_NAME, null, Constants.DB_VERSION);
        db = dbHelper.getWritableDatabase();

        students = new ArrayList<>();
        getAllStudents();
        studentAdapter = new StudentAdapter(this, R.layout.student_item, students);

        lvStudents = findViewById(R.id.lvStudents);
        lvStudents.setAdapter(studentAdapter);

        Button btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
                refreshStudentsList();
            }
        });

        Button btnUpdateStudent = findViewById(R.id.btnUpdateStudent);
        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
                refreshStudentsList();
            }
        });

        Button btnDeleteStudent = findViewById(R.id.btnDeleteStudent);
        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
                refreshStudentsList();
            }
        });
    }

    private void getAllStudents() {
        Cursor cursor = db.rawQuery("SELECT * FROM students", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));

                students.add(new Student(name, age));
                cursor.moveToNext();
            }
        }
        cursor.close();
    }

    private void refreshStudentsList() {
        students.clear();
        getAllStudents();
        studentAdapter.notifyDataSetChanged();
    }

    private void addStudent() {
        if (null != db) {
            ContentValues rowStudent = new ContentValues();
            rowStudent.put("name", "Steve Jobs");
            rowStudent.put("age", 53);

            db.insert(Constants.TABLE_STUDENT, null, rowStudent);
        }
    }

    public void updateStudent() {
        if (null != db) {
            ContentValues rowStudent = new ContentValues();
            rowStudent.put("age", 35);

            db.update(Constants.TABLE_STUDENT, rowStudent, "ID = ?", new String[]{"1"});
        }

    }

    public void deleteStudent() {
        if (null != db) {
            db.delete(Constants.TABLE_STUDENT, "ID = ?", new String[]{"1"});
        }
    }
}
