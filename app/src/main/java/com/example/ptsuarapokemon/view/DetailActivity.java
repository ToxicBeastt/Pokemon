package com.example.ptsuarapokemon.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptsuarapokemon.R;
import com.example.ptsuarapokemon.model.DetailPokemon;
import com.example.ptsuarapokemon.model.PokemonDetailResponse;
import com.example.ptsuarapokemon.model.PokemonResponse;
import com.example.ptsuarapokemon.rest.PokemonApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    TextView tvSecond;
    Button btnCatch;
    RecyclerView rvStatPokemon;
    PokemonResponse.Pokemon dataPokemon;
    PokemonStatAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        btnCatch = (Button) findViewById(R.id.btnCatch);
        rvStatPokemon = (RecyclerView) findViewById(R.id.rvStatPokemon);

        Random rand = new Random();

        Intent intent = getIntent();
        String url = intent.getStringExtra("link_pokemon");

        List<String> array = Arrays.asList(url.split("pokemon"));
        String id = array.get(1).replace("/","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokemonApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonApiService pokeapi = retrofit.create(PokemonApiService.class);

        Call<PokemonDetailResponse> call = pokeapi.getPokemonDetail(id);

        call.enqueue(new Callback<PokemonDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<PokemonDetailResponse> call, @NonNull Response<PokemonDetailResponse> response) {
                PokemonDetailResponse dataFetch = response.body();
                List<DetailPokemon.Detail> tempStat = new ArrayList<>();

                assert dataFetch != null;
                dataPokemon = new PokemonResponse.Pokemon(dataFetch.getPokemon().get(0).getName(),dataFetch.getPokemon().get(0).getUrl());

                tempStat.add(new DetailPokemon.Detail("Name",dataFetch.getPokemon().get(0).getName()));

                for (int i = 0; i < dataFetch.getStats().size(); i++) {
                    tempStat.add(new DetailPokemon.Detail(dataFetch.getStats().get(i).getStat().getName(),dataFetch.getStats().get(i).getBaseStat().toString()));
                }

                String type = "";
                for (int i = 0; i < dataFetch.getTypes().size(); i++) {
                    if(i != 0){
                        type = type.concat(", ");
                    }
                    type = type.concat(dataFetch.getTypes().get(i).getType().getName());
                }

                tempStat.add(new DetailPokemon.Detail("Type",type));

                String move = "";
                for (int i = 0; i < dataFetch.getMoves().size(); i++) {
                    if(i != 0){
                        move = move.concat(", ");
                    }
                    move = move.concat(dataFetch.getMoves().get(i).getMove().getName());
                }
                tempStat.add(new DetailPokemon.Detail("Move",move));

                adapter = new PokemonStatAdapter(getApplicationContext(),tempStat);
                LinearLayoutManager llm = new GridLayoutManager(getApplicationContext(),1);
                rvStatPokemon.setLayoutManager(llm);
                rvStatPokemon.setAdapter(adapter);

                btnCatch.setOnClickListener(v -> {
                    int randomInt = rand.nextInt(2);
                    if(randomInt == 1){
                        Toast toast = Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT);
                        toast.setMargin(50,50);
                        toast.show();
                        PokemonResponse.getInstance().addResults(new PokemonResponse.Pokemon(dataFetch.getPokemon().get(0).getName(),dataFetch.getPokemon().get(0).getUrl()));
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_SHORT);
                        toast.setMargin(50,50);
                        toast.show();
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call<PokemonDetailResponse> call, @NonNull Throwable t) {
                tvSecond.setText(call.toString());
            }
        });

    }
}
