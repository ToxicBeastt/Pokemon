package com.example.ptsuarapokemon.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ptsuarapokemon.R;
import com.example.ptsuarapokemon.model.Pokemon;
import com.example.ptsuarapokemon.model.PokemonResponse;
import com.example.ptsuarapokemon.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = this.findViewById(R.id.tvTittle);

        MainActivityViewModel mViewModel = ViewModelProviders.of.get(MainActivityViewModel.class);

        mViewModel.getPokemonResponse().observe(this, new androidx.lifecycle.Observer<PokemonResponse>(){
            @Override
            public void onChanged(@Nullable PokemonResponse pokemonResponse) {
                ArrayList<Pokemon> pR = pokemonResponse.getResult();
                String s = String.valueOf(pR);
                textView.setText(s);
            }
        });


    }





}