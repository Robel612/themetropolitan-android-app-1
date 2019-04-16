package com.sandiprai.themetropolitan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UserSettings extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        mAuth = FirebaseAuth.getInstance();

        //Get the toolbar and load it as the main toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarInUserSettings);
        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        //Following code is for having the title in the center; otherwise it would be aligned to
        //left as default
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String appName = "User Settings";
        toolbarTitle.setText(appName.toUpperCase());
         username = (EditText) findViewById(R.id.editTextDisplayName);

        setUpUserSettings();
        loadUserInformation();




    }


    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, SignIn.class));
        }
    }


    private void loadUserInformation(){

        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            if (user.getDisplayName() != null) {
                username.setText(user.getDisplayName());
            }
            String email = user.getEmail();
        }

    }


    public void setUpUserSettings(){

      //  username.setText("Dummy Name");

        TextView email = findViewById(R.id.textEmailinSettings);
        email.setText("DummyEmail@yahoo.com");

        findViewById(R.id.buttonSave).setOnClickListener(this);

        Button buttonSignOut = findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(this);

      //  findViewById(R.id.buttonSignOut).setOnClickListener(this);
    }

    private void saveUserInformation(){

        String displayName = username.getText().toString();

        if(displayName.isEmpty()){

            username.setError("Name required");
            username.requestFocus();
            return;
        }

        FirebaseUser user = mAuth.getCurrentUser();

        if(user!= null){
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build();

            user.updateProfile(profile)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(UserSettings.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });




        }






    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSignOut:
                Toast.makeText(getApplicationContext(), "Sign Out button clicked!",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonSave:
                    saveUserInformation();
                break;

        }

    }
}

