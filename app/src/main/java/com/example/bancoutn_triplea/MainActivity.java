package com.example.bancoutn_triplea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final int SIMULAR_ACTIVITY_REQUEST_CODE = 0;
    private ActivityMainBinding binding;
    private boolean constituirBtnActivo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar tool1 = binding.toolbar;
        setSupportActionBar(tool1);
        String [] opciones_monedas = new String[] {"PESOS","DOLARES"};
        Spinner monedas = binding.spinnerMonedas;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones_monedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monedas.setAdapter(adapter);

        Button constituirBtn = binding.button2;
        constituirBtn.setEnabled(false);
        constituirBtn.setOnClickListener(view -> mostrarAlerta());
    }

    private void mostrarAlerta() {
        new AlertDialog.Builder(this)
                .setTitle("Felicitaciones {nombre} {apellido}!")
                .setMessage("Tu plazo fijo de {capital} {moneda} por {dias} ha sido constituido!")
                .setPositiveButton("Piola!", (dialogInterface, which) -> Log.d("Mensaje","Plazo fijo constituido"))
                .show();
    }

    public void mostrarPantallaSimular(View view){
        Intent intent = new Intent(this, SimularActivity.class);
        startActivityForResult(intent, SIMULAR_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIMULAR_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                binding.button2.setEnabled(data.getBooleanExtra("simulado",false));
            }
            else{
                binding.button2.setEnabled(false);
            }
        }
    }
}