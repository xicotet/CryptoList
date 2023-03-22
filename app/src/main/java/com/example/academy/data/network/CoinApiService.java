package com.example.academy.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinApiService {
    private static Retrofit retrofit = null;

    public static CoinApiClient getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.coincap.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CoinApiClient.class);
    }
}
