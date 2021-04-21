package com.example.mvvmtest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvvmtest.model.UserModel;
import com.example.mvvmtest.R;
import com.example.mvvmtest.repository.Repo;
import com.example.mvvmtest.viewmodel.QuizItemViewModel;
import com.example.mvvmtest.viewmodel.UserViewModel;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private Button signInBtn;
    private Button signUpBtn;
    private EditText userEditText , passEditText;
    public QuizItemViewModel quizItemViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        userViewModel = new UserViewModel();
        userViewModel.init(this);

        signInBtn = findViewById(R.id.sign_in_btn);
        signUpBtn = findViewById(R.id.sign_up_btn);
        userEditText = findViewById(R.id.login_input);
        passEditText = findViewById(R.id.pass_input);
    }
    public void onSignInBtnClick(View v){
        String login = userEditText.getText().toString();
        String pass = passEditText.getText().toString();
        if (login.isEmpty() || pass.isEmpty()){
            Snackbar.make(v, R.string.empty_fields, Snackbar.LENGTH_LONG)
                    .show();
            return;
        }
        if (!userViewModel.isUserExist(login , pass)){
            Snackbar.make(v, R.string.passincorrect, Snackbar.LENGTH_LONG)
                    .show();
            return;
        }
        else{
            Intent i = new Intent(this , MainScreenActivity.class);
            startActivity(i);
            UserModel userModel = userViewModel.getUserByLogin(login);
            userViewModel.setCurrentUserId(userModel.getUser_id());
        }
    }
    public void onSignUpBtnClick(View v){
        Intent i = new Intent(this , RegisterActivity.class);
        startActivity(i);
    }

}