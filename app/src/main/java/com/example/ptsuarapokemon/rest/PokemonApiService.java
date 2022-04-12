package com.example.ptsuarapokemon.rest;

import com.example.ptsuarapokemon.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonApiService {

    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Call<PokemonResponse> getPokemonList();
}
