package com.example.academy.ui.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.academy.data.model.CoinCard;
import com.example.academy.data.network.CoinApiClient;
import com.example.academy.data.network.CoinApiResponse;
import com.example.academy.data.network.CoinApiService;
import com.example.academy.data.persistence.AppDatabase;
import com.example.academy.data.persistence.CoinDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinViewModel extends AndroidViewModel {
    private final Application application;
    private final AppDatabase appDatabase;
    private final CoinDao coinDao;
    private List<CoinCard> coinCards;
    //private MutableLiveData<List<CoinCard>> coinsLiveData; //Si no fuera mutable no podemos hacer postValue()

    public CoinViewModel(Application application) {
        super(application);
        this.application = application;
        this.appDatabase = AppDatabase.getDatabase(this.getApplication());
        this.coinDao = appDatabase.getCoinDao();
        coinCards = new ArrayList<>();
        //coinsLiveData = new MutableLiveData<List<CoinCard>>();
    }

    public LiveData<List<CoinCard>> getCoins(){
        return coinDao.getCoins();
    }

    public void fetchCoins() {
        fetchCoinsFromApi();
    }

    private void fetchCoinsFromApi() {
        CoinApiClient coinApiClient = CoinApiService.getClient();
        List<String> coinIds = Arrays.asList("bitcoin", "ethereum", "ripple", "binance-coin", "cardano", "solana", "polkadot", "dogecoin", "avalanche", "shiba-inu", "chainlink", "litecoin", "algorand", "uniswap", "matic");
        Call<CoinApiResponse> call = coinApiClient.getCoins();

        call.enqueue(new Callback<CoinApiResponse>() {
            @Override
            public void onResponse(Call<CoinApiResponse> call, Response<CoinApiResponse> response) {
                if (response.isSuccessful()) {
                    List<CoinApiResponse.CoinData> coinDataList = response.body().getData();

                    // Filtrar la lista de monedas para incluir solamente las 15 que nosotros queremos
                    List<CoinApiResponse.CoinData> filteredCoinDataList = coinDataList.stream()
                            .filter(coinData -> coinIds.contains(coinData.getId()))
                            .collect(Collectors.toList());

                    // Crear una lista de CoinCard de la lista filtrada
                    List<CoinCard> coinCards = new ArrayList<>();
                    for (CoinApiResponse.CoinData coinData : filteredCoinDataList) {
                        CoinCard coinCard = new CoinCard(
                                coinData.getName(),
                                coinData.getSymbol(),
                                Double.parseDouble(coinData.getPriceUsd()), //Hay que redondearlo primero
                                Double.parseDouble(coinData.getChangePercent24Hr())
                        );
                        coinCards.add(coinCard);
                    }

                    updateCoinsInDatabase(coinCards);
                } else {
                    Log.e("CoinAPI", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CoinApiResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void updateCoinsInDatabase(List<CoinCard> coinCards) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            coinDao.deleteAllCoins();
            coinDao.addCoins(coinCards);
        });
    }

    public void deleteCoin(CoinCard coinCard) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> coinDao.deleteCoin(coinCard));
    }




    //Parte de refresh va aqui


}
