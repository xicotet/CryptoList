package com.example.academy.data.network;

import com.example.academy.data.model.Coin;
import com.example.academy.data.network.CoinResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CoinApiClient {
    @GET("/v2/assets")
    Call<List<Coin>> getCoins();
}
