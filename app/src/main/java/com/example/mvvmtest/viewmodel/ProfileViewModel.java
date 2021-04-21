package com.example.mvvmtest.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.BR;
import com.example.mvvmtest.model.ResultModel;
import com.example.mvvmtest.model.UserModel;
import com.example.mvvmtest.repository.Repo;
import com.example.mvvmtest.view.LoginActivity;

public class ProfileViewModel extends ViewModel implements Observable {
    PropertyChangeRegistry notifyPropertyChanged = new PropertyChangeRegistry();
    OnBtnClickListener onBtnClickListener;
    private UserModel currentUser;
    private Context context;

    public void setOnBtnClickListener(OnBtnClickListener onBtnClickListener) {
        this.onBtnClickListener = onBtnClickListener;
    }

    public void setCurrentUser(UserModel currentUser){
        this.currentUser = currentUser;
        notifyPropertyChanged.notifyChange(this , BR._all);
    }
    public void loadCurrentUserFromRepo(){
        this.currentUser = Repo.getInstance().getUserById(Repo.getInstance().getCurrentuser_id());
    }
    @Bindable
    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void setNumberOfTests(int num){
        currentUser.setNumberOfTests(num);
        notifyPropertyChanged.notifyChange(this , BR._all);
    }
    @Bindable
    public String getNumberOfTests(){
        currentUser.setNumberOfTests(currentUser.getResultList().size());
        return String.valueOf(currentUser.getNumberOfTests());
    }
    public void setName(String name){
        currentUser.setName(name);
        notifyPropertyChanged.notifyChange(this , BR._all);
    }
    @Bindable
    public String getName(){
        return currentUser.getName();
    }
    public void setAverageScore(float score){
        currentUser.setAverage_score(score);
        notifyPropertyChanged.notifyChange(this , BR._all);

    }
    @Bindable
    public String getAverageScore(){
        return String.valueOf(currentUser.getAverage_score());
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void exitProfile(View view){
        onBtnClickListener.onExitBtnClick();
    }

    public void openResults(View view){ onBtnClickListener.onMyResultsBtnClick();}
    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        notifyPropertyChanged.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        notifyPropertyChanged.remove(callback);
    }

    public void deleteAllResults(View view){
        Log.d("12", "deleteAllResults: ");

        currentUser.clearResultList();
        notifyPropertyChanged.notifyChange(this , BR._all);
        Repo.getInstance().updateUserById(currentUser , currentUser.getUser_id());
    }
    public interface OnBtnClickListener{
        public void onExitBtnClick();
        public void onMyResultsBtnClick();
    }

}
