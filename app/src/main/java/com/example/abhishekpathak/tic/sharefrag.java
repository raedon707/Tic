package com.example.abhishekpathak.tic;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class sharefrag extends Fragment {


    public sharefrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        startActivity(Intent.createChooser(i, "Share Via"));
        return inflater.inflate(R.layout.fragment_sharefrag, container, false);
    }

}
