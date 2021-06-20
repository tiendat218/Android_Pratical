package com.example.pratical_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.pratical_android.R;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pratical_android.database.DBHepler;

public class UpdateAct extends AppCompatActivity implements View.OnClickListener {
    private DBHepler db;
    private int _id;
    private EditText edName;
    private EditText edDesignation;
    private EditText edSalary;
    ///abc

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = new DBHepler(this);

        initView();

        //Get Data From ListEmployeeActivity
        Intent intent = getIntent();

        _id = intent.getIntExtra(DBHepler.ID,0);
        String name = intent.getStringExtra(DBHepler.EMPLOYEE_NAME);
        String designation = intent.getStringExtra(DBHepler.DESIGNATION);
        String salary = intent.getStringExtra(DBHepler.SALARY);

        edName.setText(name);
        edDesignation.setText(designation);
        edDesignation.setText(salary);
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.edName);
        edDesignation = (EditText) findViewById(R.id.edDesignation);
        edSalary = (EditText) findViewById(R.id.edSalary);

        Button btUpdate = (Button) findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(this);
        Button btDelete = (Button) findViewById(R.id.btDelete);
        btDelete.setOnClickListener(this);
    }

    protected void onUpdate() {
        String isUpdate  =db.updateEmployee(_id, edName.getText().toString(), edDesignation.getText().toString() + "update", edSalary.getText().toString());
        Toast.makeText(this, isUpdate,Toast.LENGTH_SHORT).show();
        finish();

    }

    protected void onDelete() {
        Toast.makeText(this, db.deleteEmployee(_id), Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btUpdate:
                onUpdate();
                break;
            case R.id.btDelete:
                onDelete();
                break;
            default:
                break;
        }

    }
}
