package com.alura.challenge.conversor;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class Consulta {
    private HttpResponse<String> response;

    public Consulta(String monedaOrigen, String monedaDestino) {
        Properties config = new Properties();
        InputStream configInput;

        try {
            configInput = new FileInputStream("config.properties");
            config.load(configInput);
            String key = config.getProperty("API_KEY");

            String direccion = "https://v6.exchangerate-api.com/v6/" + key + "/pair/" + monedaOrigen + "/" + monedaDestino;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    public Cambio realizarConsulta() {
        return new Gson().fromJson(response.body(), Cambio.class);
    }
}
