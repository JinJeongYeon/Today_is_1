package org.ksj.today_is;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }//생성자

    @Override
    public void onCreate(SQLiteDatabase db) {
        String main_sql = "create table if not exists main_table(_id integer primary key autoincrement, Date_name text not null, Group_name text not null, Contents text not null, Check_name integer not null, Place text, Start_time text, Notice_time text);";
        String main_group_sql = "create table if not exists main_group_table(_id integer primary key autoincrement, Group_name text not null);";
        String memo_sql = "";
        String diary_sql = "";

        db.execSQL(main_sql);
        db.execSQL(main_group_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    String sql_main = "drop table if exists main_table;";
    String sql_main_group = "drop table if exists main_group_table;";
        onCreate(db);
    }//버전 업그레이드 시 사용
}
