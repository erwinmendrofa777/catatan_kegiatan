package com.example.todoapp.UI;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.todoapp.R;
import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;
import java.util.Date;
import static android.widget.Toast.LENGTH_SHORT;



public class UpdateFragment extends Fragment {

    MainViewModel mTodoViewModel;
    private Task task;  //only in this class protected --package

    EditText edit_title,edit_desc;
    Button update_btn;
    Button share_btn;

    private Repository repository;

    String update_description,update_title;

    private String mShareTextEditText, mShareDescriptionText;


    public UpdateFragment() {

    }

    public static Fragment newInstance() {

        return  new UpdateFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_update, container, false);

        mTodoViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        //to get the list clicked
        task=mTodoViewModel.getTask();


        //the values on the list that are clicked
        update_title= task.getTitle();
        update_description=  task.getDescription();


        //the existing values on the form
        edit_title = (EditText)view.findViewById(R.id.update_title);
        edit_desc = (EditText)view.findViewById(R.id.update_desc);
        update_btn=(Button)view.findViewById(R.id.update_btn);

        share_btn =(Button)view.findViewById(R.id.button2);
        //------------


        //sets the data that is clicked on the form
        edit_title.setText(update_title);
        edit_desc.setText(update_description);



        repository = Repository.getRepository(getActivity().getApplication());


        share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText(share_btn);
            }
        });



        update_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String title = edit_title.getText().toString();
                String desc = edit_desc.getText().toString();

                if (title.isEmpty())
                {
                    showMissing(view);
                }
                else {

                    task.setTitle(title);
                    task.setDescription(desc);
                    task.setUpdatedDate(new Date());

                    repository.update(task);

                    showToast(view);


                    Toast toast = Toast.makeText(getActivity(), "Updated Successfully", LENGTH_SHORT);

                    //to switch to the another fragment
                    //-----------

                    TodoFragment todoFragment = TodoFragment.newInstance();
                    assert getFragmentManager() != null;
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.container, todoFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                /*
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, TodoFragment.newInstance())
                        .commitNow();
                    */

                }
            }
        });

        return  view;
    }


    public void showToast(View view) {
        Toast toast = Toast.makeText(getActivity(), "Updated Todo",
                Toast.LENGTH_SHORT);
        toast.show();
    }


    //for menu
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.updatemenu, menu);
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

            case R.id.shareall:
                {
                shareText(share_btn);
            }

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void showMissing(View view) {
        Toast toast = Toast.makeText(getActivity(), "Empty title makes invalid todo. Enter a value for the title",
                Toast.LENGTH_LONG);
        toast.show();
    }


    //Implicit intents

    public void shareText(Button share_btn) {
        //values to be shared using implicit intent
        String txt =mShareTextEditText=edit_title.getText().toString();
        String des= mShareDescriptionText=edit_desc.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(getActivity())
                .setType(mimeType)
                .setChooserTitle(txt)
                .setText(txt+"\n"+des)
                .startChooser();
    }
}