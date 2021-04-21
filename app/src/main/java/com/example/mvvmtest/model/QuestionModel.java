package com.example.mvvmtest.model;

import java.util.ArrayList;

public class QuestionModel {
    private String question;
    private boolean isTrue;

    public QuestionModel(String question, boolean isTrue) {
        this.question = question;
        this.isTrue = isTrue;
    }
    public QuestionModel() {
        this.question = question;
        this.isTrue = isTrue;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }


}
