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

/**
 * This adapter class is to display the item id and the item name in a recylerview
 */
public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Items> items;

    public ItemsAdapter(@NonNull final List<Items> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_view_row, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.row_item_id.setText(String.format(Locale.US, "%d", items.get(position).getId()));
        itemViewHolder.row_item_name.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }
        return 0;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        final TextView row_item_id;
        final TextView row_item_name;

        public ItemViewHolder(final View itemView) {
            super(itemView);
            row_item_id = (TextView) itemView.findViewById(R.id.row_item_id);
            row_item_name = (TextView) itemView.findViewById(R.id.row_item_name);
        }
    }
}
