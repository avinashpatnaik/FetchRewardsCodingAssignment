package com.android.fetchrewards;

import com.android.fetchrewards.model.Items;
import com.android.fetchrewards.network.ListRepository;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the functionality for Item Sorting and filtering.
 */
public class ItemSortAndFilterTest {
    @Test
    public void testItemSorting() {
        final ArrayList<Items> items = new ArrayList<>();
        items.add(new Items(755, 2, "Item 755"));
        items.add(new Items(684, 1, "Item 684"));
        items.add(new Items(11, 11, "Item 11"));
        items.add(new Items(99, 1, "Item 99"));
        items.add(new Items(66, 2, "Item 66"));

        final ListRepository listRepository = new ListRepository();
        final List<Items> sortedItems = listRepository.sortByListIdAndId(items);

        Assert.assertEquals(99, sortedItems.get(0).getId());
        Assert.assertEquals(1, sortedItems.get(0).getListId());
        Assert.assertEquals(684, sortedItems.get(1).getId());
        Assert.assertEquals(1, sortedItems.get(1).getListId());
        Assert.assertEquals(66, sortedItems.get(2).getId());
        Assert.assertEquals(2, sortedItems.get(2).getListId());
        Assert.assertEquals(755, sortedItems.get(3).getId());
        Assert.assertEquals(2, sortedItems.get(3).getListId());
        Assert.assertEquals(11, sortedItems.get(4).getId());
        Assert.assertEquals(11, sortedItems.get(4).getListId());
    }

    @Test
    public void testFilter() {
        final ArrayList<Items> items = new ArrayList<>();
        items.add(new Items(755, 2, ""));
        items.add(new Items(684, 1, "Item 684"));
        items.add(new Items(11, 11, null));
        items.add(new Items(99, 1, "Item 99"));
        items.add(new Items(66, 2, "Item 66"));

        final ListRepository listRepository = new ListRepository();
        final List<Items> sortedItems = listRepository.filterNullOrEmptyItems(items);

        Assert.assertEquals(3, sortedItems.size());

    }
}