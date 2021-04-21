package com.example.mvvmtest.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.model.ResultModel;
import com.example.mvvmtest.model.UserModel;
import com.example.mvvmtest.repository.Repo;

import java.util.ArrayList;

public class LeaderViewModel extends ViewModel {
    MutableLiveData<ArrayList<ResultModel>> resultModelMutableLiveData;
    ArrayList<ResultModel> resultModels = new ArrayList<>();
    public LeaderViewModel(){
        resultModelMutableLiveData = new MutableLiveData<>();


    }
    public void init(){
        resultModels.clear();
        loadResults();
        resultModelMutableLiveData.setValue(resultModels);
    }
    public void loadResults(){
        ArrayList<UserModel> userModels = Repo.getInstance().getUserList();
        resultModels.clear();
        for(UserModel model : userModels){
            resultModels.addAll(model.getResultList());
        }
    }

    public MutableLiveData<ArrayList<ResultModel>> getResultModelMutableLiveData() {
        init();
        return resultModelMutableLiveData;
    }
}
