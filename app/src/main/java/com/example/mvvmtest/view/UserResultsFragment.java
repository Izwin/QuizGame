package com.example.mvvmtest.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmtest.R;
import com.example.mvvmtest.adapters.ResultAdapter;
import com.example.mvvmtest.viewmodel.UserViewModel;

public class UserResultsFragment extends Fragment {

    UserViewModel userViewModel;
    ResultAdapter resultAdapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user_results, container, false);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        recyclerView = view.findViewById(R.id.recycle_view);
        if (userViewModel.getCurrentUser().getResultList().isEmpty()){
            ((MainScreenActivity)getActivity()).makeSnackbar(R.string.empty);
        }
        else{
            resultAdapter = new ResultAdapter(getContext() , userViewModel.getCurrentUser().getResultList());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(resultAdapter);

        }

        return view;
    }
}