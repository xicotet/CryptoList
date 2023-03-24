package com.example.academy.ui.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.academy.R;
import com.example.academy.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment {

    FragmentSearchBinding fragmentSearchBinding;
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
        return fragmentSearchBinding.getRoot();
    }
}