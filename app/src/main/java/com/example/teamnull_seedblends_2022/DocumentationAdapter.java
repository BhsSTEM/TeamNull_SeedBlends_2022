package com.example.teamnull_seedblends_2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DocumentationAdapter extends RecyclerView.Adapter<DocumentationAdapter.DocumentationViewHolder> {
    private ArrayList<DocumentationCard> mDocumentationList;

    @NonNull
    @Override
    public DocumentationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.documentation_card, parent, false);
        DocumentationViewHolder dvh = new DocumentationViewHolder(v);
        return dvh;
    }

    public DocumentationAdapter(ArrayList<DocumentationCard> documentationList) {
        mDocumentationList = documentationList;
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentationViewHolder holder, int position) {
        DocumentationCard currentDocumentation = mDocumentationList.get(position);

        holder.associatedField.setText(currentDocumentation.getAssociatedField());
        holder.date.setText(currentDocumentation.getDate());
        holder.documentation.setText(currentDocumentation.getDocumentation());
    }

    @Override
    public int getItemCount() {
        return mDocumentationList.size();
    }

    public static class DocumentationViewHolder extends RecyclerView.ViewHolder {
        public TextView associatedField;
        public TextView date;
        public TextView documentation;

        public DocumentationViewHolder(@NonNull View itemView) {
            super(itemView);
            associatedField = itemView.findViewById(R.id.associatedFieldText);
            date = itemView.findViewById(R.id.dateText);
            documentation = itemView.findViewById(R.id.documentationText);
        }
    }
}
