package com.example.notetime.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notetime.Login;
import com.example.notetime.MainActivity;
import com.example.notetime.R;
import com.example.notetime.ViewModel.LogoutViewModel;
import com.example.notetime.ViewModel.NotepadViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class Logout extends Fragment implements View.OnClickListener{

    private LogoutViewModel mViewModel;
    private View root;
    private TextView logout;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.logout_fragment, container, false);

        return root;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        logout=root.findViewById(R.id.logout);
        logout.setOnClickListener(this);

        mViewModel = ViewModelProviders.of(getActivity()).get(LogoutViewModel.class);

    }

    @Override
    public void onClick(View v) {
        mViewModel.deleteAllNotes();
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getContext(), Login.class));
        getActivity().finish();
    }
}
