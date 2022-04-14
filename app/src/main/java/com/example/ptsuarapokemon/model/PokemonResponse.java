package com.example.ptsuarapokemon.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokemonResponse {
    public static PokemonResponse myObj;

    public PokemonResponse(){}

    public static PokemonResponse getInstance() {
        if (myObj == null) {
            myObj = new PokemonResponse();
        }

        return myObj;
    }

    @SerializedName("count")
    private Integer count;
    @SerializedName("previous")
    private Object previous;
    @SerializedName("results")
    private List<Pokemon> results = new ArrayList<>();
    @SerializedName("next")
    private String next;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }

    public void addResults(Pokemon results){ this.results.add(results);}

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }


    public static class Pokemon {

        @SerializedName("url")
        private String url;
        @SerializedName("name")
        private String name;

        public Pokemon(String name, String url){
            this.name = name;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setDataPokemon(String name, String url){
            this.name = name;
            this.url = url;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
