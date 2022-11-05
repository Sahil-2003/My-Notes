package com.example.mynotes.ui.ll;

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

import com.example.mynotes.Adapters.Adapter;
import com.example.mynotes.Models.PutPDF;
import com.example.mynotes.R;
import com.example.mynotes.Upload;
import com.example.mynotes.databinding.FragmentLinkedListBinding;
import com.example.mynotes.databinding.FragmentStackBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LinkedList extends Fragment {


    private FragmentLinkedListBinding binding;
    ArrayList<PutPDF> LL_list;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    Adapter adapter;

    public LinkedList() {
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
        binding = FragmentLinkedListBinding.inflate(inflater, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("UploadPDF");

        recyclerView = binding.recyclerView;
        final ImageView add = binding.addButton;
        LL_list = new ArrayList<>();

        adapter = new Adapter(LL_list, getContext());
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(adapter);
        retrieveFiles();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Upload.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    public void retrieveFiles(){
        databaseReference.child("LL").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                        PutPDF pdf = snapshot1.getValue(PutPDF.class);
                        assert pdf != null;
                        LL_list.add(pdf);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
