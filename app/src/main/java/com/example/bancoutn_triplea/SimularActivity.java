package com.example.bancoutn_triplea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;
import com.example.bancoutn_triplea.databinding.ActivitySimularBinding;

public class SimularActivity extends AppCompatActivity {

    private ActivitySimularBinding binding;
    private boolean simularBtnActivo = false;

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

        Toolbar toolbar= binding.toolbarSimular;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntentResponse();
                finish();
            }
        });

        dias = binding.seekBarSimular;

    }

    public void confirmarSimulacion(View view){
        simularBtnActivo = true;
        setIntentResponse();
        finish();
    }

    @Override
    public void onBackPressed(){
        setIntentResponse();
        finish();
    }

    private void setIntentResponse(){
        Intent intent = new Intent();
        intent.putExtra("simulado",simularBtnActivo);
        intent.putExtra("dias", dias.getProgress()*30);
        setResult(RESULT_OK, intent);
    }
}