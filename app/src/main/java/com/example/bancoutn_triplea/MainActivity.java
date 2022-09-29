package com.example.bancoutn_triplea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final int SIMULAR_ACTIVITY_REQUEST_CODE = 0;
    private ActivityMainBinding binding;
    private boolean constituirBtnActivo = false;

    private EditText nombre;
    private EditText apellido;
    private Spinner monedas;
    private ArrayAdapter<String> adapter;
    Bundle dias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar tool1 = binding.toolbar;
        setSupportActionBar(tool1);
        String[] opciones_monedas = new String[]{"PESOS", "DOLARES"};
        monedas = binding.spinnerMonedas;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones_monedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monedas.setAdapter(adapter);
        Button constituirBtn = binding.button2;
        constituirBtn.setEnabled(false);
        constituirBtn.setOnClickListener(view -> mostrarAlerta());
        nombre = binding.inputNombre;
        apellido = binding.inputApellido;
        //dias = getIntent().getExtras();
        //int diasInv = dias.getInt("dias");
        Intent mIntent = getIntent();
        int diasInv = mIntent.getIntExtra("dias", 0);

    }

    private void mostrarAlerta() {
        new AlertDialog.Builder(this)
                .setTitle("Felicitaciones "
                        + nombre.getText() + " "
                        + apellido.getText() + "! ")
                .setMessage("Tu plazo fijo de "
                        + adapter.getItem(monedas.getSelectedItemPosition())
                        + " por " + "diasInv"
                        + " ha sido constituido!")
                .setPositiveButton("Piola!", (dialogInterface, which) -> Log.d("Mensaje", "Plazo fijo constituido"))
                .show();
    }

    public void mostrarPantallaSimular(View view) {
        Intent intent = new Intent(this, SimularActivity.class);

        intent.putExtra("moneda", adapter.getItem(monedas.getSelectedItemPosition()));
        //para habilitar el constituir y probar el cuadro (hay que arreglar)
        Button constituirBtn = binding.button2;
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
