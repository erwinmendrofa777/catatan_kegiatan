package com.example.todoapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.todoapp.UI.Add_Data_Fragments;
import com.example.todoapp.UI.MainViewModel;
import com.example.todoapp.UI.RecyclerViewClickInterface;
import com.example.todoapp.UI.TaskAdapter;
import com.example.todoapp.UI.TodoFragment;
import com.example.todoapp.data.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();


    private RecyclerView recyclerView;
    private List<Task> taskList;
    private TaskAdapter adapter;
    private FloatingActionButton fab;
    private MainViewModel viewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //calling fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TodoFragment.newInstance())
                    .commitNow();
        }

    }



}


