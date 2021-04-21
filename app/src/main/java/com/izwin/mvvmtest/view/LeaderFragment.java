package com.izwin.mvvmtest.view;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.izwin.mvvmtest.R;
import com.izwin.mvvmtest.adapters.ResultAdapter;
import com.izwin.mvvmtest.model.ResultModel;
import com.izwin.mvvmtest.viewmodel.LeaderViewModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LeaderFragment extends Fragment {
    RecyclerView recyclerView;
    ResultAdapter adapter;
    LeaderViewModel leaderViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_leader, container, false);
        leaderViewModel = new ViewModelProvider(this).get(LeaderViewModel.class);
        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        leaderViewModel.getResultModelMutableLiveData().observe(getViewLifecycleOwner() , new Observer<ArrayList<ResultModel>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChanged(ArrayList<ResultModel> resultModels) {
                if (resultModels.isEmpty()){
                    ((MainScreenActivity)getActivity()).makeSnackbar(R.string.empty);
                    return;
                }
                resultModels.sort(new Comparator<ResultModel>() {
                    @Override
                    public int compare(ResultModel o1, ResultModel o2) {
                        if (o1.getScore()>o2.getScore())return -1;
                        if (o1.getScore()<o2.getScore())return 1;
                        return 0;
                    }
                });
                resultModels = (ArrayList<ResultModel>) resultModels.stream().limit(10).collect(Collectors.toList());
                adapter = new ResultAdapter(view.getContext() , resultModels);
                recyclerView.setAdapter(adapter);
            }
        });
        return view;


    }
}