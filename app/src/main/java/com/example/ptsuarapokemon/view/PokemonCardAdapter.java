package com.example.ptsuarapokemon.view;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsuarapokemon.R;
import com.example.ptsuarapokemon.model.PokemonResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonCardAdapter extends RecyclerView.Adapter<PokemonCardAdapter.ViewHolder>{

    List<PokemonResponse.Pokemon> pokelist;
    LayoutInflater inflater;
    Context context;

    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener{
        void OnItemClick(int position);
    }

    public void OnRecyclerViewClickListener (OnRecyclerViewClickListener listener){
        this.listener = listener;
    }

    public PokemonCardAdapter(Context context, List<PokemonResponse.Pokemon> pokelist){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.pokelist = pokelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_pokemon_holder,parent,false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonCardAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvPokemon.setText(pokelist.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return pokelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPokemon;

        public ViewHolder(@NonNull View itemView, OnRecyclerViewClickListener listener) {
            super(itemView);
            tvPokemon = itemView.findViewById(R.id.tvPokemon);
            itemView.setOnClickListener(v -> {
                if(PokemonCardAdapter.this.listener !=null && getAbsoluteAdapterPosition()!=RecyclerView.NO_POSITION){
                    PokemonCardAdapter.this.listener.OnItemClick(getAbsoluteAdapterPosition());
                }
            });
        }
    }
}
