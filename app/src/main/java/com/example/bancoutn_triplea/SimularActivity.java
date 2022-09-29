package com.example.bancoutn_triplea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;
import com.example.bancoutn_triplea.databinding.ActivitySimularBinding;

public class SimularActivity extends AppCompatActivity {

    private ActivitySimularBinding binding;

    //Estas variables las necesito para validar que hayan ingresado todos los campo
    //y para despues poder devolver a la otra pantalla
    private EditText TasaNominal;
    private EditText TasaEfectiva;
    private EditText capitalAInvertir;
    private SeekBar dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular);

        binding = ActivitySimularBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button simularBtn = binding.simularBtn;

        dias = binding.seekBarSimular;

    }

    public void confirmarSimulacion(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("dias", dias.getProgress()*30);
        finish();
    }

}