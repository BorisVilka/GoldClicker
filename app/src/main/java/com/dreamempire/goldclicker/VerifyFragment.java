package com.dreamempire.goldclicker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dreamempire.goldclicker.databinding.FragmentVerifyBinding;


public class VerifyFragment extends Fragment {

    private FragmentVerifyBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVerifyBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
}