package com.alura.challenge.conversor;

public record Cambio(String result, String base_code, String target_code, double conversion_rate) {
    public double convertir(double cantidad) {
        return cantidad * conversion_rate;
    }

    public String resultadoDeConversion(double cantidad) {
        return String.format("%.2f %s -> %.2f %s", cantidad, base_code, convertir(cantidad), target_code);
    }
}
