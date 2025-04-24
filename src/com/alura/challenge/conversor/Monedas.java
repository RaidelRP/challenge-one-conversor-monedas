package com.alura.challenge.conversor;

import java.util.ArrayList;

public class Monedas {
    private ArrayList<String> monedas;

    public Monedas() {
        this.monedas = new ArrayList<>();
        monedas.add("USD");
        monedas.add("CLP");
        monedas.add("ARS");
        monedas.add("BOB");
        monedas.add("BRL");
        monedas.add("COP");
    }

    public void listarMonedas() {
        for (int i = 0; i < monedas.size(); i++) {
            System.out.println((i + 1) + " -> " + monedas.get(i));
        }
    }

    public void agregarMoneda(String moneda) {
        monedas.add(moneda);
    }

    public String obtenerMoneda(int indice) {
        return monedas.get(indice);
    }
}
