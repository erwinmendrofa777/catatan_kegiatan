package com.example.todoapp.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.data.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private static final String DATE_FORMAT = "dd/MM/yyy";  //british format
    private final TodoFragment todoFragment;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    private List<Task> taskList;

    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    public TaskAdapter(TodoFragment todoFragment, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.todoFragment =todoFragment;
        this.recyclerViewClickInterface=recyclerViewClickInterface;
    }



    public void setData(List<Task> data){
        taskList = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater, parent);
    }


    //to find the position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.onBind(task);
    }

    @Override
    public int getItemCount() {
        if(taskList != null){
            return taskList.size();}
        else {
            return 0;
        }
    }



    //created to find the position of clicked item
    public List<Task> getTaskList() {
        return taskList;
    }


    //-------INNER CLASS---------//
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTextView;
        private  TextView descTextView;
        private TextView todoDate;




        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.recyclerview_item, parent, false));

            titleTextView = itemView.findViewById(R.id.title_tv);
            descTextView = itemView.findViewById(R.id.description_tv);

            todoDate = itemView.findViewById(R.id.todo_date);


            itemView.setOnClickListener( new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }



        //setting data on the layout
        public void onBind(Task task) {

            titleTextView.setText(task.getTitle());
            descTextView.setText(task.getDescription());

            //formatting date to insert
            String updatedAt= dateFormat.format(task.getUpdatedDate());
            todoDate.setText(updatedAt);
        }
    }


}