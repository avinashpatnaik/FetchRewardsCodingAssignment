package com.android.fetchrewards.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.fetchrewards.R;
import com.android.fetchrewards.adapter.ItemsAdapter;
import com.android.fetchrewards.model.Items;

import java.util.List;
import java.util.Objects;

/**
 * This is the activity for displaying the items like item id and item name in a readable list.
 */
public class ItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_view);

        // Receiving the list items for the selected list id grid.
        final List<Items> itemsForListId = getIntent().getParcelableArrayListExtra("listItems");
        final TextView textView = findViewById(R.id.items_nodata);

        if (itemsForListId != null) {
            textView.setVisibility(View.GONE);
            final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.items_recyclerview);
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            final ItemsAdapter itemsAdapter = new ItemsAdapter(itemsForListId);
            final RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecorator(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider)));

            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setAdapter(itemsAdapter);
        } else {
            textView.setVisibility(View.VISIBLE);
        }

    }
}
