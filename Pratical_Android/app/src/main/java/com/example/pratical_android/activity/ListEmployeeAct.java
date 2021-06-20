package com.example.pratical_android.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.example.pratical_android.R;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pratical_android.database.DBHepler;

public class ListEmployeeAct extends AppCompatActivity {
    private DBHepler db;
    private Cursor c;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_employee);
        db = new DBHepler(this);
        ListView lvEmployee = (ListView) findViewById(R.id.lvEmployee);
        c = db.getAllEmployee();
        adapter = new SimpleCursorAdapter(this, R.layout.items_employee, c, new String[]{
                DBHepler.ID, DBHepler.EMPLOYEE_NAME, DBHepler.DESIGNATION, DBHepler.SALARY}, new int[]{
                R.id.tvId, R.id.tvName, R.id.tvDesignation, R.id.tvSalary}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lvEmployee.setAdapter(adapter);
        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                int _id = cursor.getInt(cursor.getColumnIndex(DBHepler.ID));
                String name = cursor.getString(cursor.getColumnIndex(DBHepler.EMPLOYEE_NAME));
                String designation = cursor.getString(cursor.getColumnIndex(DBHepler.DESIGNATION));
                String salary = cursor.getString(cursor.getColumnIndex(DBHepler.SALARY));

                Intent intent = new Intent(ListEmployeeAct.this, UpdateAct.class);
                intent.putExtra(DBHepler.ID, _id);
                intent.putExtra(DBHepler.EMPLOYEE_NAME, name);
                intent.putExtra(DBHepler.DESIGNATION, designation);
                intent.putExtra(DBHepler.SALARY, salary);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Relaod Data in ListView
        c = db.getAllEmployee();
        adapter.changeCursor(c);
        adapter.notifyDataSetChanged();
        db.close();
    }
}

