package com.example.bancoutn_triplea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
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
    EditText capitalAInvertir;
    SeekBar dias;
    TextView mostrarDias;
    TextView mostrarPlazo;
    TextView mostrarCapital;
    TextView mostrarIntereses;
    TextView mostrarMonto;
    TextView mostrarMontoAnual;

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
        capitalAInvertir = binding.capitalAInvertir;
        mostrarDias = binding.diasTextView;
        tasaNominal = binding.TasaNominal;
        tasaEfectiva = binding.TasaEfectiva;
        mostrarPlazo = binding.plazoTextView;
        mostrarCapital = binding.capitalTextView;
        mostrarIntereses = binding.interesesGanadosTextView;
        mostrarMonto = binding.montoTotalTextView;
        mostrarMontoAnual = binding.montoAnualTextView;

        dias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar dias) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar dias) {
            }
            @Override
            public void onProgressChanged(SeekBar dias, int progress,boolean fromUser) {
                mostrarProgreso(progress);
            }
        });

        /*mostrarIntereses.setText(cap*tna/100);
        String capital = capitalAInvertir.getText().toString();
        String tna = tasaNominal.getText().toString();
        int interesAnual = Integer.valueOf(capital) * Integer.valueOf(tna) / 100;
        int interesMensual = interesAnual *  dias.getProgress() / 12;*/

        /*mostrarCapital.setText("Capital: $" + capitalAInvertir.getText().toString());
        mostrarIntereses.setText("Intereses ganados: $"+ interesMensual);
        mostrarMonto.setText("Monto total: $" + interesMensual + capital);
        mostrarMontoAnual.setText("Monto anual: $" + interesAnual + capital);*/
        //mostrarMonto.setText(cap+cap*tna/100);

        //mostrarMontoAnual.setText(cap + cap * tea / 100);
        //mostrarMontoAnual.setText("100");
        //mostrarSimulacion(String.valueOf(tasaNominal), String.valueOf(tasaEfectiva), String.valueOf(capitalAInvertir), String.valueOf(mostrarDias));

    }

    private void mostrarProgreso(int how_many){
        String what_to_say = String.valueOf(how_many*30+" días");
        mostrarDias.setText(what_to_say);
        //int seek_label_pos = (int)((float)(dias.getMeasuredWidth()) * ((float)how_many / 60f));
        mostrarDias.setX(100);
        String plazo = String.valueOf("Plazo: "+how_many*30+" días");
        mostrarPlazo.setText(plazo);

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