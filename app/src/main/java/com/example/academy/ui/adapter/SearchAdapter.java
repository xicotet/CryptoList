package com.example.academy.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.academy.R;
import com.example.academy.data.model.CoinCard;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private static List<CoinCard> coins;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Define click listener for the ViewHolder's View
        public CardView cardView;
        public ImageView coinLogo;
        public TextView coinName;
        public TextView coinNameAbbreviation;
        public ImageView isFavorite;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.abbreviated_card_view);
            coinLogo = itemView.findViewById(R.id.coinLogoConciseCard);
            coinName = itemView.findViewById(R.id.coinNameConciseCard);
            coinNameAbbreviation = itemView.findViewById(R.id.coinNameAbbreviationConciseCard);
            isFavorite = itemView.findViewById(R.id.favorite);
        }
    }

    public SearchAdapter(List<CoinCard> coins) {
        this.coins = coins;
    }

    public void setData(List<CoinCard> coins){
        this.coins = coins;
        notifyDataSetChanged();
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
        CoinCard coinCard = coins.get(position);

        if(!coinCard.isFavorite()){
            holder.cardView.setBackgroundColor(Color.WHITE);
        }

        Picasso.get().load(coinCard.getSymbolUrl())
                .placeholder(R.drawable.hourglass)
                .error(R.drawable.coin)
                .resize(170, 0)
                .centerCrop()
                .into(holder.coinLogo);

        holder.coinName.setText(coinCard.getName());
        holder.coinNameAbbreviation.setText(coinCard.getNameAbbreviation());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int adapterPosition = holder.getBindingAdapterPosition();
                listener.onItemClick(position);
                coinCard.setFavorite(false);
            }
        });
        //holder.coinLogo.setImageResource();
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

}
