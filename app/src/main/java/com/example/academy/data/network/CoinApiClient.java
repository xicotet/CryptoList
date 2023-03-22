package com.example.academy.data.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinApiClient {
    @GET("/v2/assets")
    Call<CoinApiResponse> getCoins();
}
