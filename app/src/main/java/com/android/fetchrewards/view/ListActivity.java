package com.android.fetchrewards.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.android.fetchrewards.R;
import com.android.fetchrewards.adapter.ListAdapter;
import com.android.fetchrewards.model.Items;
import com.android.fetchrewards.viewmodel.ItemsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is the activity for displaying the grouped grid items.
 */
public class ListActivity extends AppCompatActivity implements ListAdapter.ItemClickListener {

    private Map<Integer, List<Items>> listItems;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        recyclerView = findViewById(R.id.recyclerview);
        final TextView noData = findViewById(R.id.nodata);

        initRecyclerView();

        final ItemsViewModel itemsViewModel = new ViewModelProvider(this).get(ItemsViewModel.class);
        itemsViewModel.init();

        itemsViewModel.getItemsListObserver().observe(this, itemsList -> {
            if (itemsList != null) {
                listItems = itemsList;
                listAdapter.setItemsList(listItems);
                noData.setVisibility(View.GONE);
            } else {
                noData.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initRecyclerView() {
        final AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(this, 500);
        recyclerView.setLayoutManager(autoFitGridLayoutManager);
        listAdapter = new ListAdapter(listItems, this);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onItemClick(final int position) {
        final Intent intent = new Intent(this, ItemsActivity.class);
        final ArrayList<Items> items = (ArrayList<Items>) listItems.get(position);
        intent.putParcelableArrayListExtra("listItems", items);
        startActivity(intent);
    }
}