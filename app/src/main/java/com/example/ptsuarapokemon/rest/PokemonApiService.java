package com.example.ptsuarapokemon.rest;

import com.example.ptsuarapokemon.model.PokemonDetailResponse;
import com.example.ptsuarapokemon.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApiService {

    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("pokemon")
    Call<PokemonResponse> getPokemonList();

    @GET("pokemon/{id}/")
    Call<PokemonDetailResponse> getPokemonDetail(@Path("id") String id);
}
