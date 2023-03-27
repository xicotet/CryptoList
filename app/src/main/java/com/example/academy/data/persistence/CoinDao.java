package com.example.academy.data.persistence;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.academy.data.model.CoinCard;

import java.util.List;

@Dao
public interface CoinDao {

    @Query("SELECT * FROM CoinCard")
    LiveData<List<CoinCard>> getCoins();



    //Para la pantalla de busqueda la Card es mas concisa
    /*@Query("SELECT coin_name, coin_name_abbreviation, coin_symbol " +
            "FROM CoinCard")
    LiveData<List<CoinCard>> getConciseCoins();

    //Para el chip 'Gainer' del SearchFragment
    @Query("SELECT coin_name, coin_name_abbreviation, coin_symbol " +
            "FROM CoinCard WHERE daily_variation > 0")
    LiveData<List<CoinCard>> getGainerCoins();

    //Para el chip 'Loser' del SearchFragment
    @Query("SELECT coin_name, coin_name_abbreviation, coin_symbol " +
            "FROM CoinCard WHERE daily_variation < 0")
    LiveData<List<CoinCard>> getLoserCoins();*/


    //Faltaria aqui para el chip 'Trending' del SearchFragment


    @Insert
    void addCoin(CoinCard coinCard);

    @Insert
    void addCoins(List<CoinCard> coinCard);

    @Delete
    void deleteCoin(CoinCard coinCard);

    @Query("DELETE FROM CoinCard")
    void deleteAllCoins();






}
