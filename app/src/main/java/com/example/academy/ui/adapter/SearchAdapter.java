package com.example.academy.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academy.R;
import com.example.academy.data.model.AbbreviatedCoinCard;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private static List<AbbreviatedCoinCard> coins;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Define click listener for the ViewHolder's View
        public ImageView coinLogo;
        public TextView coinName;
        public TextView coinNameAbbreviation;

        public TextView isFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coinLogo = itemView.findViewById(R.id.coinLogoConciseCard);
            coinName = itemView.findViewById(R.id.coinNameConciseCard);
            coinNameAbbreviation = itemView.findViewById(R.id.coinNameAbbreviationConciseCard);
            isFavorite = itemView.findViewById(R.id.favorite);
        }
    }

    public SearchAdapter(List<AbbreviatedCoinCard> coins) {
        this.coins = coins;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.abbreviated_coin_card, parent, false);
        //view.setVisivility(View.VISIBLE);
        return new SearchAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AbbreviatedCoinCard coinCard = coins.get(position);

        Picasso.get().load(coinCard.getSymbolUrl())
                .placeholder(R.drawable.hourglass)
                .error(R.drawable.coin)
                .resize(170, 0)
                .centerCrop()
                .into(holder.coinLogo);

        holder.coinName.setText(coinCard.getName());
        holder.coinNameAbbreviation.setText(coinCard.getNameAbbreviation());
        //holder.coinLogo.setImageResource();
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

}
