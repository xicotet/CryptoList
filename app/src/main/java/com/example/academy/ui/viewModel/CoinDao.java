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

    @Insert
    void addCoin(CoinCard coinCard);

    @Insert
    void addCoins(List<CoinCard> coinCard);

   /* @Delete("DELETE  FROM CoinCard")
    void deleteCoin(CoinCard coinCard);*/



}
