package com.example.todoapp.UI;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.R;
import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

//fist ui
public class Add_Data_Fragments extends Fragment {


    //variables for objects and views
    private EditText titleEditText;
    private EditText descEditText;
    private Button submitButton;
    private MainViewModel todoViewModel;
    private Repository repository;


    public Add_Data_Fragments()
    {
    }

    public static Fragment newInstance() {
        return new Add_Data_Fragments();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add__data__fragments, container, false);

        titleEditText = (EditText) view.findViewById(R.id.title_et);
        descEditText = (EditText)view.findViewById(R.id.desc_dt);
        submitButton = view.findViewById(R.id.submit_btn);

        //converting to string

        repository = Repository.getRepository(getActivity().getApplication());

        // submitButton = view.findViewById(R.id.submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {

            //to save data
            @Override
            public void onClick(View v) {

                String title = titleEditText.getText().toString();
                String desc = descEditText.getText().toString();

                if (title.isEmpty() ){
                    showMissing(view);
                }

                else {

                    //insert data to database
                    Task task = new Task(title, desc, new Date(), new Date(), 1);
                    repository.addTask(task);

                    showToast(view);

                    //to switch to the another fragment
                    //-----------
                    TodoFragment todoFragment = TodoFragment.newInstance();
                    assert getFragmentManager() != null;
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, todoFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    //-----------

                }
                /* alternatively
                //switches to the main fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, TodoFragment.newInstance())
                        .commitNow();
                        */

            }
        });

        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.addmenu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backbutton:  {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, TodoFragment.newInstance())
                        .commitNow();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void showToast(View view) {
        Toast toast = Toast.makeText(getActivity(), "Todo Inserted",
                Toast.LENGTH_SHORT);
        toast.show();
    }



    public void showMissing(View view) {
        Toast toast = Toast.makeText(getActivity(), "Empty title doesn't create a valid todo. Please enter a title",
                Toast.LENGTH_SHORT);
        toast.show();
    }
}