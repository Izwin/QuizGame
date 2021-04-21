package com.izwin.mvvmtest.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.izwin.mvvmtest.R;
import com.izwin.mvvmtest.databinding.FragmentResultBinding;
import com.izwin.mvvmtest.model.ResultModel;
import com.izwin.mvvmtest.viewmodel.ResultViewModel;

public class ResultFragment extends Fragment {

    public ResultViewModel viewModel;
    public ResultModel resultModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_result, container, false);
        FragmentResultBinding binding = DataBindingUtil.inflate(inflater , R.layout.fragment_result, container , false);
        viewModel = new ViewModelProvider(this).get(ResultViewModel.class);
        viewModel.setResultModel(resultModel);
        binding.setResultViewModel(viewModel);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment fragment = new MainFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont , fragment).commit();
            }
        });

        return binding.getRoot();
    }

}