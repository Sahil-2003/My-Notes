package com.example.mynotes.ui.stack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mynotes.Adapters.StackAdapter;
import com.example.mynotes.Models.PDFTopics;
import com.example.mynotes.Models.PutPDF;
import com.example.mynotes.StackUpload;
import com.example.mynotes.databinding.FragmentStackBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Stack extends Fragment {

    private FragmentStackBinding binding;
    ArrayList<PutPDF> stackList;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    StackAdapter stackAdapter;

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
        databaseReference = FirebaseDatabase.getInstance().getReference("UploadPDF");

        recyclerView = binding.recyclerView;
        final ImageView add = binding.addButton;
        stackList = new ArrayList<>();

        stackAdapter = new StackAdapter(stackList, getContext());
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(stackAdapter);
        retrieveFiles();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), StackUpload.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    public void retrieveFiles(){
        databaseReference.child("Stack").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        PutPDF pdf = snapshot1.getValue(PutPDF.class);
                        assert pdf != null;
                        stackList.add(pdf);
                    }
                }
                stackAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}