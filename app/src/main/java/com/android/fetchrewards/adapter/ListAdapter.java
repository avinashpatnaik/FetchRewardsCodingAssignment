package com.android.fetchrewards.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.fetchrewards.R;
import com.android.fetchrewards.model.Items;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * This adapter class is to display the list id's in a grid view clicking upon which the corresponding
 * items are shown in a list
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.CustomViewHolder> {

    protected final ItemClickListener itemListener;
    private Map<Integer, List<Items>> itemsList;

    public ListAdapter(@NonNull final Map<Integer, List<Items>> itemsList, @NonNull final ItemClickListener itemListener) {
        this.itemsList = itemsList;
        this.itemListener = itemListener;
    }

    public void setItemsList(@NonNull final Map<Integer, List<Items>> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.CustomViewHolder holder, int position) {
        holder.listId.setText(String.format(Locale.US, "%d", position + 1));
    }

    @Override
    public int getItemCount() {
        if (itemsList != null) {
            return itemsList.size();
        }
        return 0;
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView listId;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            listId = (TextView) itemView.findViewById(R.id.list_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemListener != null) {
                itemListener.onItemClick(getBindingAdapterPosition() + 1);
            }
        }
    }
}
