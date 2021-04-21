package com.izwin.mvvmtest.viewmodel;

import android.util.Log;
import android.view.View;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

import com.izwin.mvvmtest.BR;
import com.izwin.mvvmtest.R;
import com.izwin.mvvmtest.model.QuizItemModel;
import com.izwin.mvvmtest.model.ResultModel;
import com.izwin.mvvmtest.repository.Repo;
import com.izwin.mvvmtest.view.ResultFragment;

public class GameViewModel extends ViewModel implements Observable {
    public int current_question_index = 1;
    public QuizItemModel quizItemModel;
    private boolean answerArray[];
    public int seconds;
    public boolean isAlive = true;
    private OnTestCompleted onTestCompleted;
    PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

    public void setOnTestCompleted(OnTestCompleted onTestCompleted) {
        this.onTestCompleted = onTestCompleted;
    }

    public void startTimer(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(isAlive){
                    seconds++;
                    Log.d("SDS", "run: 12");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    callbacks.notifyChange(GameViewModel.this , BR._all);
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }
    public void setQuizItemModel(QuizItemModel quizItemModel) {
        this.quizItemModel = quizItemModel;
        answerArray = new boolean[quizItemModel.getQuestionList().size()];
    }

    public String getQuestionCount() {
        return quizItemModel.getQuestionList().size() + "";
    }
    @Bindable
    public String getTimer() {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }
    @Bindable
    public String getCurrentQuestion() {
        return quizItemModel.getQuestionList().get(current_question_index - 1).getQuestion();
    }

    public void OnButtonClick(View view) {
        if (current_question_index < quizItemModel.getQuestionList().size()) {
            boolean answer = false;
            switch (view.getId()) {
                case R.id.false_button:
                    answer = false;
                    break;
                case R.id.true_button:
                    answer = true;
                    break;
            }
            answerArray[current_question_index-1] = answer;
            current_question_index++;
            callbacks.notifyChange(this, BR._all);
        } else {
            isAlive = false;
            boolean answer = false;
            switch (view.getId()) {
                case R.id.false_button:
                    answer = false;
                    break;
                case R.id.true_button:
                    answer = true;
                    break;
            }
            answerArray[current_question_index-1] = answer;
            callbacks.notifyChange(this, BR._all);
            float score;
            int right_answers_count = 0;
            for (int i = 0; i < quizItemModel.getQuestionList().size(); i++) {
                if (quizItemModel.getQuestionList().get(i).isTrue() == answerArray[i]) {
                    right_answers_count++;
                }
            }
            score = (float)right_answers_count / (float)quizItemModel.getQuestionList().size() ;
            score *= 100;
            String userName = Repo.getInstance().getUserById(Repo.getInstance().getCurrentuser_id()).getName();
            String itemName = quizItemModel.getName();
            ResultModel resultModel = new ResultModel(userName , itemName , score , right_answers_count , quizItemModel.getQuestionList().size(), seconds);
            Repo.getInstance().addResultToUser(resultModel , Repo.getInstance().getUserById(Repo.getInstance().getCurrentuser_id()).getUser_id());
            Repo.getInstance().getUserById(Repo.getInstance().getCurrentuser_id()).getResultList().add(resultModel);
            ResultFragment fragment = new ResultFragment();
            fragment.resultModel = resultModel;
            onTestCompleted.onTestCompleted(fragment);

        }

    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }
    public interface OnTestCompleted{
        public void onTestCompleted(ResultFragment fragment);
    }
}
