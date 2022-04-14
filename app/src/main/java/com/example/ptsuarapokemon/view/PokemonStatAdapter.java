package com.example.ptsuarapokemon.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsuarapokemon.R;
import com.example.ptsuarapokemon.model.DetailPokemon;
import com.example.ptsuarapokemon.model.PokemonDetailResponse;

import org.w3c.dom.Text;

import java.util.List;

public class PokemonStatAdapter extends RecyclerView.Adapter<PokemonStatAdapter.ViewHolder> {

    List<DetailPokemon.Detail> pokelist;
    LayoutInflater inflater;
    Context context;

    public PokemonStatAdapter(Context context, List<DetailPokemon.Detail> pokelist){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.pokelist = pokelist;
    }

    @NonNull
    @Override
    public PokemonStatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_stat_holder,parent,false);
        return new PokemonStatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonStatAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTitlePokemon.setText(pokelist.get(position).getTitle());
        holder.tvPokemon.setText(pokelist.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return pokelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPokemon;
        TextView tvTitlePokemon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPokemon = itemView.findViewById(R.id.tvPokemon);
            tvTitlePokemon = itemView.findViewById(R.id.tvTitlePokemon);
        }
    }
}
