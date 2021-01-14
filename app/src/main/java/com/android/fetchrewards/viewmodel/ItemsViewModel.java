package com.android.fetchrewards.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.fetchrewards.model.Items;
import com.android.fetchrewards.network.NetworkAPI;
import com.android.fetchrewards.network.RetrofitState;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * This is the view model class which acts like a manager that is responsible for making the network call,
 * filtering the required data and posting the data back to the view.
 */
public class ItemsViewModel extends ViewModel {

    private MutableLiveData<Map<Integer, List<Items>>> itemsList;
    private Map<Integer, List<Items>> data;

    public ItemsViewModel() {
        itemsList = new MutableLiveData<>();
    }

    public MutableLiveData<Map<Integer, List<Items>>> getItemsListObserver() {
        return itemsList;
    }

    public void makeApiCall() {
        final NetworkAPI networkAPI = RetrofitState.getRetrofitInstance().create(NetworkAPI.class);
        final Call<List<Items>> call = networkAPI.getItems();
        call.enqueue(new Callback<List<Items>>() {
            @Override
            public void onResponse(Call<List<Items>> call, Response<List<Items>> response) {
                if (response.body() != null) {
                    final List<Items> filteredItems = filterNullOrEmptyItems(response.body());
                    final List<Items> sortedItems = sortByListIdAndId(filteredItems);
                    data = groupByListId(sortedItems);
                    itemsList.postValue(data);
                } else {
                    itemsList.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Items>> call, Throwable t) {
                itemsList.postValue(null);
            }
        });
    }

    /**
     * This filters the null or empty items
     */
    public List<Items> filterNullOrEmptyItems(@NonNull List<Items> listItems){
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
    public Map<Integer, List<Items>> groupByListId(@NonNull final List<Items> sortedItems){
        return sortedItems.stream().collect(Collectors.groupingBy(Items::getListId));
    }


}
