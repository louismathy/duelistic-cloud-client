package com.duelistic.cloud.client;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Typed view of a single server status entry.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerStatus {
    private String name;
    private String template;
    private int port;
    private boolean online;
    private int currentPlayers;
    private int maxPlayers;
    private Instant startedAt;

    public ServerStatus() {
    }

    public String getName() {
        return name;
    }

    public String getTemplate() {
        return template;
    }

    public int getPort() {
        return port;
    }

    public boolean isOnline() {
        return online;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public Instant getStartedAt() {
        return startedAt;
    }
}
