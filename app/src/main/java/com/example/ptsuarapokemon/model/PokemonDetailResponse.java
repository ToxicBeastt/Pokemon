package com.example.ptsuarapokemon.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonDetailResponse {

    @SerializedName("forms")
    private List<PokemonDetail> forms = null;

    @SerializedName("types")
    private List<TypesDetail> types = null;

    @SerializedName("moves")
    private List<Moves> moves = null;

    @SerializedName("stats")
    private List<Stats> stats = null;

    public List<PokemonDetail> getPokemon() {
        return forms;
    }
    public List<TypesDetail> getTypes() { return types;}
    public List<Moves> getMoves(){ return moves;}

    public List<Stats> getStats() {
        return stats;
    }

    public Integer getCountType(){ return types.size(); }
    public Integer getCountMove(){ return moves.size(); }

    public static class PokemonDetail {

        @SerializedName("url")
        private String url;
        @SerializedName("name")
        private String name;

        public PokemonDetail(String name, String url){
            this.name = name;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }

        public void setDataPokemon(String name, String url){
            this.name = name;
            this.url = url;
        }

    }

    public static class TypesDetail {

        @SerializedName("slot")
        private Integer slot;
        @SerializedName("type")
        private PokemonDetailResponse.type type;

        public Integer getSlot() {
            return slot;
        }

        public PokemonDetailResponse.type getType() {
            return type;
        }

        public void setDataPokemon(Integer slot){
            this.slot = slot;
        }

    }
    public static class type {

        @SerializedName("name")
        private String name;
        @SerializedName("url")
        private String url;

        public type(String name,String url){
            this.name = name;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }

    }
    public static class Moves {

        @SerializedName("move")
        private MoveDetail move;

        public MoveDetail getMove(){
            return move;
        }

    }
    public static class MoveDetail {

        @SerializedName("name")
        private String name;
        @SerializedName("url")
        private String url;

        public MoveDetail(String name,String url){
            this.name = name;
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }
    }

    public static class Stats {

        @SerializedName("base_stat")
        private Integer base_stat;
        @SerializedName("effort")
        private Integer effort;
        @SerializedName("stat")
        private Stat stat;

        public Stats(Integer base_stat,Integer effort){
            this.base_stat = base_stat;
            this.effort = effort;
        }

        public Integer getEffort() {
            return effort;
        }

        public Integer getBaseStat() {
            return base_stat;
        }

        public Stat getStat(){return stat;}
    }
    public static class Stat {

        @SerializedName("name")
        private String name;
        @SerializedName("url")
        private String url;

        public Stat(String base_stat,String effort){
        }

        public String getUrl() {
            return url;
        }

        public String getName() {
            return name;
        }
    }



}
