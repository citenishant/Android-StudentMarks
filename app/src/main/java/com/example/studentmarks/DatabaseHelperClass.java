package com.example.studentmarks;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "student_database";
    //Database Table name
    private static final String TABLE_NAME = "STUDENT";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ROLL = "roll";
    public static final String SEM = "sem";
    public static final String MARKS = "marks";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+ ROLL +" TEXT NOT NULL,"+ SEM +" TEXT NOT NULL,"+ MARKS +" TEXT NOT NULL);";
    //Constructor
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add student Data
    public void addStudent(StudentClass studentClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME, studentClass.getName());
        contentValues.put(DatabaseHelperClass.ROLL, studentClass.getRoll());
        contentValues.put(DatabaseHelperClass.SEM, studentClass.getSem());
        contentValues.put(DatabaseHelperClass.MARKS, studentClass.getMarks());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<StudentClass> getStudentList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<StudentClass> storeStudent = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String roll = cursor.getString(2);
                String sem = cursor.getString(3);
                String marks = cursor.getString(4);
                storeStudent.add(new StudentClass(id,name,roll,sem,marks));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeStudent;
    }

    public void updateStudent(StudentClass studentClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME,studentClass.getName());
        contentValues.put(DatabaseHelperClass.ROLL,studentClass.getRoll());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(studentClass.getId())});
    }

    public void deleteStudent(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}