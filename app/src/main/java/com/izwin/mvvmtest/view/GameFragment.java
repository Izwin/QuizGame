package com.izwin.mvvmtest.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.izwin.mvvmtest.R;
import com.izwin.mvvmtest.databinding.FragmentGameBinding;
import com.izwin.mvvmtest.model.QuizItemModel;
import com.izwin.mvvmtest.viewmodel.GameViewModel;

public class GameFragment extends Fragment {

    public GameViewModel gameViewModel = new GameViewModel();
    public QuizItemModel quizItemModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.setQuizItemModel(quizItemModel);
        FragmentGameBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container , false);
        binding.setGameViewModel(gameViewModel);
        gameViewModel.startTimer();
        gameViewModel.setOnTestCompleted(new GameViewModel.OnTestCompleted() {
            @Override
            public void onTestCompleted(ResultFragment fragment) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont , fragment).commit();
            }
        });
        return binding.getRoot();
    }

}