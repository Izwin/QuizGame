package com.izwin.mvvmtest.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.izwin.mvvmtest.R;
import com.izwin.mvvmtest.adapters.MainAdapter;
import com.izwin.mvvmtest.model.QuizItemModel;
import com.izwin.mvvmtest.viewmodel.GameViewModel;
import com.izwin.mvvmtest.viewmodel.QuizItemViewModel;

import java.util.ArrayList;


public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    QuizItemViewModel quizItemViewModel = new  QuizItemViewModel();
    GameViewModel gameViewModel = new GameViewModel();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quizItemViewModel =  new ViewModelProvider(requireActivity()).get(QuizItemViewModel.class);
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);

        recyclerView = view.findViewById(R.id.main_rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        quizItemViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<QuizItemModel>>() {
            @Override
            public void onChanged(ArrayList<QuizItemModel> arrayList) {
                if (arrayList.isEmpty()){
                    ((MainScreenActivity)getActivity()).makeSnackbar(R.string.empty);
                    return;
                }

                mainAdapter = new MainAdapter(view.getContext(), arrayList);
                mainAdapter.setOnQuizItemClick(new MainAdapter.OnQuizItemClick() {
                    @Override
                    public void onQuizItemClick(int pos, ArrayList<QuizItemModel> quizItemModels ) {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.pressandhold, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onQuizItemLongClick(int pos, ArrayList<QuizItemModel> quizItemModels) {
                        gameViewModel.setQuizItemModel(quizItemModels.get(pos));
                        GameFragment fragment1 = new GameFragment();
                        fragment1.quizItemModel = quizItemModels.get(pos);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont , fragment1).commit();

                    }
                });
                recyclerView.setAdapter(mainAdapter);

                Log.d("SAD", arrayList.size() + "");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        return v;
    }
}