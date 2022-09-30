package com.example.bancoutn_triplea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bancoutn_triplea.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int SIMULAR_ACTIVITY_REQUEST_CODE = 0;
    private ActivityMainBinding binding;
    private boolean constituirBtnActivo = false;

    private EditText nombre;
    private EditText apellido;
    private Spinner monedas;
    private ArrayAdapter<String> adapter;

    private String capital;
    private int dias;

    private Validation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        validation = new Validation();

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

        binding.simularBtn1.setEnabled(false);

        nombre = binding.inputNombre;
        apellido = binding.inputApellido;

        nombre.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validarDatos(nombre.getText().toString(),apellido.getText().toString());
            }

            @Override public void afterTextChanged(Editable editable) {}
        });

        apellido.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validarDatos(nombre.getText().toString(),apellido.getText().toString());
            }

            @Override public void afterTextChanged(Editable editable) {}
        });
    }

    private void validarDatos(String nombre, String apellido){
        binding.simularBtn1.setEnabled(validation.validatePerson(nombre, apellido));
    }

    private void mostrarAlerta() {
        new AlertDialog.Builder(this)
                .setTitle("Felicitaciones "
                        + nombre.getText() + " "
                        + apellido.getText() + "! ")
                .setMessage("Tu plazo fijo de "
                        + capital + " "
                        + adapter.getItem(monedas.getSelectedItemPosition()).toLowerCase()
                        + " por "
                        + dias
                        + " dias ha sido constituido!")
                .setPositiveButton("Piola!", (dialogInterface, which) -> Log.d("Mensaje", "Plazo fijo constituido"))
                .show();
    }

    public void mostrarPantallaSimular(View view) {
        Intent intent = new Intent(this, SimularActivity.class);
        String moneda = adapter.getItem(monedas.getSelectedItemPosition()).toLowerCase();
        intent.putExtra("moneda", moneda);

        startActivityForResult(intent, SIMULAR_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIMULAR_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                binding.button2.setEnabled(data.getBooleanExtra("simulado",false));
                capital = data.getExtras().getString("capital");
                dias = data.getExtras().getInt("dias");
            }
            else{
                binding.button2.setEnabled(false);
            }
        }

    }
}
