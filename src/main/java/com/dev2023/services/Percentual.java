package com.dev2023.services;

import java.text.NumberFormat;
import java.util.Locale;

public class Percentual {
    public static void main(String[] args) {


        double juros = 1.03;
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        String[] númerosPorExtenso = {
                "zero", "um", "dois", "três", "quatro", "cinco", "seis",
                "sete", "oito", "nove", "dez", "onze", "doze", "treze",
                "quatorze", "quinze", "dezesseis", "dezessete", "dezoito",
                "dezenove"
        };

        String porcentagem = " por cento";
        int intPart = (int) juros;
        double decimalPart = juros - intPart;
        decimalPart = decimalPart * 100;
        int decimalPartInt = (int) decimalPart;
        if (decimalPartInt < 20) {
            System.out.println(  númerosPorExtenso[decimalPartInt] + " centésimos" + porcentagem);
        } else {
            System.out.println(  nf.format(decimalPart) + " centésimos" + porcentagem);
        }
    }
}

