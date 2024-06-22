package com.example.myapplication.web_data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;  // 数据库版本号
    private static String db_name = "test";  // 数据库名称
    public SQLiteHelper(@Nullable Context context) {
        super(context, db_name, null, VERSION);
    }

    // 第一次建库时调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据表
        String sql = "create table user(id int, name varchar(20), age int)";
        db.execSQL(sql);

        Log.i("SQLiteHelper", "onCreate.....");
    }

    // 更新版本号时执行
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("SQLiteHelper", "update Database.....");
    }
}
