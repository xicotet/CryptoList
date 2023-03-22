package com.example.academy.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academy.R;
import com.example.academy.ui.data.CoinCard;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private static List<CoinCard> coins;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Define click listener for the ViewHolder's View
        public ImageView coinLogo;
        public ImageView dailyVariationSymbol;
        public TextView coinName;
        public TextView coinNameAbbreviation;
        public TextView currentPrice;
        public TextView dailyVariation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coinLogo = itemView.findViewById(R.id.coin_logo);
            dailyVariationSymbol = itemView.findViewById(R.id.daily_variation_symbol);
            coinName = itemView.findViewById(R.id.coin_name);
            coinNameAbbreviation = itemView.findViewById(R.id.coin_name_abbreviation);
            currentPrice = itemView.findViewById(R.id.current_price);
            dailyVariation = itemView.findViewById(R.id.daily_variation);

        }
    }

    public HomeAdapter(List<CoinCard> coins) {
        this.coins = coins;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_card, parent, false);
        //view.setVisivility(View.VISIBLE);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoinCard coinCard = coins.get(position);

        holder.coinName.setText(coinCard.getName());
        holder.coinNameAbbreviation.setText(coinCard.getNameAbbreviation());
        holder.currentPrice.setText(coinCard.getPrice());
        holder.dailyVariation.setText(coinCard.getVariation() + "%");

        if(Double.parseDouble(coinCard.getVariation()) > 0)
            holder.dailyVariationSymbol.setImageResource(R.drawable.increase);
        else
            holder.dailyVariationSymbol.setImageResource(R.drawable.decrease);
        //holder.coinLogo.setImageResource();
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }
}
