package com.igor.scrumassistant.data.network;

import com.igor.scrumassistant.data.provider.server.JSONServerApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static NetworkService mInstance;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com"; // URL к которому обращаемся
    private Retrofit mRetrofit;

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public JSONServerApi getJSONApi() {
        return mRetrofit.create(JSONServerApi.class);
    }

}
