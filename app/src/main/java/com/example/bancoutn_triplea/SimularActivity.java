package com.example.bancoutn_triplea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;
import com.example.bancoutn_triplea.databinding.ActivitySimularBinding;

public class SimularActivity extends AppCompatActivity {

    private ActivitySimularBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular);

        binding = ActivitySimularBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar= binding.toolbarSimular;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button simularBtn = binding.simularBtn;

    }

    public void confirmarSimulacion(View view){

        finish();
    }

}