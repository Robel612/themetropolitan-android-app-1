package com.sandiprai.themetropolitan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private View view;
    private EditText textEdit;
    public Notifications notify;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_search, container, false);

        //Get the buttons and start their listener
        Button buttonSearch = view.findViewById(R.id.buttonSearch);
        Button buttonClear = view.findViewById(R.id.buttonClear);
        //textEdit = view.findViewById(R.id.buttonClear);
        //notify = new Notifications();

        buttonSearch.setOnClickListener(this);
        buttonClear.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.buttonSearch:
                CharSequence text = "Search button clicked!";
                showToast(text);
                break;

            case R.id.buttonClear:
                TextInputEditText searchText = getView().findViewById(R.id.textInputSearch);
                searchText.setText("");
                //notify.makeNotification();
                //CharSequence text1 = "Search text cleared!";
                //showToast(text1);
                break;
        }

    }

    //Shows the passed string as a toast
    public void showToast(CharSequence text) {
        int duration1 = Toast.LENGTH_SHORT;

        Toast toast1 = Toast.makeText(getActivity().getApplicationContext(), text, duration1);
        toast1.show();
    }
}
