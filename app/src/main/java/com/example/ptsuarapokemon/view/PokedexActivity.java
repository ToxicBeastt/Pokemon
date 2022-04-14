package com.example.ptsuarapokemon.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsuarapokemon.R;
import com.example.ptsuarapokemon.model.PokemonResponse;

import java.util.ArrayList;
import java.util.List;

public class PokedexActivity extends AppCompatActivity {

    RecyclerView listView;
    List<PokemonResponse.Pokemon> pokelist = new ArrayList<>();
    PokemonCardAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        listView = findViewById(R.id.rvMain);

        pokelist = PokemonResponse.getInstance().getResults();

        adapter = new PokemonCardAdapter(getApplicationContext(),pokelist);
        LinearLayoutManager llm = new GridLayoutManager(getApplicationContext(), 2);
        listView.setLayoutManager(llm);
        listView.setAdapter(adapter);


    }
}
