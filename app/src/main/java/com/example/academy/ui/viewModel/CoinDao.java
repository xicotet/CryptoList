package com.example.academy.ui.viewModel;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import androidx.lifecycle.LiveData;

import com.example.academy.data.model.CoinCard;

import java.util.List;

public interface CoinDao {

    @Query("SELECT * FROM CoinCard")
    LiveData<List<CoinCard>> getCoins();

    @Query("SELECT coin_name, coin_name_abbreviation, coin_symbol " +
            "FROM CoinCard")
    LiveData<List<CoinCard>> getConciseCoins();

    @Query("SELECT coin_name, coin_name_abbreviation, coin_symbol " +
            "FROM CoinCard WHERE daily_variation > 0")
    LiveData<List<CoinCard>> getGainerCoins();

    @Query("SELECT coin_name, coin_name_abbreviation, coin_symbol " +
            "FROM CoinCard WHERE daily_variation < 0")
    LiveData<List<CoinCard>> getLoserCoins();


    //Faltaria aqui filtrar por las 'Trending Coins'


    @Insert
    void addCoin(CoinCard coinCard);

    @Insert
    void addCoins(List<CoinCard> coinCard);

    @Delete
    void deleteCoin(CoinCard coinCard);




}
