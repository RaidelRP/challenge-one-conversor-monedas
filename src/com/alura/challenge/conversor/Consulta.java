package com.alura.challenge.conversor;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta {
    private final HttpRequest request;
    private final HttpClient client;
    private final HttpResponse<String> response;

    public Consulta(String monedaOrigen, String monedaDestino) {
        String direccion = "https://v6.exchangerate-api.com/v6/91b5d7429052a85b8683eb6f/pair/" + monedaOrigen + "/" + monedaDestino;
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Cambio realizarConsulta() {
        return new Gson().fromJson(response.body(), Cambio.class);
    }
}
