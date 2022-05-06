package com.example.teamnull_seedblends_2022;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DocumentationAdapter extends RecyclerView.Adapter<DocumentationAdapter.DocumentationViewHolder> implements Filterable {
    private ArrayList<DocumentationCard> mDocumentationList;
    private ArrayList<DocumentationCard> mDocumentationListFull;

    @NonNull
    @Override
    public DocumentationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.documentation_card, parent, false);
        DocumentationViewHolder dvh = new DocumentationViewHolder(v);
        return dvh;
    }

    public DocumentationAdapter(ArrayList<DocumentationCard> documentationList) {
        mDocumentationList = documentationList;
        mDocumentationListFull = new ArrayList<>(documentationList);
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

    @Override
    public Filter getFilter() {
        return documentation_filter;
    }

    private Filter documentation_filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<DocumentationCard> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mDocumentationListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (DocumentationCard item : mDocumentationListFull) {
                    if (item.getDocumentation().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDocumentationList.clear();
            mDocumentationList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
