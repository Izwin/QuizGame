package com.example.mvvmtest.model;

import java.util.ArrayList;

public class QuizItemModel {
    private String name;
    private ArrayList<QuestionModel> questionList;

    public QuizItemModel(String name, ArrayList<QuestionModel> questionList) {
        this.name = name;
        this.questionList = questionList;
    }
    public QuizItemModel() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<QuestionModel> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<QuestionModel> questionList) {
        this.questionList = questionList;
    }
}
