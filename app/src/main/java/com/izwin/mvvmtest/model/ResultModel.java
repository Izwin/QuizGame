package com.izwin.mvvmtest.model;

import java.util.Date;

public class ResultModel {
    private String user;
    private String itemName;
    private Date date;
    private float score;
    private int right_answers;
    private int question_count;
    private int seconds;

    public ResultModel(String user, String itemName, float score, int right_answers, int question_count, int seconds) {
        this.user = user;
        this.itemName = itemName;
        this.score = score;
        this.right_answers = right_answers;
        this.question_count = question_count;
        this.date = new Date();
        this.seconds = seconds;
    }

    public ResultModel(){
        this.user = "null";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getRight_answers() {
        return right_answers;
    }

    public void setRight_answers(int right_answers) {
        this.right_answers = right_answers;
    }

    public int getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(int question_count) {
        this.question_count = question_count;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
