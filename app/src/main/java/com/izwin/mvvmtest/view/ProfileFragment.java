package com.izwin.mvvmtest.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.izwin.mvvmtest.R;
import com.izwin.mvvmtest.viewmodel.ProfileViewModel;
import com.izwin.mvvmtest.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private  ProfileViewModel profileViewModel;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile , container , false);
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile , container , false );
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.loadCurrentUserFromRepo();
        profileViewModel.setOnBtnClickListener(new ProfileViewModel.OnBtnClickListener() {
            @Override
            public void onExitBtnClick() {
                requireActivity().finish();
            }

            @Override
            public void onMyResultsBtnClick() {
                Log.d("123", "onMyResultsBtnClick: ");
                UserResultsFragment fragment = new UserResultsFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, fragment).commit();
            }
        });
        binding.setProfileViewModel(profileViewModel);
        binding.executePendingBindings();

        return binding.getRoot();
    }

}