package com.example.academy.ui.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.academy.R;
import com.example.academy.ui.view.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow();
        }
    }
}