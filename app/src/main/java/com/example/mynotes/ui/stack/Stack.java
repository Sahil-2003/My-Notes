package com.example.mynotes.ui.stack;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mynotes.Adapters.StackAdapter;
import com.example.mynotes.Models.PDFTopics;
import com.example.mynotes.R;
import com.example.mynotes.databinding.FragmentStackBinding;

import java.util.ArrayList;

public class Stack extends Fragment {

    private FragmentStackBinding binding;
    ArrayList<PDFTopics> stackList;

    public Stack() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStackBinding.inflate(inflater, container, false);

        final RecyclerView recyclerView = binding.recyclerView;
        final ImageView add = binding.addButton;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        stackList = new ArrayList<>();
        stackList.add(new PDFTopics("Introduction to Topics"));
        stackList.add(new PDFTopics("PreFix to PostFix"));
        stackList.add(new PDFTopics("PostFix to PreFix"));


        StackAdapter stackAdapter = new StackAdapter(stackList, getContext());
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(stackAdapter);



        return binding.getRoot();
    }
}