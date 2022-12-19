package com.example.studentmarks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<StudentClass> studentClasses = databaseHelperClass.getStudentList();

        if (studentClasses.size() > 0){
            StudentAdapterClass studenadapterclass = new StudentAdapterClass(studentClasses,ViewStudentActivity.this);
            recyclerView.setAdapter(studenadapterclass);
        }else {
            Toast.makeText(this, "There is no student in the database", Toast.LENGTH_SHORT).show();
        }




    }
}