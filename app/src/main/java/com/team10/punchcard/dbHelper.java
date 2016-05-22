package com.team10.punchcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by hrz on 16-5-18.
 */
public class dbHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "words_Library";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "TOEFL";

    //构造函数，创建数据库
    public dbHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //建表
    public void onCreate(SQLiteDatabase db) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        sql = "CREATE TABLE " + TABLE_NAME
                + "(word VARCHAR(30) PRIMARY KEY,"
                + " label INTEGER NOT NULL)";
        db.execSQL(sql);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public long insert(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("word", word);
        cv.put("label", 0);
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
    }
    public Cursor query() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME + " where label=0 limit 0,10", null);
       /* Cursor cur = cursor;
        while(cur.moveToNext())
        {
            int nameColumnIndex = cur.getColumnIndex("word");
            String word = cur.getString(nameColumnIndex);
            String sql = "update "+TABLE_NAME+" set label=1 where word=\""+word+"\"";
            db.execSQL(sql);
        }*/

        //   Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE _id = "+id, null);
        //  Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE BookName LIKE ?", args);
        return cursor;
    }


    //获取游标
  /*  public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }

    //插入一条记录
    public long insert(String word, int label) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("word", word);
        cv.put("label", label);
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
    }

    //根据条件查询
    public Cursor query(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE _id = "+id, null);
      //  Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE BookName LIKE ?", args);
        return cursor;
    }

    //删除记录
    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where ="_id = ?";
        String[] whereValue = { Integer.toString(id) };
        db.delete(TABLE_NAME, where, whereValue);
    }

    //更新记录
    public void update(int id, String bookName,String author,String publisher) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id = ?";
        String[] whereValue = { Integer.toString(id) };
        ContentValues cv = new ContentValues();
        cv.put("BookName", bookName);
        cv.put("Author", author);
        cv.put("Publisher", publisher);
        db.update(TABLE_NAME, cv, where, whereValue);
    }*/

}
