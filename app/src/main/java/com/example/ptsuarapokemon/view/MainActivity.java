package com.example.ptsuarapokemon.view;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsuarapokemon.R;
import com.example.ptsuarapokemon.model.PokemonResponse;
import com.example.ptsuarapokemon.rest.PokemonApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    PokemonCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listView = (RecyclerView) findViewById(R.id.rvMain);
        listView.setHasFixedSize(true);

        Button btnToPokedex = (Button) findViewById(R.id.ToPokedex);
        btnToPokedex.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(),PokedexActivity.class);
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokemonApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonApiService pokeapi = retrofit.create(PokemonApiService.class);

        Call<PokemonResponse> call = pokeapi.getPokemonList();

        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonResponse> call, @NonNull Response<PokemonResponse> response) {
                PokemonResponse dataFetch = response.body();
                List<PokemonResponse.Pokemon> pokelist = new ArrayList<>();

                for (int i = 0; i < Objects.requireNonNull(dataFetch).getResults().size(); i++) {
                    pokelist.add(i, new PokemonResponse.Pokemon(dataFetch.getResults().get(i).getName(),dataFetch.getResults().get(i).getUrl()));
                }

                adapter = new PokemonCardAdapter(getApplicationContext(),pokelist);
                LinearLayoutManager llm = new GridLayoutManager(getApplicationContext(), 2);
                adapter.OnRecyclerViewClickListener(position -> {
                    Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                    intent.putExtra("link_pokemon",pokelist.get(position).getUrl());
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    }
                );

                listView.setLayoutManager(llm);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}