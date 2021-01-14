package com.android.fetchrewards.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is used to setup Retrofit instance and also define the base url.
 */
public class RetrofitState {

    public static final String BASE_URL = "https://fetch-hiring.s3.amazonaws.com/";

    private static Retrofit retrofit;

    /**
     * Singleton design pattern is used to build Retrofit's instance
     */
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
