package com.example.myapplication.web_data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class SqliteActivity extends AppCompatActivity {
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqlite);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        View view = LayoutInflater.from(SqliteActivity.this).inflate(R.layout.activity_sqlite, null);
        createDb(view);
        addRecord(view);
        update(view);
        findStudent(view);
        delete(view);
    }

    public void createDb(View view){
        SQLiteHelper sqLiteHelper = new SQLiteHelper(SqliteActivity.this);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
    }

    public void addRecord(View view){
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("name", "test1");
        values.put("age", 11);
        sqLiteDatabase.insert("user", null, values);

        Log.i("SQLite", "插入一条数据");

    }

    public void update(View view){
        ContentValues values = new ContentValues();
        values.put("age", 88);
        sqLiteDatabase.update("user", values, "name=?", new String[]{"test1"});

        Log.i("SQLite", "updateRecord");
    }

    public void findStudent(View view){
        Cursor cursor = sqLiteDatabase.query("user", new String[]{"id", "name", "age"}, null, null, null, null, null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);

            Log.i("SQLite", "id:" + id + " name:" + name + " age:" + age);
        }
    }

    public void delete(View view){
        sqLiteDatabase.delete("user", "name=?", new String[]{"test1"});

        Log.i("SQLite", "删除一条数据");
    }

}