package com.android.fetchrewards.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.fetchrewards.model.Items;
import com.android.fetchrewards.network.ListRepository;

import java.util.List;
import java.util.Map;


/**
 * This is the view model class which is a layer between the UI and the repository. It only fetches the data to
 * UI layer and has no idea where the data is coming from.It only talks to repository.
 */
public class ItemsViewModel extends ViewModel {

    private MutableLiveData<Map<Integer, List<Items>>> itemsList;

    public void init() {
        if (itemsList != null) {
            return;
        }
        final ListRepository listRepository = ListRepository.getInstance();
        listRepository.makeApiCall();
        itemsList = listRepository.getItemsList();
    }

    public LiveData<Map<Integer, List<Items>>> getItemsListObserver() {
        return itemsList;
    }

}
