package com.example.bancoutn_triplea;

public class Validation {

    public void Validation(){}
    public boolean validate(String campo){
        if(campo.isEmpty() || !isDouble(campo))
            return false;
        return true;
    }

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    double getDouble(String str){
        try {
            Double value = Double.parseDouble(str);
            return value;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
