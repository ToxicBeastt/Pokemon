package com.example.ptsuarapokemon.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ptsuarapokemon.model.PokemonResponse;
import com.example.ptsuarapokemon.rest.PokemonApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<PokemonResponse> listPokemon;

    public LiveData<PokemonResponse> getPokemonResponse(){
        if(listPokemon == null){
            listPokemon = new MutableLiveData<PokemonResponse>();
            fetchPokemon();
        }
        return listPokemon;
    }

    private void fetchPokemon() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PokemonApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonApiService api = retrofit.create(PokemonApiService.class);
        Call<PokemonResponse> call = api.getPokemonList();

        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if(response.isSuccessful()){
                    listPokemon.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

            }

        });
    }
}
