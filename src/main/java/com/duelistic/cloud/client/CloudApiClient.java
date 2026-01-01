package com.duelistic.cloud.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Typed client for the Duelistic Cloud local HTTP API.
 */
public class CloudApiClient {
    private static final String LOCAL_HOST = "127.0.0.1";

    private final int port;
    private final HttpClient client;
    private final ObjectMapper mapper;

    /**
     * Creates a client targeting a local API port.
     */
    public CloudApiClient(int port) {
        this.port = port;
        this.client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(2))
            .build();
        this.mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Returns the full list of server statuses.
     */
    public List<ServerStatus> getServers() throws IOException, InterruptedException {
        HttpResponse<String> response = sendRequest("/api/servers");
        if (response.statusCode() != 200) {
            throw new IOException("HTTP " + response.statusCode() + " for /api/servers: " + response.body());
        }
        ServersResponse payload = mapper.readValue(response.body(), ServersResponse.class);
        return payload.getServers();
    }

    /**
     * Returns the status for a single server if present.
     */
    public Optional<ServerStatus> getServer(String name) throws IOException, InterruptedException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("server name is required");
        }
        HttpResponse<String> response = sendRequest("/api/servers/" + encodePath(name.trim()));
        if (response.statusCode() == 404) {
            return Optional.empty();
        }
        if (response.statusCode() != 200) {
            throw new IOException("HTTP " + response.statusCode() + " for /api/servers/" + name + ": " + response.body());
        }
        return Optional.of(mapper.readValue(response.body(), ServerStatus.class));
    }

    /**
     * Requests that a temporary server be stopped.
     */
    public boolean stopServer(String name) throws IOException, InterruptedException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("server name is required");
        }
        HttpResponse<String> response = sendRequest("/api/servers/" + encodePath(name.trim()) + "/stop", "POST");
        if (response.statusCode() == 404) {
            return false;
        }
        if (response.statusCode() != 200) {
            throw new IOException("HTTP " + response.statusCode() + " for /api/servers/" + name + "/stop: " + response.body());
        }
        return true;
    }

    /**
     * Updates the current player count for a server.
     */
    public boolean setCurrentPlayers(String name, int currentPlayers) throws IOException, InterruptedException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("server name is required");
        }
        String path = "/api/servers/" + encodePath(name.trim()) + "/players?currentPlayers=" + currentPlayers;
        HttpResponse<String> response = sendRequest(path, "POST");
        if (response.statusCode() == 404) {
            return false;
        }
        if (response.statusCode() != 200) {
            throw new IOException("HTTP " + response.statusCode() + " for " + path + ": " + response.body());
        }
        return true;
    }

    private HttpResponse<String> sendRequest(String path) throws IOException, InterruptedException {
        return sendRequest(path, "GET");
    }

    private HttpResponse<String> sendRequest(String path, String method) throws IOException, InterruptedException {
        URI uri = URI.create("http://" + LOCAL_HOST + ":" + port + path);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
            .uri(uri)
            .timeout(Duration.ofSeconds(5));
        if ("POST".equalsIgnoreCase(method)) {
            builder.POST(HttpRequest.BodyPublishers.noBody());
        } else {
            builder.GET();
        }
        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
    }

    private static String encodePath(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
