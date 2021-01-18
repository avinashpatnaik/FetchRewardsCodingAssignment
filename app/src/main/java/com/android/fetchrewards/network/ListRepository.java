package com.android.fetchrewards.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.fetchrewards.model.Items;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This is an abstract layer that is responsible for fetching the data from the network. This layer gives the value back to
 * .the view model. This layer doesn't know anything about the UI. It only talks to the network to fetch data.
 */
public class ListRepository {

    private static ListRepository listRepositoryInstance;
    private MutableLiveData<Map<Integer, List<Items>>> itemsList = new MutableLiveData<>();
    private Map<Integer, List<Items>> data;

    public static ListRepository getInstance() {
        if (listRepositoryInstance == null) {
            listRepositoryInstance = new ListRepository();
        }
        return listRepositoryInstance;
    }

    public MutableLiveData<Map<Integer, List<Items>>> getItemsList() {
        return itemsList;
    }

    public void makeApiCall() {
        final NetworkAPI networkAPI = RetrofitState.getRetrofitInstance().create(NetworkAPI.class);
        final Call<List<Items>> call = networkAPI.getItems();
        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(@NonNull Call<List<Items>> call, @NonNull Response<List<Items>> response) {
                if (response.body() != null) {
                    final List<Items> filteredItems = filterNullOrEmptyItems(response.body());
                    final List<Items> sortedItems = sortByListIdAndId(filteredItems);
                    data = groupByListId(sortedItems);
                    itemsList.setValue(data);
                } else {
                    itemsList.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Items>> call, @NonNull Throwable t) {
                itemsList.setValue(null);
            }
        });
    }

    /**
     * This filters the null or empty items
     */
    public List<Items> filterNullOrEmptyItems(@NonNull List<Items> listItems) {
        listItems = listItems.stream().filter(r -> r.getName() != null).collect(Collectors.toList());
        listItems = listItems.stream().filter(r -> !r.getName().equals("")).collect(Collectors.toList());
        return listItems;
    }

    /**
     * This sorts the results  first by list id and then sorts based on id(using id instead of name as boh complement
     * each other.
     */
    public List<Items> sortByListIdAndId(@NonNull final List<Items> filteredItems) {
        final Comparator<Items> comparator = Comparator.comparing(Items::getListId).thenComparing(Items::getId);
        final Stream<Items> sortedItems = filteredItems.stream().sorted(comparator);
        return sortedItems.collect(Collectors.toList());
    }

    /**
     * This groups the results by list id
     */
    public Map<Integer, List<Items>> groupByListId(@NonNull final List<Items> sortedItems) {
        return sortedItems.stream().collect(Collectors.groupingBy(Items::getListId));
    }

}
