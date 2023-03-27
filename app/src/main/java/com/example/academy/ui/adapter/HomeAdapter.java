package com.example.academy.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academy.R;
import com.example.academy.data.model.CoinCard;
import com.squareup.picasso.Picasso;

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
            coinLogo = itemView.findViewById(R.id.coinLogo);
            dailyVariationSymbol = itemView.findViewById(R.id.dailyVariationSymbol);
            coinName = itemView.findViewById(R.id.coinName);
            coinNameAbbreviation = itemView.findViewById(R.id.coinNameAbbreviation);
            currentPrice = itemView.findViewById(R.id.currentPrice);
            dailyVariation = itemView.findViewById(R.id.dailyVariation);

        }
    }

    public HomeAdapter(List<CoinCard> coins) {
        this.coins = coins;
    }

    public void setData(List<CoinCard> coins){
        this.coins = coins;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_card, parent, false);
        //view.setVisivility(View.VISIBLE);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CoinCard coinCard = coins.get(position);

        Picasso.get().load(coinCard.getSymbolUrl())
                .placeholder(R.drawable.hourglass)
                .error(R.drawable.coin)
                .resize(170, 0)
                .centerCrop()
                .into(holder.coinLogo);

        holder.coinName.setText(coinCard.getName());
        holder.coinNameAbbreviation.setText(coinCard.getNameAbbreviation());
        holder.currentPrice.setText(String.format("%.2f\u20AC", coinCard.getPrice()));
        holder.dailyVariation.setText(String.format("%.2f%%", coinCard.getVariation()));

        if(coinCard.getVariation() > 0)
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
