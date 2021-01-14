package com.android.fetchrewards.network;

import com.android.fetchrewards.model.Items;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This is an interface which defines all the api call url's with each method indicating the kind of request(GET, POST, PUT)
 * with an annotation.
 */
public interface NetworkAPI {

    @GET("hiring.json")
    Call<List<Items>> getItems();
}
