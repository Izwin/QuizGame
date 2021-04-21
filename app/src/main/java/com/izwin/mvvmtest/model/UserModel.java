package com.izwin.mvvmtest.model;

import java.util.ArrayList;

public class UserModel {
    public static int counter = 0;
    private float average_score;
    private int numberOfTests= 0 ;
    private int user_id;
    private String name;
    private String password;
    private ArrayList<ResultModel> resultList= new ArrayList<>();
    public UserModel(){
        this.user_id = counter;
        counter++;
        resultList.add(new ResultModel());
    }

    public UserModel(String name , String password){
        this();
        this.name = name;
        this.password = password;
    }
    public UserModel(String name , String password , ArrayList<ResultModel> resultList){
        this();
        this.name = name;
        this.password = password;
        this.resultList = resultList;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<ResultModel> getResultList() {
        ArrayList<ResultModel> resultModelArrayList = new ArrayList<>();
        for(ResultModel model : this.resultList){
            if (!model.getUser().equals("null")) resultModelArrayList.add(model);
        }
        return resultModelArrayList;
    }
    public void clearResultList(){
        this.resultList.clear();
        this.resultList.add(new ResultModel());
    }
    public void setResultList(ArrayList<ResultModel> resultList) {
        this.resultList = resultList;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setAverage_score(float average_score) {
        this.average_score = average_score;
    }

    public float getAverage_score() {
        float score = 0;
        for (ResultModel resultModel : resultList) {
            score += resultModel.getScore();
        }
        score/=resultList.size();
        return score;
    }

    public void setNumberOfTests(int numberOfTests) {
        this.numberOfTests = numberOfTests;
    }

    public int getNumberOfTests() {
        return numberOfTests;
    }
}
