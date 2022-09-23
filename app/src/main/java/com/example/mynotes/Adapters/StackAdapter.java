package com.example.mynotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Models.PDFTopics;
import com.example.mynotes.R;
import com.example.mynotes.databinding.FragmentStackBinding;
import com.example.mynotes.databinding.StackContentsBinding;

import java.util.ArrayList;

public class StackAdapter extends RecyclerView.Adapter<StackAdapter.ViewHolder>{

    ArrayList<PDFTopics> stackList;
    Context context;

    public StackAdapter(){}

    public StackAdapter(ArrayList<PDFTopics> stackList, Context context) {
        this.stackList = stackList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.stack_contents, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PDFTopics topics = stackList.get(position);
        holder.binding.topics.setText(topics.getPdf_name());
        holder.binding.pdfViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, topics.getPdf_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return stackList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        StackContentsBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = StackContentsBinding.bind(itemView);
        }
    }
}
