package com.example.bancoutn_triplea;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;
import com.example.bancoutn_triplea.databinding.SimularBinding;

public class Simular extends AppCompatActivity {

    private SimularBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //PRUEBA AGUS
        Log.e("AppBanco", "onCreate: "+binding);
        setContentView(R.layout.activity_main);
        binding = SimularBinding.inflate(getLayoutInflater());
        Toolbar tool1 = binding.toolbar;
        setSupportActionBar(tool1);
        String [] opciones_monedas = new String[] {"PESOS","DOLARES"};
        Spinner monedas = binding.spinner;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones_monedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monedas.setAdapter(adapter);
    }
}