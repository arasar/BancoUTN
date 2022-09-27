package com.example.bancoutn_triplea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("AppBanco", "onCreate: "+binding);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar tool1 = binding.toolbar;
        setSupportActionBar(tool1);
        String [] opciones_monedas = new String[] {"PESOS","DOLARES"};
        Spinner monedas = binding.spinnerMonedas;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones_monedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monedas.setAdapter(adapter);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(Arrays.asList("PESOS","DOLARES"));
        binding.spinnerMonedas.setAdapter(adapter);*/

        Button boton = binding.button2;
        boton.setOnClickListener(view -> mostrarAlerta());

    }

    private void mostrarAlerta() {
        new AlertDialog.Builder(this)
                .setTitle("Felicitaciones {nombre} {apellido}!")
                .setMessage("Tu plazo fijo de {capital} {moneda} por {dias} ha sido constituido!")
                .setPositiveButton("Piola!", (dialogInterface, which) -> Log.d("Mensaje","Plazo fijo constituido"))
                .show();
    }
}