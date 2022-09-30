package com.example.bancoutn_triplea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;
import com.example.bancoutn_triplea.databinding.ActivitySimularBinding;

public class SimularActivity extends AppCompatActivity {

    private ActivitySimularBinding binding;
    private boolean simularBtnActivo = false;


    private EditText tasaNominal;
    private EditText tasaEfectiva;
    private EditText capitalAInvertir;
    private SeekBar dias;
    private TextView mostrarDias;
    private TextView mostrarPlazo;
    private TextView mostrarCapital;
    private TextView mostrarIntereses;
    private TextView mostrarMonto;
    private TextView mostrarMontoAnual;

    private Validation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular);

        validation = new Validation();

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
        capitalAInvertir = binding.capitalAInvertir;
        mostrarDias = binding.diasTextView;
        mostrarDias.setText("0 días");
        tasaNominal = binding.TasaNominal;
        tasaEfectiva = binding.TasaEfectiva;
        mostrarPlazo = binding.plazoTextView;
        mostrarPlazo.setText("Plazo: 0 días");
        mostrarCapital = binding.capitalTextView;
        mostrarIntereses = binding.interesesGanadosTextView;
        mostrarMonto = binding.montoTotalTextView;
        mostrarMontoAnual = binding.montoAnualTextView;

        dias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar dias) {}
            @Override
            public void onStartTrackingTouch(SeekBar dias) {}
            @Override
            public void onProgressChanged(SeekBar dias, int progress,boolean fromUser) {
                mostrarProgreso(progress);
                calcular();
            }
        });

        capitalAInvertir.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                calcular();
            }

            @Override public void afterTextChanged(Editable editable) {}
        });

        //mostrarIntereses.setText(cap*tna/100);





        //mostrarIntereses.setText("Intereses ganados: $"+ interesMensual);

        //mostrarMontoAnual.setText("Monto anual: $" + interesAnual + capital);
        //mostrarMonto.setText(cap+cap*tna/100);

        //mostrarMontoAnual.setText(cap + cap * tea / 100);
        //mostrarMontoAnual.setText("100");
        //mostrarSimulacion(String.valueOf(tasaNominal), String.valueOf(tasaEfectiva), String.valueOf(capitalAInvertir), String.valueOf(mostrarDias));

    }

    private void calcular(){
        actualizarPlazo(dias.getProgress());
        actualizarCapital();
        actualizarInteresesGanados();
        actualizarMonto();
        actualizarMontoAnual();
    }

    private void actualizarMontoAnual() {
        String capitalString = capitalAInvertir.getText().toString();
        boolean capitalValido = validation.validate(capitalString);

        String tasaNominalString = tasaNominal.getText().toString();
        boolean tasaNominalValida = validation.validate(tasaNominalString);

        String tasaEfectivaString = tasaEfectiva.getText().toString();
        boolean tasaEfectivaValida = validation.validate(tasaEfectivaString);

        if(capitalValido && tasaEfectivaValida && tasaNominalValida){
            double interesAnual = validation.getDouble(capitalString) * validation.getDouble(tasaNominalString) / 100;
            double interesMensual = interesAnual * dias.getProgress() * validation.getDouble(capitalString) / 12;
            double montoAnual = interesAnual + validation.getDouble(capitalString);
            mostrarMontoAnual.setText("Monto total anual: $" + montoAnual);
        }
    }

    private void actualizarMonto() {
        String capitalString = capitalAInvertir.getText().toString();
        boolean capitalValido = validation.validate(capitalString);

        String tasaNominalString = tasaNominal.getText().toString();
        boolean tasaNominalValida = validation.validate(tasaNominalString);

        String tasaEfectivaString = tasaEfectiva.getText().toString();
        boolean tasaEfectivaValida = validation.validate(tasaEfectivaString);

        if(capitalValido && tasaEfectivaValida && tasaNominalValida){
            double interesAnual = validation.getDouble(capitalString) * validation.getDouble(tasaNominalString) / 100;
            double interesMensual = interesAnual * dias.getProgress() * validation.getDouble(capitalString) / 12;
            mostrarMonto.setText("Monto total: $" + interesMensual + validation.getDouble(capitalString));
        }

    }

    private void actualizarCapital() {
        String capital = capitalAInvertir.getText().toString();
        mostrarCapital.setText("Capital: $" + capital);
    }

    private void actualizarInteresesGanados(){
        String capital = capitalAInvertir.getText().toString();
        double interesMes = validation.getDouble(tasaNominal.getText().toString()) / 12;
        int plazo = dias.getProgress();
        Log.i("plazo", "actualizarInteresesGanados: "+plazo);
        double interesesGanados = (interesMes) / 100 * validation.getDouble(capital) * plazo;
        mostrarIntereses.setText("Intereses ganados: $" + interesesGanados);

    }
    private void actualizarPlazo(int how_many){
        String plazo = String.valueOf("Plazo: "+how_many*30+" días");
        mostrarPlazo.setText(plazo);
    }

    private void mostrarProgreso(int how_many){
        String what_to_say = String.valueOf(how_many*30+" días");
        mostrarDias.setText(what_to_say);
        mostrarDias.setX(100);
        actualizarPlazo(how_many);
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
        intent.putExtra("capital", capitalAInvertir.getText().toString());
        intent.putExtra("dias", dias.getProgress()*30);
        setResult(RESULT_OK, intent);
    }
}