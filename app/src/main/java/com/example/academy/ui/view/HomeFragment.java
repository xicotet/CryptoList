package com.example.academy.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.academy.R;
import com.example.academy.data.network.CoinApiClient;
import com.example.academy.data.network.CoinApiResponse;
import com.example.academy.data.network.CoinApiService;
import com.example.academy.ui.adapter.HomeAdapter;
import com.example.academy.data.model.CoinCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private CoinApiClient coinApiClient;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    public HomeFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).getSupportActionBar().hide();

        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //List<CoinCard> coins = getCoins();

        coinApiClient = CoinApiService.getClient(); // initialize CoinApiService
        fetchCoins(); // fetch coins data

        return view;
    }

    private void fetchCoins() {
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
                    List<CoinCard> coins = new ArrayList<>();
                    for (CoinApiResponse.CoinData coinData : filteredCoinDataList) {
                        CoinCard coinCard = new CoinCard(
                                coinData.getName(),
                                coinData.getSymbol(),
                                Double.parseDouble(coinData.getPriceUsd()), //Hay que redondearlo primero
                                Double.parseDouble(coinData.getChangePercent24Hr())
                        );
                        coins.add(coinCard);
                    }

                    // set up the RecyclerView adapter with the list of CoinCards


                    adapter = new HomeAdapter(coins);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("CoinAPI", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CoinApiResponse> call, Throwable t) {

            }
        });
    }
}