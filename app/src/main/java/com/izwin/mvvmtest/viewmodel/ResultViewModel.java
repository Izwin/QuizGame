package com.izwin.mvvmtest.viewmodel;

import androidx.lifecycle.ViewModel;

import com.izwin.mvvmtest.R;
import com.izwin.mvvmtest.model.ResultModel;

public class ResultViewModel extends ViewModel {
    private ResultModel resultModel;

    public void setResultModel(ResultModel resultModel) {
        this.resultModel = resultModel;
    }

    public String getQuizItemName(){
        return resultModel.getItemName();
    }
    public String getUserName(){
        return  resultModel.getUser();
    }
    public String getScore(){
        return  String.format("%.2f", resultModel.getScore());
    }
    public String getDate(){
        return String.format("%02d:%02d", resultModel.getSeconds() / 60, resultModel.getSeconds() % 60);
    }
    public int getGrade(){
        if (resultModel.getScore()>80) return R.string.excellent;
        else if(resultModel.getScore()>60) return R.string.good;
        else if(resultModel.getScore()>40) return R.string.average;
        else return R.string.bad;
    }
    public String getRightAnswers(){
        String s = resultModel.getRight_answers() + "/" + resultModel.getQuestion_count();
        return s;
    }
    public String getUser(){
        return resultModel.getUser();
    }
}
