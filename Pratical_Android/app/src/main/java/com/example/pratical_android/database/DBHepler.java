package com.example.pratical_android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pratical_android.activity.Employee;

public class DBHepler extends SQLiteOpenHelper {
    private static final String DB_NAME = "EMPLOYEE";
    private static final int DB_VERSION = 1;

    public static String TABLE_NAME = "TBL_EMPLOYEE";
    public static String ID = "_id";
    public static String EMPLOYEE_NAME = "employeeName";
    public static String DESIGNATION = "designation";
    public static String SALARY = "salary";

    public DBHepler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                ID + "INTEGER PRIMARY KEY , " +
                EMPLOYEE_NAME + "TEXT , " +
                DESIGNATION + "TEXT , " +
                SALARY + "INT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public Cursor getAllEmployee() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public String addEmployee(Employee employee) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_NAME, employee.getEmployeeName());
        contentValues.put(DESIGNATION, employee.getDesignation());
        contentValues.put(SALARY, employee.salary);
        long isAdd = db.insert(TABLE_NAME, null, contentValues);
        if (isAdd == -1) {
            return "Add Fail";
        }
        db.close();
        return "Add success";
    }

    public String updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMPLOYEE_NAME, employee.getEmployeeName());
        contentValues.put(DESIGNATION, employee.getDesignation());
        contentValues.put(SALARY, employee.salary);
        int isUpdate = db.update(TABLE_NAME, contentValues, ID + "= ? ", new String[]{employee.id + ""});
        if (isUpdate > 0) {
            return "Update success";
        }
        db.close();
        return "Update fail";
    }

    public String deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int isDelete = db.delete(TABLE_NAME, ID + " = ?", new String[]{id + ""});
        if (isDelete > 0) {
            return "Delete success";
        }
        db.close();
        return "Delete fail";
    }
}
