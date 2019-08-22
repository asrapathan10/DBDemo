package com.nirvanaapp.sourcekode.demoasra;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {


    TextView textViewViewEmployees;
    EditText editTextName, editTextSalary;
    Spinner spinnerDepartment;
    DatabaseHelper mydb;
    Button addemployee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewViewEmployees = (TextView) findViewById(R.id.textViewViewEmployees);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSalary = (EditText) findViewById(R.id.editTextSalary);
        spinnerDepartment = (Spinner) findViewById(R.id.spinnerDepartment);
        addemployee=(Button)findViewById(R.id.buttonAddEmployee);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());
        Adddata();

        textViewViewEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cviwe = new Intent(MainActivity.this,EmployeeActivity.class);
                startActivity(cviwe);
            }
        });

    }
    public void Adddata(){

        addemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb =new DatabaseHelper(getApplicationContext());

                boolean isInserted=mydb.insertData(editTextName.getText().toString(),
                        spinnerDepartment.getSelectedItem().toString(),
                        editTextSalary.getText().toString());
                if(isInserted==true){
                    Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Dta is not inserted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean inputsAreCorrect(String name, String salary) {
        if (name.isEmpty()) {
            editTextName.setError("Please enter a name");
            editTextName.requestFocus();
            return false;
        }

        if (salary.isEmpty() || Integer.parseInt(salary) <= 0) {
            editTextSalary.setError("Please enter salary");
            editTextSalary.requestFocus();
            return false;
        }
        return true;
    }



}