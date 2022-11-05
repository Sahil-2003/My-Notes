package com.example.mynotes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Models.PutPDF;
import com.example.mynotes.PDFScreenView;
import com.example.mynotes.R;
import com.example.mynotes.databinding.ContentsViewBinding;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    ArrayList<PutPDF> List;
    Context context;

    public Adapter(){}

    public Adapter(ArrayList<PutPDF> List, Context context) {
        this.List = List;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.contents_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PutPDF topics = List.get(position);
        holder.binding.topics.setText(topics.getName());
        holder.binding.pdfViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PDFScreenView.class);
                intent.putExtra("pdfName", topics.getName());
                intent.putExtra("pdfUrl", topics.getUrl());

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ContentsViewBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ContentsViewBinding.bind(itemView);
        }
    }
}
