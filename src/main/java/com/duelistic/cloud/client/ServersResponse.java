package com.duelistic.cloud.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Wrapper for the /api/servers response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServersResponse {
    private int count;
    private List<ServerStatus> servers = new ArrayList<>();

    public ServersResponse() {
    }

    public int getCount() {
        return count;
    }

    public List<ServerStatus> getServers() {
        return servers;
    }
}
