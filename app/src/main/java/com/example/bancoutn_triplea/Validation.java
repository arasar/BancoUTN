package com.example.bancoutn_triplea;

public class Validation {

    public void Validation(){}

    public boolean validate(String campo){
        if(campo.isEmpty())
            return false;
        return true;
    }

    double getDouble(String str){
        try {
            Double value = Double.parseDouble(str);
            return value;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean validateProgress(int progress){
        return progress > 0;
    }

    public boolean validatePerson(String nombre, String apellido) {
        return !nombre.isEmpty() && !apellido.isEmpty();
    }
}
