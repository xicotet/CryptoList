package com.example.academy.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.academy.R;
import com.example.academy.ui.MainActivity;
import com.example.academy.ui.adapter.HomeAdapter;
import com.example.academy.data.model.CoinCard;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private HomeAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
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
        recyclerView = view.findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<CoinCard> coins = getCoins();

        adapter = new HomeAdapter(coins);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<CoinCard> getCoins() {
        CoinCard bitcoin = new CoinCard(
                "Bitcoin",
                "BTC",
                "26348.06€",
                "0.72"
        );

        CoinCard ethereum = new CoinCard(
                "Ethereum",
                "ETH",
                "1673.82€",
                "3"
        );

        CoinCard tether = new CoinCard(
                "Tether",
                "USDT",
                "0.93€",
                "-0.06"
        );

        CoinCard bnb = new CoinCard(
                "BNB",
                "BNB",
                "311.78€",
                "0.42"
        );

        CoinCard steth = new CoinCard(
                "Lido Staked ETH",
                "Steth",
                "1622.52€",
                "-0.29"
        );

        List<CoinCard> coins = new ArrayList<>();
        coins.add(bitcoin);
        coins.add(ethereum);
        coins.add(tether);
        coins.add(bnb);
        coins.add(steth);
        return coins;
    }
}