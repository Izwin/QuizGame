package com.example.mvvmtest.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.model.QuizItemModel;
import com.example.mvvmtest.repository.Repo;

import java.util.ArrayList;

public class QuizItemViewModel extends ViewModel {
    MutableLiveData<ArrayList<QuizItemModel>> mutableLiveData= new MutableLiveData<>();
    ArrayList<QuizItemModel> quizItemModels = new ArrayList<>();
    public void init()  {
        loadQuizItems();
        mutableLiveData.postValue(quizItemModels);

    }
    public void loadQuizItems()   {
        quizItemModels.clear();
        quizItemModels.addAll(Repo.getInstance().getQuizItemModels());
        mutableLiveData.setValue(quizItemModels);
    }

    public LiveData<ArrayList<QuizItemModel>> getMutableLiveData()   {
        init();
        return mutableLiveData;
    }

    public void update(){
        mutableLiveData.setValue(quizItemModels);
    }
}
