package com.example.academy.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.academy.ui.viewModel.CoinViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private CoinApiClient coinApiClient;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private CoinViewModel coinViewModel;
    private FloatingActionButton fab;

    public HomeFragment() {
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
        fab = view.findViewById(R.id.fab);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //List<CoinCard> coins = getCoins();
        adapter = new HomeAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        coinViewModel = new ViewModelProvider(this).get(CoinViewModel.class);
        coinViewModel.getCoins().observe(getViewLifecycleOwner(), new Observer<List<CoinCard>>() {
            @Override
            public void onChanged(List<CoinCard> coins) {
                adapter.setData(coins);
                //coinViewModel.deleteCoin("bitcoin");
            }
        });

        coinViewModel.fetchCoins();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment.newInstance())
                        .commit();
            }
        });

        return view;
    }
}