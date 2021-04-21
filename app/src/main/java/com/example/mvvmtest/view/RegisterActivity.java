package com.example.mvvmtest.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvvmtest.model.UserModel;
import com.example.mvvmtest.R;
import com.example.mvvmtest.viewmodel.UserViewModel;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private Button signInBtn;
    private Button signUpBtn;
    private EditText userEditText , passEditText , passConfirmText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        userViewModel = new UserViewModel();
        userViewModel.init(this);
        signInBtn = findViewById(R.id.sign_up_btn);
        signUpBtn = findViewById(R.id.sign_up_btn);
        userEditText = findViewById(R.id.login_input);
        passEditText = findViewById(R.id.pass_input);
        passConfirmText = findViewById(R.id.pass_confirm_input);
    }
    public void OnReturnLoginBtnClick(View v){
        finish();
    }
    public void OnSignUpBtnClick(View v){
        String user = userEditText.getText().toString();
        String pass = passEditText.getText().toString();
        String passConfirm = passConfirmText.getText().toString();
        if (user.isEmpty() || pass.isEmpty() || passConfirm.isEmpty()){
            Snackbar.make(v , R.string.empty_fields  , Snackbar.LENGTH_SHORT).show();
            return;
        }
        else if (!pass.equals(passConfirm)){
            Snackbar.make(v , R.string.pass_not_equal  , Snackbar.LENGTH_SHORT).show();
            return;
        }
        else if (userViewModel.isLoginExist(user)){
            Snackbar.make(v , R.string.login_err  , Snackbar.LENGTH_SHORT).show();
            return;
        }
        UserModel userModel = new UserModel(user , pass);
        userViewModel.addUser(userModel);
        userViewModel.setCurrentUserId(userModel.getUser_id());
        Intent i = new Intent(this, MainScreenActivity.class);
        startActivity(i);
    }

}