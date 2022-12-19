package com.example.studentmarks;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapterClass extends RecyclerView.Adapter<StudentAdapterClass.ViewHolder> {

    List<StudentClass> student;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public StudentAdapterClass(List<StudentClass> student, Context context) {
        this.student = student;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.student_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final StudentClass studentClass = student.get(position);

        holder.textViewID.setText(Integer.toString(studentClass.getId()));
        holder.editText_Name.setText(studentClass.getName());
        holder.editText_Sem.setText(studentClass.getSem());
        holder.editText_Marks.setText(studentClass.getMarks());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringSem = holder.editText_Sem.getText().toString();
                String stringMarks = holder.editText_Marks.getText().toString();

                databaseHelperClass.updateStudent(new StudentClass(studentClass.getId(),
                        stringName,
                        stringSem,
                        stringMarks));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteStudent(studentClass.getId());
                student.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return student.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View editText_Roll;
        TextView textViewID;
        EditText editText_Name;
        EditText editText_Sem;
        EditText editText_Marks;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Roll = itemView.findViewById(R.id.edittext_roll);
            editText_Sem = itemView.findViewById(R.id.edittext_sem);
            editText_Sem = itemView.findViewById(R.id.edittext_marks);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
