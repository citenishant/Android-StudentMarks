package com.example.studentmarks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_name,editText_roll,editText_sem,editText_marks;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_name = findViewById(R.id.edittext_name);
        editText_roll = findViewById(R.id.edittext_roll);
        editText_sem = findViewById(R.id.edittext_sem);
        editText_marks = findViewById(R.id.edittext_marks);

        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringRoll = editText_roll.getText().toString();
                String stringSem = editText_sem.getText().toString();
                String stringMarks = editText_marks.getText().toString();

                if (stringName.length() <=0 || stringRoll.length() <=0 || stringSem.length() <=0 || stringMarks.length() <=0){
                    Toast.makeText(MainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(MainActivity.this);
                    StudentClass studentClass = new StudentClass(stringName,stringRoll,stringSem,stringMarks);
                    databaseHelperClass.addStudent(studentClass);
                    Toast.makeText(MainActivity.this, "Add Student Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewStudentActivity.class);
                startActivity(intent);
            }
        });


    }
}