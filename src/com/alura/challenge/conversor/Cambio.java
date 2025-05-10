package com.alura.challenge.conversor;

public record Cambio(String result, String base_code, String target_code, double conversion_rate) {
    public double convertir(double cantidad) {
        return cantidad * conversion_rate;
    }

    public String resultadoDeConversion(double cantidad) {
        // Ejemplo de formato de salida
        //      1,00 USD -> 1,00 USD. Tasa de conversión :  1 USD -> 1,00 USD
        return String.format("%.2f %s -> %.2f %s. Tasa de conversión :  1 %s -> %.2f %s", cantidad, base_code, convertir(cantidad), target_code, base_code, conversion_rate, target_code);
    }
}
