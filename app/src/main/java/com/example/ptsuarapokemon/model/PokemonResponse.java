package com.example.ptsuarapokemon.model;

import java.util.ArrayList;

public class PokemonResponse {
    Integer count;
    String next;
    String previous;
    ArrayList<Pokemon> result;

    public ArrayList<Pokemon> getResult() {
        return result;
    }

    public void setResult(ArrayList<Pokemon> result) {
        this.result = result;
    }
}
