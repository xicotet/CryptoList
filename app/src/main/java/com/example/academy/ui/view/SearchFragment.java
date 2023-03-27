package com.example.academy.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.academy.R;
import com.example.academy.data.model.CoinCard;
import com.example.academy.databinding.FragmentSearchBinding;
import com.example.academy.ui.adapter.SearchAdapter;
import com.example.academy.ui.viewModel.CoinViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding fragmentSearchBinding;
    private RecyclerView searchRecyclerView;
    private SearchAdapter searchAdapter;
    private ImageButton backButton;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) requireActivity()).getSupportActionBar().hide();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);
        SearchView searchView = fragmentSearchBinding.searchView;
        searchView.requestFocus(); //Para que se abra el keyboard por defecto

        searchRecyclerView = fragmentSearchBinding.searchRecyclerView;
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        backButton = fragmentSearchBinding.backButton;

        searchAdapter = new SearchAdapter(new ArrayList<>());
        searchRecyclerView.setAdapter(searchAdapter);

        CoinViewModel coinViewModel = new ViewModelProvider(this).get(CoinViewModel.class);
        coinViewModel.getCoins().observe(getViewLifecycleOwner(), new Observer<List<CoinCard>>() {
            @Override
            public void onChanged(List<CoinCard> coins) {
                searchAdapter.setData(coins);

                //coinViewModel.deleteCoin("bitcoin");
            }
        });

        coinViewModel.fetchCoins();


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment.newInstance())
                        .commit();
            }
        });

        return fragmentSearchBinding.getRoot();
    }
}