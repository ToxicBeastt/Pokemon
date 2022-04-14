package com.example.ptsuarapokemon.model;

import java.util.List;

public class DetailPokemon {
    private List<Detail> Pokemon;

    public List<Detail> getPokemon(){ return Pokemon;}

    public void setPokemon(List<Detail> pokemon) {
        Pokemon = pokemon;
    }

    public static class Detail{
        private String title;
        private String text;

        public Detail(String title, String text) {
            this.title = title;
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public String getTitle() {
            return title;
        }

        public void setDetail(String title, String text){
            this.title = title;
            this.text = text;
        }
    }
}
