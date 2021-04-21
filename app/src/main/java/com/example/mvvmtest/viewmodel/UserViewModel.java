package com.example.mvvmtest.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.model.UserModel;
import com.example.mvvmtest.repository.Repo;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {
    ArrayList<UserModel> userList = new ArrayList<>();

    public void init(Context context){
        userList = Repo.getInstance().getUserList();
    }
    public ArrayList<UserModel> getAllUser(){
        return userList;
    }
    public void addUser(UserModel model){
        Repo.getInstance().addUser(model);
        userList = Repo.getInstance().getUserList();
    }
    public boolean isUserExist(String user , String pass){
        userList = Repo.getInstance().getUserList();
        for (UserModel model  : userList){
            if (model.getName().equals(user) && model.getPassword().equals(pass)){
                return true;
            }
        }
        return false;
    }
    public boolean isLoginExist(String user){
        userList = Repo.getInstance().getUserList();
        for (UserModel model  : userList){
            if (model.getName().equals(user)){
                return true;
            }
        }
        return false;
    }
    public void setCurrentUserId(int id){
        Repo.getInstance().setCurrentuser_id(id);
    }
    public UserModel getCurrentUser(){
        return Repo.getInstance().getUserById(Repo.getInstance().getCurrentuser_id());
    }
    public UserModel getUserById(int id){
        return Repo.getInstance().getUserById(id);
    }
    public UserModel getUserByLogin(String login){
        userList = Repo.getInstance().getUserList();
        for (UserModel model : userList) {
            if (model.getName().equals(login)){
                return model;
            }
        }
        return null;
    }
}
